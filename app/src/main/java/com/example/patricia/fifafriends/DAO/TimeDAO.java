package com.example.patricia.fifafriends.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.util.data.DataBase;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by patricia on 29/03/2018.
 */

public class TimeDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;

    public TimeDAO(Context context) {
        banco = new DataBase(context);
    }

    public void open()
    {
        conexao = banco.getWritableDatabase();
    }

    public void close()
    {
        conexao.close();
    }

    public void SetarTimeAtivo(int id)
    {
        String setTimeAtivo = "update "+DataBase.TbTimes+" set "+DataBase.FdAtivo+" = 1 where "+DataBase.FdId+" = "+id+";";
        conexao.execSQL(setTimeAtivo);
    }

    public Times FindById(int id){

        String sql = "select * from "+DataBase.TbTimes+" where "+DataBase.FdId+" = "+id+";";
        Cursor cursor = conexao.rawQuery(sql,null);

        Times time = null;

        if(cursor.moveToNext())
        {
            time = new Times();
            time.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdId)));
            time.setTime(cursor.getString(cursor.getColumnIndex(DataBase.FdNome)));
            time.setPontos(cursor.getDouble(cursor.getColumnIndex(DataBase.FdPontos)));
            time.setAtivo(1 == cursor.getInt(cursor.getColumnIndex(DataBase.FdAtivo)));
        }

        return time;
    }

    public int count() {

        String sql = "select count(*) from "+DataBase.TbTimes+";";
        Cursor cursor = conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToNext())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public ArrayList<Times> FindAll(){

        String sql = "select * from "+DataBase.TbTimes+";";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<Times> times = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Times t = new Times();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdId)));
            t.setTime(cursor.getString(cursor.getColumnIndex(DataBase.FdNome)));
            t.setPontos(cursor.getDouble(cursor.getColumnIndex(DataBase.FdPontos)));
            t.setAtivo(1 == cursor.getInt(cursor.getColumnIndex(DataBase.FdAtivo)));

            times.add(t);
        }

        return times;
    }

    public ArrayList<Times> BuscarTimesInativos(){

        String sql = "select * from "+DataBase.TbTimes+" where "+DataBase.FdAtivo+" = 0;";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<Times> times = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Times t = new Times();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdId)));
            t.setTime(cursor.getString(cursor.getColumnIndex(DataBase.FdNome)));
            t.setPontos(cursor.getDouble(cursor.getColumnIndex(DataBase.FdPontos)));
            t.setAtivo(1 == cursor.getInt(cursor.getColumnIndex(DataBase.FdAtivo)));

            times.add(t);
        }

        return times;
    }

    public Times BuscarPeloNome(String nome){

        String sql = "select * from "+DataBase.TbTimes+" where "+DataBase.FdNome+" = '"+nome+"';";
        Cursor cursor = conexao.rawQuery(sql,null);

        Times time = null;

        if(cursor.moveToNext())
        {
            time = new Times();
            time.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdId)));
            time.setTime(cursor.getString(cursor.getColumnIndex(DataBase.FdNome)));
            time.setPontos(cursor.getDouble(cursor.getColumnIndex(DataBase.FdPontos)));
            time.setAtivo(1 == cursor.getInt(cursor.getColumnIndex(DataBase.FdAtivo)));
        }

        return time;
    }

    public void UpdatePontos(double pontos,int id)
    {
        String setTimeAtivo = "update "+DataBase.TbTimes+" set "+DataBase.FdPontos+" = "+DataBase.FdPontos+" + "+pontos+" where "+DataBase.FdId+" = "+id+";";
        conexao.execSQL(setTimeAtivo);
    }
}
