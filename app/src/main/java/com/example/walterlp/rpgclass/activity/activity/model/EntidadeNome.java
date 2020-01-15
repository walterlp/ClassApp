package com.example.walterlp.rpgclass.activity.activity.model;

import java.io.Serializable;

public class EntidadeNome implements Serializable {
    private String nome;
    private String id;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
