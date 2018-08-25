package com.example.patricia.fifafriends.Model;

import java.io.Serializable;

/**
 * Created by aluno on 24/04/2018.
 */

public class JogosRodada implements Serializable {

    private int id;
    private Times time1,time2;
    private int golsTime1,golsTime2;

    public JogosRodada() {
    }

    public JogosRodada(int id, Times time1, Times time2, int golsTime1, int golsTime2) {
        this.id = id;
        this.time1 = time1;
        this.time2 = time2;
        this.golsTime1 = golsTime1;
        this.golsTime2 = golsTime2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Times getTime1() {
        return time1;
    }

    public void setTime1(Times time1) {
        this.time1 = time1;
    }

    public Times getTime2() {
        return time2;
    }

    public void setTime2(Times time2) {
        this.time2 = time2;
    }

    public int getGolsTime1() {
        return golsTime1;
    }

    public void setGolsTime1(int golsTime1) {
        this.golsTime1 = golsTime1;
    }

    public int getGolsTime2() {
        return golsTime2;
    }

    public void setGolsTime2(int golsTime2) {
        this.golsTime2 = golsTime2;
    }
}
