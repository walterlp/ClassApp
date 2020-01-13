package com.example.walterlp.rpgclass.activity.activity.model;

import com.google.firebase.database.Exclude;

import java.util.List;
import java.util.Map;

public class Turma  extends EntidadeNome{


    private String urlImagem;

    private String descricao;
    private String codigo;
    private String idProfessor;
    private List<Aluno> alunos;

    private Map<String, String> refAlunos;



    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
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

    @Exclude
    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Exclude
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }



    public Map<String, String> getRefAlunos() {
        return refAlunos;
    }

    public void setRefAlunos(Map<String, String> refAlunos) {
        this.refAlunos = refAlunos;
    }

    public void trellarAlunos(List <Aluno> alunos){
        for(String key : refAlunos.keySet()){
            for (Aluno a: alunos) {
                if(a.getId().equals(key)){
                    this.alunos.add(a);
                }
            }
        }
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }
}
