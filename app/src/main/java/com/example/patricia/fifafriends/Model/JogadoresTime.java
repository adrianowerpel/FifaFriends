package com.example.patricia.fifafriends.Model;

import java.io.Serializable;

/**
 * Created by patricia on 29/03/2018.
 */

public class JogadoresTime implements Serializable {

    private int id;
    private String nome;
    private String posicao;
    private int gols;
    private Times time;

    public JogadoresTime() {
    }

    public JogadoresTime(int id, String nome, String posicao, int gols, Times time) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.gols = gols;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }
}
