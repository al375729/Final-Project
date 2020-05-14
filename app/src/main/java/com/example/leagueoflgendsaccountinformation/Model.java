package com.example.leagueoflgendsaccountinformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import androidx.room.Room;

import com.android.volley.Response;
import com.example.leagueoflgendsaccountinformation.Classes.Match;
import com.example.leagueoflgendsaccountinformation.Classes.Player;
import com.example.leagueoflgendsaccountinformation.Database.Champions;
import com.example.leagueoflgendsaccountinformation.Database.ChampionsDao;
import com.example.leagueoflgendsaccountinformation.Database.ChampionsDatabase;

import java.util.List;

public class Model {

    private static Model instance;
    private static ApiAccess dataAccess;
    private static ChampionsDao dao;
    private final ChampionsDatabase database;

    private Model(final Context context) {

        database = Room.databaseBuilder(context, ChampionsDatabase.class, "campeones").build();
        dao = database.getChampionsDao();
        dataAccess = new ApiAccess(context);

    }

    public static synchronized Model getInstance(Context context) {
        if (instance == null) instance = new Model(context);
        return instance;

    }


    public void userApi(String name, Response.Listener<Player> listener, Response.ErrorListener errorListener) {
        dataAccess.getPlayer(name,listener,errorListener);
    }

    public void iconsApi(int id, Response.Listener<Bitmap> listener , Response.ErrorListener errorListener){
        dataAccess.getIcons(id,listener,errorListener);
    }

    public void campeonesApi(Response.Listener<List<Champions>> listener, Response.ErrorListener errorListener) {
        dataAccess.getChampions(listener,errorListener);
    }

    public void getSplashArt(String name, Response.Listener<Bitmap> listener, Response.ErrorListener errorListener) {
        dataAccess.getSplashArt(name,listener,errorListener);
    }

    public void campeonesBaseDeDatos(Response.Listener<Champions> listener,Integer id) {
        new extraerCampeonPrueba(listener,id).execute();
    }

    public void insertarCampeonesBD(List<Champions> campeones) {
        new insertarCampeonesEnLaBaseDeDatos(campeones).execute();
    }

    public void getChampionDB(String name, Response.Listener<Champions> championsListener) {
        new extraerCampeonNombre(name,championsListener).execute();
    }

    public void getTopMaestries(String id, Response.Listener<List<Integer>> listener, Response.ErrorListener errorListener) {
        dataAccess.getMaestries(id,listener,errorListener);
    }

    public void getMatches(String accountId, Response.Listener<List<Long>> listListener, Response.ErrorListener errorListener) {
        dataAccess.getMatches(accountId,listListener,errorListener);
    }

    public void infoMatch(Long integer, String nombre, Response.Listener<Match> matchListener, Response.ErrorListener errorListener) {
        dataAccess.getInfoMatch(integer,nombre,matchListener,errorListener);
    }

    public void getMiniatura(String s, Response.Listener<Bitmap> listener, Response.ErrorListener errorListener) {
        dataAccess.getMiniatura(s,listener,errorListener);
    }




    private static class insertarCampeonesEnLaBaseDeDatos extends AsyncTask<List<Champions>, Void, Void> {
        private List<Champions> campeones;

        private insertarCampeonesEnLaBaseDeDatos(List<Champions> campeones) {
            this.campeones = campeones;
        }
        @Override
        protected Void doInBackground(List<Champions>... lists) {
            dao.insertarCampeones(campeones);
            return null;
        }
    }

    private static class extraerCampeonPrueba extends AsyncTask<Void, Void, Champions> {
    private final int id;
        private Response.Listener<Champions> listener;

        private extraerCampeonPrueba(Response.Listener<Champions> listener,int id) {
            this.id=id;
            this.listener = listener;
        }

        @Override
        protected Champions doInBackground(Void... voids) {
            return dao.getChampionById(id);
        }

        @Override
        protected void onPostExecute(Champions champion) {
            listener.onResponse(champion);
        }

    }


    private static class extraerCampeonNombre extends AsyncTask<Void, Void, Champions> {
        private final String name;
        private Response.Listener<Champions> listener;

        private extraerCampeonNombre(String name,Response.Listener<Champions> listener) {
            this.name=name;
            this.listener = listener;
        }

        @Override
        protected Champions doInBackground(Void... voids) {
            return dao.getChampionByString(name);
        }

        @Override
        protected void onPostExecute(Champions champion) {
            listener.onResponse(champion);
        }

    }
}
