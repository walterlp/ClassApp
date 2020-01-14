package com.example.walterlp.rpgclass.activity.activity.model;

import com.google.firebase.database.Exclude;

import java.util.List;
import java.util.Map;

public class Usuario extends EntidadeNome {
    private String id;
    private TipoUsuario tipo;
    private String email;
    private String senha;
    private String urlImagem;
    private List<Turma> turmas;
    private Map<String, String > refTurmas;


    public Usuario() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Exclude
    public List<Turma> getTurmas() {
        return turmas;
    }

    @Exclude
    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Map<String, String> getRefTurmas() {
        return refTurmas;
    }

    public void setRefTurmas(Map<String, String> refTurmas) {
        this.refTurmas = refTurmas;
    }


    private void atrelarTurmas(List<Turma> turmas ){
        for(String key : refTurmas.keySet()){
            for (Turma t: turmas) {
                if(t.getCodigo().equals(key)){
                    this.turmas.add(t);
                }
            }
        }
    }
    @Exclude
    public boolean isAluno(){
        return this.tipo.equals(TipoUsuario.ALUNO);
    }
    @Exclude
    public boolean isProfessor(){
        return this.tipo.equals(TipoUsuario.PROFESSOR);
    }

}
