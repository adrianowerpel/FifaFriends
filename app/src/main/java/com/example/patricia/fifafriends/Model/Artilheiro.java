package com.example.patricia.fifafriends.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by patricia on 29/03/2018.
 */

public class Artilheiro implements Serializable {

    private int id;
    private ArrayList<JogadoresTime> jogadores;

    public Artilheiro() {
        this.jogadores = new ArrayList<>();
    }

    public Artilheiro(int id, ArrayList<JogadoresTime> jogadores) {
        this.id = id;
        this.jogadores = jogadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<JogadoresTime> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<JogadoresTime> jogadores) {
        this.jogadores = jogadores;
    }
}
