package com.example.patricia.fifafriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.patricia.fifafriends.Model.Amigo;
import com.example.patricia.fifafriends.Model.JogosRodada;
import com.example.patricia.fifafriends.Model.Tabela;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.R;

import java.util.List;

/**
 * Created by aluno on 24/04/2018.
 */

public class TabelaAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private final List<Amigo> amigos;

    public TabelaAdapter(Context context, List<Amigo> amigos)
    {
        this.amigos = amigos;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return amigos.size();
    }

    @Override
    public Object getItem(int position) {
        return amigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtAmigo,txtTime,txtPontos;

        View view = layout.inflate(R.layout.activity_tabela_adapter,parent,false);

        txtAmigo = view.findViewById(R.id.txtNomeAmigoTTA);
        txtTime = view.findViewById(R.id.txtTimeTTA);
        txtPontos = view.findViewById(R.id.txtPontosTTA);

        Amigo a = (Amigo) getItem(position);

        txtAmigo.setText(a.getNome());
        txtTime.setText(a.getTime().getTime());
        txtPontos.setText(String.valueOf(a.getTime().getPontos()));

        return view;
    }
}
