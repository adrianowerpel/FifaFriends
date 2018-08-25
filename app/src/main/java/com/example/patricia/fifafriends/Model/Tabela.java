package com.example.patricia.fifafriends.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by patricia on 29/03/2018.
 */

public class Tabela implements Serializable {
    private int id;
    private Times times;

    public Tabela() {
    }

    public Tabela(int id, Times times) {
        this.id = id;
        this.times = times;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }
}
