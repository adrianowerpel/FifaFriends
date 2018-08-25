package com.example.patricia.fifafriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.patricia.fifafriends.Model.JogadoresTime;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.R;

import java.util.List;

/**
 * Created by patricia on 25/04/2018.
 */

public class ArtilheiroAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private final List<JogadoresTime> jogadores;

    public ArtilheiroAdapter(Context context, List<JogadoresTime> jogadores)
    {
        this.jogadores = jogadores;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtNome,txtTime,txtGols;

        View view = layout.inflate(R.layout.activity_artilheiro__adapter,parent,false);

        txtNome = view.findViewById(R.id.txtNomeJogadorTAA);
        txtTime = view.findViewById(R.id.txtTimeJogadorTAA);
        txtGols = view.findViewById(R.id.txtGolsJogadorTAA);

        JogadoresTime j = (JogadoresTime) getItem(position);

        txtNome.setText(j.getNome());
        txtTime.setText(j.getTime().getTime());
        txtGols.setText(String.valueOf(j.getGols()));

        return view;
    }
}
