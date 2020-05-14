package com.example.leagueoflgendsaccountinformation;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.leagueoflgendsaccountinformation.Database.Champions;

import java.util.ArrayList;
import java.util.List;

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
