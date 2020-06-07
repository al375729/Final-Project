package com.example.leagueoflgendsaccountinformation.ChampionActivity;

import android.util.Log;

import com.android.volley.Response;
import com.example.leagueoflgendsaccountinformation.ChampionActivity.ChampionActivity;
import com.example.leagueoflgendsaccountinformation.Database.Champions;
import com.example.leagueoflgendsaccountinformation.Model;

class ChampionPresenter {

    private static Model modelo;
    private final ChampionActivity view;

    public ChampionPresenter(final ChampionActivity view, final Model model) {
        this.view = view;
        this.modelo = model;


        modelo.getChampionDB(view.getName(), new Response.Listener<Champions>() {
            @Override
            public void onResponse(Champions response) {
                view.setText(response);
            }
        });
    }




}
