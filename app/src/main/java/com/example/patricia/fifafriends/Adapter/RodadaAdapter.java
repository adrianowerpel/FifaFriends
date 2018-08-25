package com.example.patricia.fifafriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.patricia.fifafriends.Model.JogosRodada;
import com.example.patricia.fifafriends.Model.Times;
import com.example.patricia.fifafriends.R;

import java.util.List;

/**
 * Created by patricia on 24/04/2018.
 */

public class RodadaAdapter extends BaseAdapter {

    private LayoutInflater layout;
    private final List<JogosRodada> JogosRodada;

    public RodadaAdapter(Context context,List<JogosRodada> JogosRodada)
    {
        this.JogosRodada = JogosRodada;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return JogosRodada.size();
    }

    @Override
    public Object getItem(int position) {
        return JogosRodada.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtTimeEsquerda,txtTimeDireita,txtGolsTimeEsquerda,txtGolsTimeDireita;

        View view = layout.inflate(R.layout.activity_rodada_adapter,parent,false);

        txtGolsTimeEsquerda = view.findViewById(R.id.txtGolsTime1);
        txtTimeEsquerda = view.findViewById(R.id.txtTimeEsquerda);
        txtGolsTimeDireita = view.findViewById(R.id.txtGolsTime2);
        txtTimeDireita = view.findViewById(R.id.txtTimeDireita);

        JogosRodada jr = (JogosRodada) getItem(position);

        txtTimeEsquerda.setText(jr.getTime1().getTime());
        txtGolsTimeEsquerda.setText(String.valueOf(jr.getGolsTime1()));
        txtTimeDireita.setText(jr.getTime2().getTime());
        txtGolsTimeDireita.setText(String.valueOf(jr.getGolsTime2()));

        return view;
    }
}
