package com.example.walterlp.rpgclass.activity.activity.DAO;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walterlp.rpgclass.activity.activity.model.Aluno;
import com.example.walterlp.rpgclass.activity.activity.model.Professor;
import com.example.walterlp.rpgclass.activity.activity.model.Sessao;
import com.example.walterlp.rpgclass.activity.activity.model.TipoUsuario;
import com.example.walterlp.rpgclass.activity.activity.ui.CadastroActivity;
import com.example.walterlp.rpgclass.activity.activity.ui.MainActivity;
import com.example.walterlp.rpgclass.activity.activity.utils.ConstantsUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseDAO {
    private static StorageReference storageReference;
    private  static DatabaseReference databaseReference;
    private  static FirebaseAuth auth;


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
    public static Query getTurmas(){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.TURMAS_NODE);
    }
    public static Query getTurmaById(String id){
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.TURMAS_NODE).child(id);
    }


    public void register(final String nome, final String email, String  senha, final Sessao sessao, ProgressBar progressBar, AppCompatActivity context) {
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(context, task -> {
            if (task.isSuccessful()) {

                FirebaseUser user = auth.getCurrentUser();
                if (sessao.getTipoUsuario().equals(TipoUsuario.ALUNO)){
                    Aluno aluno  = new Aluno();
                    aluno.setId(user.getUid());
                    aluno.setNome(nome);
                    aluno.setEmail(email);
                    salvarAluno(aluno);

                }else {

                    Professor professor = new Professor();
                    professor.setNome(nome);
                    professor.setEmail(email);
                    professor.setId(user.getUid());
                    salvarProfessor(professor);
                }

                try{
                    sessao.setId(1L);
                    sessao.save();

                }catch (Exception e){
                    e.printStackTrace();
                }




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

    public void salvarAluno(Aluno aluno){

        getFirebase().getDatabase().getReference().child(String.valueOf(ConstantsUtils.ALUNOS_NODE)).child(aluno.getId()).setValue(aluno);

    }
    public void salvarProfessor(Professor professor){
        getFirebase().getDatabase().getReference().child(String.valueOf(ConstantsUtils.PROFESSORES_NODE)).child(professor.getId()).setValue(professor);
    }
}
