package com.example.leagueoflgendsaccountinformation;

import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.leagueoflgendsaccountinformation.Database.Champions;

import java.util.List;

public class MainPresenter {

    private static Model modelo;
    private final MainActivity view;

    public MainPresenter(final MainActivity view, final Model model) {
        this.view = view;
        MainPresenter.modelo = model;

        modelo.campeonesBaseDeDatos(new Response.Listener<Champions>() {
            @Override
            public void onResponse(Champions response) {
                if(response ==null){
                    Log.d("myTag", "BD VACIA");
                    pedirCampeonesApi();
                }
                else Log.d("myTag", response.name);
            }
        },102);
    }

    private void pedirCampeonesApi() {
        modelo.campeonesApi(new Response.Listener<List<Champions>>() {
            @Override
            public void onResponse(List<Champions> response) {
                int size = response.size();
                Log.d("myTag", response.get(size-1).name);
                Log.d("myTag", "RESPONDER API");
                modelo.insertarCampeonesBD(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.showToast("ConnectionError");
            }
        });
    }

    public void getUser(String name) {


        modelo.userApi(name, new Response.Listener<Player>() {

            @Override
            public void onResponse(Player response) {
               view.infoCuenta(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error instanceof NetworkError) view.showToast("Connection error");
                else  view.showToast("Nombre no encontrado");

            }
        });
    }

}
