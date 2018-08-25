package com.example.patricia.fifafriends.Model;

import java.io.Serializable;

/**
 * Created by patricia on 29/03/2018.
 */

public class Amigo implements Serializable{

    private int id;
    private String nome;
    private Times time;

    public Amigo() {
    }

    public Amigo(int id, String nome, Times time) {
        this.id = id;
        this.nome = nome;
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

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }
}
