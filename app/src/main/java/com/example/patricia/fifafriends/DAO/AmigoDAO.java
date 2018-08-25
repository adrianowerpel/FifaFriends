package com.example.patricia.fifafriends.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.patricia.fifafriends.Model.Amigo;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.util.data.DataBase;

import java.util.ArrayList;

/**
 * Created by patricia on 29/03/2018.
 */

public class AmigoDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public AmigoDAO(Context context) {
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

    public void gravar (Amigo a)
    {
        ContentValues cv = new ContentValues();

        cv.put(DataBase.FdNome,a.getNome());
        cv.put(DataBase.FkIdTime,a.getTime().getId());

        conexao.insert(DataBase.Tbamigo,null,cv);
    }

    public Amigo BuscarAmigo(String amigo)
    {
        String sql = "select * from "+DataBase.Tbamigo+" where "+DataBase.FdNomeAmigo+" = '"+amigo+"';";
        Cursor cursor = conexao.rawQuery(sql,null);

        Amigo a = null;

        if(cursor.moveToNext()) {
            a = new Amigo();
            a.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdIdAmigo)));
            a.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FdNomeAmigo)));
        }

        return a;
    }

    public ArrayList<Amigo> BuscarTodosAmigos(){

        String sql = "select "+DataBase.Tbamigo+"."+DataBase.FdIdAmigo+","+DataBase.Tbamigo+"."+DataBase.FdNomeAmigo+","+DataBase.Tbamigo+"."+DataBase.FkIdTime+" " +
                "from "+DataBase.Tbamigo+","+DataBase.TbTimes+" " +
                "where "+DataBase.FkIdTime+" = "+DataBase.FdId+" " +
                "order by "+DataBase.FdPontos+" desc;";
        Cursor cursor = conexao.rawQuery(sql,null);

        ArrayList<Amigo> amigos = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Amigo a = new Amigo();
            a.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FdIdAmigo)));
            a.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FdNomeAmigo)));

            int idx = cursor.getInt(cursor.getColumnIndex(DataBase.FkIdTime));
            TimeDAO td = new TimeDAO(context);
            td.open();
            a.setTime(td.FindById(idx));
            td.close();

            amigos.add(a);
        }

        return amigos;
    }
}
