package com.example.walterlp.rpgclass.activity.activity.DAO;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.walterlp.rpgclass.activity.activity.model.Sessao;
import com.example.walterlp.rpgclass.activity.activity.model.TipoUsuario;
import com.example.walterlp.rpgclass.activity.activity.model.Usuario;
import com.example.walterlp.rpgclass.activity.activity.ui.LoginActivity;
import com.example.walterlp.rpgclass.activity.activity.ui.MainActivity;
import com.example.walterlp.rpgclass.activity.activity.utils.ConstantsUtils;
import com.example.walterlp.rpgclass.activity.activity.utils.GetDataFromFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.List;

public class FirebaseDAO {
    private static StorageReference storageReference;
    private  static DatabaseReference databaseReference;
    private  static FirebaseAuth auth;
    private static  List<Usuario> usuarios = new ArrayList<>();


    public static DatabaseReference getFirebase(){
        if(databaseReference == null ){
            databaseReference = FirebaseDatabase.getInstance().getReference();

        }
        return  databaseReference;

    }
    public static FirebaseAuth getAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();

        }
        return auth;
    }

    public static StorageReference getStorage(){
        if(storageReference  == null){
            storageReference  = FirebaseStorage.getInstance().getReference();

        }
        return storageReference;

    }
    public static Query getAlunos(){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.ALUNOS_NODE);
    }

    public static Query getProfessores(){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.PROFESSORES_NODE);
    }
    public static Query getUsuarioLogado(){
        Sessao sessao  = Sessao.findById(Sessao.class, 1L);

         return  FirebaseDatabase.getInstance().getReference((sessao.getTipoUsuario().equals(TipoUsuario.ALUNO)? ConstantsUtils.ALUNOS_NODE: ConstantsUtils.PROFESSORES_NODE)).child(getAuth().getUid());
    }
    public  static Query getAluno(String id){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.ALUNOS_NODE).child(id);

    }
    public static Query getProfessor(String id){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.PROFESSORES_NODE).child(id);

    }
    public static Query getTurmas(){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.TURMAS_NODE);
    }
    public static Query getTurmaById(String id){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.TURMAS_NODE).child(id);
    }
    public void singOut(AppCompatActivity context){
        auth.signOut();
        removerSessao();
        context.finish();
        context.startActivity(new Intent(context, LoginActivity.class));

    }
    public void login(final String email, final String senha, ProgressBar progressBar, AppCompatActivity context){
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    completarLoginAluno(email);
                    progressBar.setVisibility(View.GONE);
                    context.startActivity(new Intent(context, MainActivity.class));

                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Usuário não encontrado! \nPor favor, verifique suas credenciais, ou contate o suporte.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void completarLoginAluno(String email){
        usuarios = new ArrayList<>();
        Query queryAlunos = getAlunos();
        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        queryAlunos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarios.clear();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Usuario  usuario = snapshot.getValue(Usuario.class);
                        usuarios.add(usuario);


                    }
                    if(!seachUsuario(usuarios, email)){
                        completarLoginProfessor(email);
                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void completarLoginProfessor(String email){
        usuarios = new ArrayList<>();
        Query queryProfessores = getProfessores();
        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        queryProfessores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarios.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Usuario  usuario = snapshot.getValue(Usuario.class);
                    usuarios.add(usuario);



                }
                seachUsuario(usuarios, email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private boolean seachUsuario(List<Usuario> usuarios, String email){
        boolean isValid = false;
        for (Usuario u : usuarios) {
            if(u.getEmail().equals(email)){
                salvarSessao(u);
                isValid = true;
            }
        }
        return isValid;
    }
    public void salvarSessao(Usuario usuario){
       try{

           Sessao sessao = new Sessao();
           sessao.setId(1L);
           sessao.setTipoUsuario(usuario.getTipo());
           sessao.save();

       }catch (Exception e){
           e.printStackTrace();
       }
    }
    public  void removerSessao(){
        try {
            Sessao sessao = Sessao.findById(Sessao.class, 1L);
            sessao.delete();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void register(Usuario usuario, ProgressBar progressBar, AppCompatActivity context) {
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(context, task -> {
            if (task.isSuccessful()) {

                FirebaseUser user = auth.getCurrentUser();
                usuario.setId(user.getUid());
                salvarUsuario(usuario);
                salvarSessao(usuario);

                context.finish();
                context.startActivity(new Intent(context, MainActivity.class));
                progressBar.setVisibility(View.GONE);

            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Erro ao criar usuário",
                        Toast.LENGTH_SHORT).show();

            }


        });
    }

    private void salvarUsuario(Usuario usuario){

        getFirebase().getDatabase().getReference().child(usuario.isAluno()? ConstantsUtils.ALUNOS_NODE : ConstantsUtils.PROFESSORES_NODE).child(usuario.getId()).setValue(usuario);

    }

}
