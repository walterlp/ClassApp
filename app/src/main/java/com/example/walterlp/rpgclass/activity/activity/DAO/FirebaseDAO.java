package com.example.walterlp.rpgclass.activity.activity.DAO;

import com.example.walterlp.rpgclass.activity.activity.utils.ConstantsUtils;
import com.google.firebase.auth.FirebaseAuth;
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


}
