package com.example.walterlp.rpgclass.model;

import java.util.List;

public class Aluno {
    private String idUsuario;
    private String urlImagem;
    private String nome;
    private List<Turma>turmas;
    private List<Skill>habilidades;
    private int xp;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Skill> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Skill> habilidades) {
        this.habilidades = habilidades;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
