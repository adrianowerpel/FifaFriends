package com.example.patricia.fifafriends.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patricia.fifafriends.Model.JogosRodada;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.util.data.DataBase;

import java.util.ArrayList;

/**
 * Created by aluno on 24/04/2018.
 */

public class JogosRodadaDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public JogosRodadaDAO(Context context) {
        banco = new DataBase(context);
        this.context = context;
    }

    public void open()
    {
        conexao = banco.getWritableDatabase();
    }

    public void close()
    {
        conexao.close();
    }

    public void gravar(JogosRodada jr)
    {
        String insertJogoRodada = "insert into "+DataBase.tbJogosRodada+" ("+DataBase.FdGolsRodadaTime1+","+DataBase.FdGolsRodadaTime2+"," +
                ""+DataBase.FkIdTime1+"," +
                ""+DataBase.FkIdTime2+") values (0,0,"+jr.getTime1().getId()+","+jr.getTime2().getId()+")";
        conexao.execSQL(insertJogoRodada);
    }

    public ArrayList<JogosRodada> FindAll(){

        String sql = "select * from "+DataBase.tbJogosRodada+";";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<JogosRodada> rodada = new ArrayList<>();

        while(cursor.moveToNext())
        {
            JogosRodada jr = new JogosRodada();
            jr.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdIdJogosRodada)));
            jr.setGolsTime1(cursor.getInt(cursor.getColumnIndex(DataBase.FdGolsRodadaTime1)));
            jr.setGolsTime2(cursor.getInt(cursor.getColumnIndex(DataBase.FdGolsRodadaTime2)));

            int idx = cursor.getInt(cursor.getColumnIndex(DataBase.FkIdTime1));
            TimeDAO td = new TimeDAO(context);
            td.open();
            jr.setTime1(td.FindById(idx));
            td.close();

            idx = cursor.getInt(cursor.getColumnIndex(DataBase.FkIdTime2));
            td.open();
            jr.setTime2(td.FindById(idx));
            td.close();

            rodada.add(jr);
        }

        return rodada;
    }

    public void UpdateRodada(double gols1,double gols2,int id)
    {
        String setGolsJogador = "update "+DataBase.tbJogosRodada+" set "+DataBase.FdGolsRodadaTime1+" = "+gols1+" " +
                ","+DataBase.FdGolsRodadaTime2+" = "+gols2+" where "+DataBase.FdIdJogosRodada+" = "+id+";";
        conexao.execSQL(setGolsJogador);
    }
}
