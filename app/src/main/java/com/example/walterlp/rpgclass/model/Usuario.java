package com.example.walterlp.rpgclass.model;

import com.google.firebase.database.DatabaseReference;

public class Usuario {

    private String idUsuario;
    private String nome;
    //private String sobrenome;

    public Usuario() {
    }

    public void salvar(){

        DatabaseReference firebaseRef = com.example.walterlp.rpgclass.activity.todo.helper.ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuarioRef = firebaseRef
                .child("usuarios")
                .child( getIdUsuario() );
        usuarioRef.setValue(this);

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
