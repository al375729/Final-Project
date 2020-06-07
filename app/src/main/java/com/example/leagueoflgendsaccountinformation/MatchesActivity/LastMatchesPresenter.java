package com.example.leagueoflgendsaccountinformation.MatchesActivity;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.leagueoflgendsaccountinformation.Database.Champions;
import com.example.leagueoflgendsaccountinformation.Classes.Match;
import com.example.leagueoflgendsaccountinformation.Model;
import com.example.leagueoflgendsaccountinformation.Classes.Player;

import java.util.ArrayList;
import java.util.List;

class LastMatchesPresenter {
    private static Model modelo;
    private final MatchInfoActivity view;
    private Player jugador;
    private List<Bitmap> lista = new ArrayList<Bitmap>();

    public LastMatchesPresenter(final MatchInfoActivity view, final Model model) {
        this.view = view;
        this.modelo = model;
        jugador = view.getJugador();

        final List<Match> matches = new ArrayList<Match>();
        model.getMatches(jugador.accountId, new Response.Listener<List<Long>>() {
            @Override
            public void onResponse(List<Long> response) {

                if(response.size() > 10){
                    getMatchesInfo(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.showToast("ConnectionError");
            }
        });
    }

    private void getMatchesInfo(List<Long> response) {
        final List<Match> matches = new ArrayList<Match>();
        for (int i = 0; i <11 ; i++) {
            modelo.infoMatch(response.get(i),jugador.nombre, new Response.Listener<Match>() {
                @Override
                public void onResponse(final Match response) {
                    matches.add(response);



                    modelo.campeonesBaseDeDatos(new Response.Listener<Champions>() {
                        @Override
                        public void onResponse(Champions respuesta) {
                            response.setChampionName(respuesta.name);
                            if(matches.size()>10){
                                view.llenarVista(matches);
                                view.hideProgressBar();
                                view.showAll();
                            }

                        }
                        }, response.championId);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.showToast("ConnectionError");
                }
            });
        }
    }

    public void imagenesCampeones(final List<Integer> ids, final Match match) {
        final List<String> nombres= new ArrayList<String>();
        final List<Champions> campeones= new ArrayList<Champions>();

        for (final Integer i: ids) {
            modelo.campeonesBaseDeDatos(new Response.Listener<Champions>() {
                @Override
                public void onResponse(Champions response) {
                    campeones.add(response);
                    nombres.add(response.searchName);
                    if(nombres.size()==10) obtenerImgs(nombres,match,ids,campeones);

                }
            },i);
        }
    }

    private void obtenerImgs(final List<String> nombres, final Match match, final List<Integer> ids, final List<Champions> campeones) {
        final List<Bitmap> imgs =new ArrayList<Bitmap>();
        final List<String> nombresOrd =new ArrayList<String>();
        for (final String s:nombres) {
            modelo.getMiniatura(s, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    nombresOrd.add(s);
                    imgs.add(response);
                    if(imgs.size()==10) {
                       view.openDialog(match,imgs,ids,campeones,nombresOrd);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.showToast("ConnectionError");
                }
            });

        }
    }


    public List<Bitmap> getLista() {
        return lista;
    }

    public void setLista(List<Bitmap> lista) {
        this.lista = lista;
    }
}
