package com.example.leagueoflgendsaccountinformation;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.leagueoflgendsaccountinformation.Database.Champions;

import java.util.ArrayList;
import java.util.List;

public class AccountPresenter {

    private static Model modelo;
    private final AccountActivity view;

    public AccountPresenter(final AccountActivity view, final Model model) {
        this.view = view;
        this.modelo = model;

        model.iconsApi(view.getJugadorIcon(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                view.DisplayText();
                view.DisplayImage(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.showToast("error");
            }
        });

        Player jugador = view.getJugador();
        modelo.getTopMaestries(jugador.id, new Response.Listener<List<Integer>>() {
            @Override
            public void onResponse(List<Integer> response) {

               pedirSplashArts(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.showToast("maestrias error");
            }
        });

    }

    private void pedirSplashArts(List<Integer> response) {
        final List<String> nombres = new ArrayList<String>();

        /**/
        for (int i = 0; i <3 ; i++) {
            modelo.campeonesBaseDeDatos(new Response.Listener<Champions>() {
                @Override
                public void onResponse(Champions response) {

                    nombres.add(response.name);
                    if(nombres.size()==3)listaNombres(nombres);
                }
            }, response.get(i));
        }
        }



    private void listaNombres(List<String> nombres) {
        final List<Bitmap> arte =new ArrayList<Bitmap>();
        for (int i = 0; i <3 ; i++) {
            final int finalI = i;
            modelo.getSplashArt(nombres.get(i), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    arte.add(response);
                    if(arte.size()==3) view.DisplayMaestrias(arte);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.showToast(" SHYVANA ERROR");
                }
            });
        }
    }


}
