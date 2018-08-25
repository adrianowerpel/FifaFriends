package com.example.patricia.fifafriends;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.patricia.fifafriends.Adapter.ArtilheiroAdapter;
import com.example.patricia.fifafriends.DAO.JogadorDAO;
import com.example.patricia.fifafriends.DAO.TimeDAO;
import com.example.patricia.fifafriends.Model.JogadoresTime;
import com.example.patricia.fifafriends.Model.Times;

import java.util.ArrayList;
import java.util.List;

public class ArtilhariaActivity extends AppCompatActivity {

    Button btnSair;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artilharia);

        Binding();

        final ArrayList<JogadoresTime> list;

        list = BuscarTodosJogadores();

        ArtilheiroAdapter aa = new ArtilheiroAdapter(getApplicationContext(),list);
        lista.setAdapter(aa);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList<JogadoresTime> BuscarTodosJogadores() {

        JogadorDAO jd = new JogadorDAO(getApplicationContext());
        jd.open();
        ArrayList<JogadoresTime> jogadores = new ArrayList<>();
        jogadores = jd.FindAll();
        jd.close();

        return jogadores;

    }

    private void Binding() {
        lista = findViewById(R.id.ListaArtilheirosTA);
        btnSair = findViewById(R.id.btnSairT4);
    }
}
