package com.example.patricia.fifafriends;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.patricia.fifafriends.DAO.SqlDAO;
import com.example.patricia.fifafriends.DAO.TimeDAO;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.util.data.DataBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCadastroJogador, btnGerarRodada, btnTabela, btnArtilharia,btnRecomecar,btnSair;

    final int Tela_Cadastro = 2;
    final int Tela_Tabela = 3;
    final int Tela_Artilharia = 4;
    final int TELA_RODADA = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Binding();

        btnCadastroJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplication(),CadastroActivity.class);
                startActivityForResult(itn,Tela_Cadastro);
            }
        });

        btnGerarRodada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),RodadaActivity.class);
                startActivityForResult(itn,TELA_RODADA);
            }
        });

        btnTabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplication(),TabelaActivity.class);
                startActivityForResult(itn,Tela_Tabela);
            }
        });

        btnArtilharia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplication(),ArtilhariaActivity.class);
                startActivityForResult(itn,Tela_Artilharia);
            }
        });

        btnRecomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlDAO sDao = new SqlDAO(getApplicationContext());
                sDao.open();
                sDao.ApagarBanco();
                sDao.close();
                Toast.makeText(getApplicationContext(), "App reiniciado! Por favor cadastre-se novamente para gerar novas rodadas", Toast.LENGTH_LONG).show();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    private void Binding() {
        btnCadastroJogador = findViewById(R.id.btnCadastroJogadorT1);
        btnGerarRodada = findViewById(R.id.btnGerarRodadaT1);
        btnTabela = findViewById(R.id.btnTabelaT1);
        btnArtilharia = findViewById(R.id.btnArtilhariaT1);
        btnRecomecar = findViewById(R.id.btnRecomecarApp);
        btnSair = findViewById(R.id.btnSairT1);
    }
}
