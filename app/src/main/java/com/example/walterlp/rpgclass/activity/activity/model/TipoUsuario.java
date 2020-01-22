package com.example.walterlp.rpgclass.activity.activity.model;

import com.orm.SugarRecord;

public enum TipoUsuario {
    ALUNO("aluno"),
    PROFESSOR("professor");
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    TipoUsuario(String name) {
        this.name = name;
    }
}
