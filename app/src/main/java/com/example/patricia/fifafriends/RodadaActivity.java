package com.example.patricia.fifafriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patricia.fifafriends.Adapter.RodadaAdapter;
import com.example.patricia.fifafriends.DAO.AmigoDAO;
import com.example.patricia.fifafriends.DAO.JogadorDAO;
import com.example.patricia.fifafriends.DAO.JogosRodadaDAO;
import com.example.patricia.fifafriends.DAO.TimeDAO;
import com.example.patricia.fifafriends.Model.Amigo;
import com.example.patricia.fifafriends.Model.JogadoresTime;
import com.example.patricia.fifafriends.Model.JogosRodada;
import com.example.patricia.fifafriends.Model.Times;

import java.util.ArrayList;
import java.util.Random;

public class RodadaActivity extends AppCompatActivity {

    ListView lista;
    Button btnSair,btnFinalizarRodada;

    final  int TELA_PLACAR = 6;
    final int Tela_Tabela = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodada);

        Binding();

        JogosRodadaDAO jDao = new JogosRodadaDAO(getApplicationContext());
        jDao.open();
        ArrayList<JogosRodada> rodada = new ArrayList<>();
        rodada = jDao.FindAll();
        jDao.close();

        if(rodada.size() == 0)
        {
            ArrayList<Amigo> amigos = new ArrayList<>();
            AmigoDAO ad = new AmigoDAO(getApplicationContext());
            ad.open();
            amigos = ad.BuscarTodosAmigos();
            ad.close();

            Amigo[] tamanho = new Amigo[amigos.size()];
            int i = 0;

            for (Amigo a : amigos) {
                tamanho[i] = a;
                i++;
            }

            Random r = new Random();

            while (amigos.size() > 1) {
                JogosRodada jr = new JogosRodada();
                int l, l2;

                do {
                    l = r.nextInt(i);
                    l2 = r.nextInt(i);
                } while (l == l2);

                Amigo amigo = new Amigo();
                amigo = tamanho[l];
                Amigo amigo2 = new Amigo();
                amigo2 = tamanho[l2];

                if (tamanho[l] != null && tamanho[l2] != null) {
                    amigos.remove(amigo);
                    tamanho[l] = null;
                    amigos.remove(amigo2);
                    tamanho[l2] = null;
                }

                if (amigo != null && amigo2 != null) {
                    jr.setTime1(amigo.getTime());
                    jr.setTime2(amigo2.getTime());
                    JogosRodadaDAO jrDao = new JogosRodadaDAO(getApplicationContext());
                    jrDao.open();
                    jrDao.gravar(jr);
                    jrDao.close();
                }

            }

            Toast.makeText(getApplicationContext(), "Uma nova rodada foi iniciada", Toast.LENGTH_SHORT).show();
        }

        final ArrayList<JogosRodada> list;

        list = BuscarJogosRodada();

        RodadaAdapter ra = new RodadaAdapter(getApplicationContext(),list);
        lista.setAdapter(ra);

        Toast.makeText(getApplicationContext(), "Esta rodada ja esta em andamento", Toast.LENGTH_SHORT).show();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itn = new Intent(getApplicationContext(),PlacarActivity.class);
                itn.putExtra("Rodada",list.get(position));

                startActivityForResult(itn,TELA_PLACAR);
                finish();
            }
        });

        btnFinalizarRodada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JogadorDAO jDao = new JogadorDAO(getApplicationContext());
                jDao.open();
                ArrayList<JogadoresTime> jogadores = new ArrayList<>();
                jogadores = jDao.BuscarArtilheiro();
                jDao.close();

                JogadoresTime jogador = new JogadoresTime();
                TimeDAO td = new TimeDAO(getApplicationContext());
                td.open();
                if(jogadores.size() == 1) {

                    for (JogadoresTime j: jogadores) {
                        jogador = j;
                    }
                    td.UpdatePontos(5, jogador.getTime().getId());
                }
                td.close();

                Intent itn = new Intent(getApplicationContext(),TabelaActivity.class);
                startActivityForResult(itn,Tela_Tabela);
                finish();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

    }

    private ArrayList<JogosRodada> BuscarJogosRodada() {

        JogosRodadaDAO jrDao = new JogosRodadaDAO(getApplicationContext());
        jrDao.open();
        ArrayList<JogosRodada> list = new ArrayList<>();
        list = jrDao.FindAll();
        jrDao.close();

        return list;
    }

    private void Binding() {
        lista = findViewById(R.id.ListTR);
        btnFinalizarRodada = findViewById(R.id.btnFinalizarRodadaTR);
        btnSair = findViewById(R.id.btnSairTR);
    }
}
