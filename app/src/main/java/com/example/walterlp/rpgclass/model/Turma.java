package com.example.walterlp.rpgclass.model;

import java.util.List;

public class Turma {

    private String idTurma;
    private String idprofessor;
    private String urlImagem;
    private String nome;
    private String descricao;
    private String codigo;
    private List<Aluno> alunos;


    public String getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(String idTurma) {
        this.idTurma = idTurma;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdprofessor() {
        return idprofessor;
    }

    public void setIdprofessor(String idprofessor) {
        this.idprofessor = idprofessor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}