package com.example.patricia.fifafriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patricia.fifafriends.DAO.JogadorDAO;
import com.example.patricia.fifafriends.DAO.JogosRodadaDAO;
import com.example.patricia.fifafriends.DAO.TimeDAO;
import com.example.patricia.fifafriends.Model.JogadoresTime;
import com.example.patricia.fifafriends.Model.JogosRodada;
import com.example.patricia.fifafriends.Model.Times;

import java.util.List;

public class PlacarActivity extends AppCompatActivity {

    TextView txtTime1,txtTime2,txtGolsTime1,txtGolsTime2;
    EditText edtGolsJogadorT1,edtGolsJogadorT2;
    Spinner spnJogadoresT1,spnJogadoresT2;
    Button btnRegistrarT1,btnRegistrarT2,btnFinalizar;

    int golsTime1 = 0;
    double g1 = 0;
    int golsTime2 = 0;
    double g2 = 0;
    int golsJogador1 = 0;
    int golsJogador2 = 0;
    double pontosTime1 = 0;
    double pontosTime2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);

        Binding();

        final JogosRodada jr = (JogosRodada) getIntent().getExtras().get("Rodada");

        preencherSpinnerJogadoresTime1(jr.getTime1().getId());
        preencherSpinnerJogadoresTime2(jr.getTime2().getId());

        txtTime1.setText(jr.getTime1().getTime());
        txtTime2.setText(jr.getTime2().getTime());

        btnRegistrarT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtGolsJogadorT1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "O campo 'Quantidade Gols Jogador' esta vazio", Toast.LENGTH_LONG).show();
                }
                else{
                    golsJogador1 = Integer.parseInt(edtGolsJogadorT1.getText().toString());
                    JogadorDAO jDao = new JogadorDAO(getApplicationContext());
                    jDao.open();
                    jDao.UpdateGols(golsJogador1, spnJogadoresT1.getSelectedItem().toString(), jr.getTime1().getId());
                    jDao.close();
                    golsTime1 = golsTime1 + golsJogador1;
                    edtGolsJogadorT1.setText("");
                    txtGolsTime1.setText(String.valueOf(golsTime1));
                }
            }
        });

        btnRegistrarT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtGolsJogadorT2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "O campo 'Quantidade Gols Jogador' esta vazio", Toast.LENGTH_LONG).show();
                }
                else {
                    golsJogador2 = Integer.parseInt(edtGolsJogadorT2.getText().toString());
                    JogadorDAO jDao = new JogadorDAO(getApplicationContext());
                    jDao.open();
                    jDao.UpdateGols(golsJogador2, spnJogadoresT2.getSelectedItem().toString(), jr.getTime2().getId());
                    jDao.close();
                    golsTime2 = golsTime2 + golsJogador2;
                    edtGolsJogadorT2.setText("");
                    txtGolsTime2.setText(String.valueOf(golsTime2));
                }
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                g1 = golsTime1;
                g2 = golsTime2;

                if(golsTime1 > golsTime2) {
                    TimeDAO td = new TimeDAO(getApplicationContext());
                    td.open();
                    pontosTime1 = 3 + g1 - g2/2;
                    td.UpdatePontos(pontosTime1, jr.getTime1().getId());
                    pontosTime2 = g2 - g1/2;
                    td.UpdatePontos(pontosTime2, jr.getTime2().getId());
                    td.close();
                }
                else if(golsTime2 > golsTime1){
                    TimeDAO td = new TimeDAO(getApplicationContext());
                    td.open();
                    pontosTime2 = 3 + g2 - g1/2;
                    td.UpdatePontos(pontosTime2, jr.getTime2().getId());
                    pontosTime1 = g1 - g2/2;
                    td.close();
                }
                else
                {
                    TimeDAO td = new TimeDAO(getApplicationContext());
                    td.open();
                    pontosTime1 = 1 + g1 - g2/2;
                    pontosTime2 = 1 + g2 - g1/2;
                    td.UpdatePontos(pontosTime1, jr.getTime1().getId());
                    td.UpdatePontos(pontosTime2, jr.getTime2().getId());
                    td.close();
                }

                JogosRodadaDAO jDao = new JogosRodadaDAO(getApplicationContext());
                jDao.open();
                jDao.UpdateRodada(golsTime1,golsTime2,jr.getId());

                Toast.makeText(getApplicationContext(), "Placar Registrado!", Toast.LENGTH_SHORT).show();

                setResult(1);
                finish();
            }
        });

    }

    public void preencherSpinnerJogadoresTime1(int id)
    {
        JogadorDAO j = new JogadorDAO(getApplicationContext());
        j.open();
        List<JogadoresTime> lista = j.BuscarPorTime(id);
        String[] jogadores = new String[lista.size()];
        int idx = 0;

        for(JogadoresTime jt: lista)
        {
            jogadores[idx++] = jt.getNome();
        }
        j.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, jogadores);

        spnJogadoresT1.setAdapter(adapter);
    }

    public void preencherSpinnerJogadoresTime2(int id)
    {
        JogadorDAO j = new JogadorDAO(getApplicationContext());
        j.open();
        List<JogadoresTime> lista = j.BuscarPorTime(id);
        String[] jogadores = new String[lista.size()];
        int idx = 0;

        for(JogadoresTime jt: lista)
        {
            jogadores[idx++] = jt.getNome();
        }
        j.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, jogadores);

        spnJogadoresT2.setAdapter(adapter);
    }

    private void Binding() {
        txtTime1 = findViewById(R.id.txtTime1TP);
        txtTime2 = findViewById(R.id.txtTime2TP);
        edtGolsJogadorT1 = findViewById(R.id.edtGolsJogadorT1TP);
        edtGolsJogadorT2 = findViewById(R.id.edtGolsJogadorT2TP);
        txtGolsTime1 = findViewById(R.id.txtGolsT1TP);
        txtGolsTime2 = findViewById(R.id.txtGolsT2TP);
        spnJogadoresT1 = findViewById(R.id.spnJogadoresT1TP2);
        spnJogadoresT2 = findViewById(R.id.spnJogadoresT2TP);
        btnRegistrarT1 = findViewById(R.id.btnRegistraPlacarTime1TP);
        btnRegistrarT2 = findViewById(R.id.btnRegistrarPlacarT2TP);
        btnFinalizar = findViewById(R.id.btnFinalizarTP);
    }
}
