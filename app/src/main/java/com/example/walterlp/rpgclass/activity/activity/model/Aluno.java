package com.example.walterlp.rpgclass.activity.activity.model;

import com.google.firebase.database.Exclude;

import java.util.List;
import java.util.Map;

public class Aluno  extends  EntidadeNome{
    private String email;
    private String urlImagem;
    private List<Turma> turmas ;
    private Map<String, String> refTurmas;



    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    private void trellarTurmas(List<Turma> turmas ){
        for(String key : refTurmas.keySet()){
            for (Turma t: turmas) {
                if(t.getCodigo().equals(key)){
                    this.turmas.add(t);
                }
            }
        }
    }
    public Aluno() {
    }

}
