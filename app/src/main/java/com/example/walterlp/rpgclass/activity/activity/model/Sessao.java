package com.example.walterlp.rpgclass.activity.activity.model;

import com.orm.SugarRecord;

public class Sessao extends SugarRecord<Sessao> {
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

}
