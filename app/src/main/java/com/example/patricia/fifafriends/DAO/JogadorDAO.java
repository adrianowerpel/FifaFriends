package com.example.patricia.fifafriends.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patricia.fifafriends.Model.JogadoresTime;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.util.data.DataBase;

import java.util.ArrayList;

/**
 * Created by patricia on 07/04/2018.
 */

public class JogadorDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public JogadorDAO(Context context) {
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

    public int count() {

        String sql = "select count(*) from "+DataBase.TbJogadores+";";
        Cursor cursor = conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToNext())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public ArrayList<JogadoresTime> FindAll(){

        String sql = "select * from "+DataBase.TbJogadores+" order by "+DataBase.FdGols+" desc;";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<JogadoresTime> jogadores = new ArrayList<>();

        while(cursor.moveToNext())
        {
            JogadoresTime t = new JogadoresTime();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdIdJogador)));
            t.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FdNomeJogador)));
            t.setPosicao(cursor.getString(cursor.getColumnIndex(DataBase.FdPosicao)));
            t.setGols(cursor.getInt(cursor.getColumnIndex(DataBase.FdGols)));

            int idx = cursor.getInt(cursor.getColumnIndex(DataBase.FkIdTime));
            TimeDAO td = new TimeDAO(context);
            td.open();
            t.setTime(td.FindById(idx));
            td.close();


            jogadores.add(t);
        }

        return jogadores;
    }

    public ArrayList<JogadoresTime> BuscarPorTime(int id){

        String sql = "select * from "+DataBase.TbJogadores+" where "+DataBase.FkIdTime+" = "+id+";";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<JogadoresTime> jogadores = new ArrayList<>();

        while(cursor.moveToNext())
        {
            JogadoresTime t = new JogadoresTime();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdIdJogador)));
            t.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FdNomeJogador)));
            t.setPosicao(cursor.getString(cursor.getColumnIndex(DataBase.FdPosicao)));
            t.setGols(cursor.getInt(cursor.getColumnIndex(DataBase.FdGols)));

            int idx = cursor.getInt(cursor.getColumnIndex(DataBase.FkIdTime));
            TimeDAO td = new TimeDAO(context);
            td.open();
            t.setTime(td.FindById(idx));
            td.close();


            jogadores.add(t);
        }

        return jogadores;
    }

    public ArrayList<JogadoresTime> BuscarArtilheiro(){

        String sql = "select * from "+DataBase.TbJogadores+" where "+DataBase.FdGols+" = " +
                "(select max("+DataBase.FdGols+") from "+DataBase.TbJogadores+");";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<JogadoresTime> jogadores = new ArrayList<>();

        while(cursor.moveToNext())
        {
            JogadoresTime t = new JogadoresTime();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdIdJogador)));
            t.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FdNomeJogador)));
            t.setPosicao(cursor.getString(cursor.getColumnIndex(DataBase.FdPosicao)));
            t.setGols(cursor.getInt(cursor.getColumnIndex(DataBase.FdGols)));

            int idx = cursor.getInt(cursor.getColumnIndex(DataBase.FkIdTime));
            TimeDAO td = new TimeDAO(context);
            td.open();
            t.setTime(td.FindById(idx));
            td.close();


            jogadores.add(t);
        }

        return jogadores;
    }

    public void UpdateGols(int gols,String nome,int id)
    {
        String setGolsJogador = "update "+DataBase.TbJogadores+" set "+DataBase.FdGols+" = "+gols+" where "+DataBase.FdNomeJogador+" " +
                "= '"+nome+"' and "+DataBase.FkIdTime+" = "+id+";";
        conexao.execSQL(setGolsJogador);
    }
}
