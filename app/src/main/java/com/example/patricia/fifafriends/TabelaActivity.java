package com.example.patricia.fifafriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.patricia.fifafriends.Adapter.TabelaAdapter;
import com.example.patricia.fifafriends.DAO.AmigoDAO;
import com.example.patricia.fifafriends.DAO.TimeDAO;
import com.example.patricia.fifafriends.Model.Amigo;
import com.example.patricia.fifafriends.Model.Tabela;
import com.example.patricia.fifafriends.Model.Times;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TabelaActivity extends AppCompatActivity {

    ListView lista;
    Amigo amigo;
    Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela);

        Binding();

        final ArrayList<Amigo> list;

        list = BuscarAmigosTimes();

        TabelaAdapter ta = new TabelaAdapter(getApplicationContext(),list);
        lista.setAdapter(ta);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

    }

    private ArrayList<Amigo> BuscarAmigosTimes() {

        AmigoDAO ad = new AmigoDAO(getApplicationContext());
        ad.open();
        ArrayList<Amigo> list = new ArrayList<>();
        list = ad.BuscarTodosAmigos();
        ad.close();

        return list;
    }

    private void Binding() {
        lista = findViewById(R.id.ListTT);
        btnSair = findViewById(R.id.btnSairTT);
    }
}
