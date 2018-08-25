package com.example.patricia.fifafriends.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by patricia on 29/03/2018.
 */

public class Times implements Serializable {

    private int id;
    private String time;
    private double pontos;
    private Boolean ativo;
    private ArrayList<JogadoresTime> jogadores;

    public Times() {
        this.jogadores = new ArrayList<>();
    }

    public Times(int id, String time, double pontos, Boolean ativo, ArrayList<JogadoresTime> jogadores) {
        this.id = id;
        this.time = time;
        this.pontos = pontos;
        this.ativo = ativo;
        this.jogadores = jogadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public ArrayList<JogadoresTime> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<JogadoresTime> jogadores) {
        this.jogadores = jogadores;
    }
}
