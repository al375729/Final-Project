package com.example.leagueoflgendsaccountinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Context context;
    private List<Match> matches;

    public Adapter(Context context, List<Match> matches){
        this.context=context;
        this.matches=matches;
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Match getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView != null) view = convertView;
        else view= LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);

        TextView campeon = view.findViewById(R.id.championFill);
        campeon.setText(getItem(position).championName);

        TextView kills = view.findViewById(R.id.killsFill);
        kills.setText(String.valueOf(getItem(position).kills));

        TextView deaths = view.findViewById(R.id.deathsFill);
        deaths.setText(String.valueOf(getItem(position).deaths));

        TextView assists = view.findViewById(R.id.assistsFill);
        assists.setText(String.valueOf(getItem(position).assists));

        TextView win = view.findViewById(R.id.resultFill);
        if(getItem(position).win.equals("true")) win.setText("Won");
        else win.setText("Lost");
        return view;
    }
}
