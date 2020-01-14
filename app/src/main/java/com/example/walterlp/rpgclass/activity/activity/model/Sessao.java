package com.example.walterlp.rpgclass.activity.activity.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Sessao extends SugarRecord<Sessao> implements Serializable {
    private TipoUsuario tipoUsuario;


    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


    public Sessao(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Sessao() {
    }

    public void closed() {
        this.delete();
        executeQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME ='SESSAO'");
    }



}
