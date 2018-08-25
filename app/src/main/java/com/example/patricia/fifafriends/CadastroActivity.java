package com.example.patricia.fifafriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.patricia.fifafriends.DAO.AmigoDAO;
import com.example.patricia.fifafriends.DAO.TimeDAO;
import com.example.patricia.fifafriends.Model.Amigo;
import com.example.patricia.fifafriends.Model.Times;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    EditText edtNome;
    Spinner spnTimes;
    Button btnSair,btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Binding();

        preencherSpinnerTimes();

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AmigoDAO ad = new AmigoDAO(getApplicationContext());
                ad.open();

                TimeDAO t = new TimeDAO(getApplicationContext());
                Times time = new Times();
                t.open();
                time = t.BuscarPeloNome(spnTimes.getSelectedItem().toString());

                Amigo amigo = new Amigo(0,edtNome.getText().toString(),time);
                AmigoDAO a = new AmigoDAO(getApplicationContext());
                a.open();
                if(amigo.getNome().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Campo nome vazio", Toast.LENGTH_SHORT).show();

                }else
                {
                    t.SetarTimeAtivo(time.getId());
                    a.gravar(amigo);
                    t.close();
                    a.close();

                    Toast.makeText(getApplicationContext(), "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                }

            }
        });

    }

    private void preencherSpinnerTimes() {

        TimeDAO t = new TimeDAO(getApplicationContext());
        t.open();
        List<Times> lista = t.BuscarTimesInativos();
        String[] times = new String[lista.size()];
        int idx = 0;

        for(Times g: lista)
        {
            times[idx++] = g.getTime();
        }
        t.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, times);

        spnTimes.setAdapter(adapter);
    }

    private void Binding() {
        edtNome = findViewById(R.id.edtNomeTCJ);
        spnTimes = findViewById(R.id.spnTimesTCJ);
        btnSalvar = findViewById(R.id.btnSalvarTCJ);
        btnSair = findViewById(R.id.btnSairTCJ);
    }
}
