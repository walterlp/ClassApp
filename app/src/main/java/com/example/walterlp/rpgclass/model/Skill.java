package com.example.walterlp.rpgclass.model;

public class Skill {
    private String idAluno;
    private String idskill;
    private String nome;
    private int uso;
    private int custo;

    public Skill(){}

    public String getIdskill() {
        return idskill;
    }

    public void setIdskill(String idskill) {
        this.idskill = idskill;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }
}
