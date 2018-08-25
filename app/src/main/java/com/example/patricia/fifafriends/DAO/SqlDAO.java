package com.example.patricia.fifafriends.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.patricia.fifafriends.Model.JogosRodada;
import com.example.patricia.fifafriends.util.data.DataBase;

/**
 * Created by patricia on 09/05/2018.
 */

public class SqlDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public SqlDAO(Context context) {
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

    public void ApagarBanco()
    {
        context.deleteDatabase(DataBase.dataBaseName);
    }
}
