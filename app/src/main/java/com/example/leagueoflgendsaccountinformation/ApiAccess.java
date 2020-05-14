package com.example.leagueoflgendsaccountinformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.leagueoflgendsaccountinformation.Database.Champions;
/*
import com.example.ujisoccer.Database.League;
import com.example.ujisoccer.Database.Team;
import com.example.ujisoccer.Database.TeamInStanding;
*/
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class ApiAccess {

    private static final String token = "X-Riot-Token";
    private static final String miClave = "RGAPI-39468fdf-1cb5-4ff3-b08a-3079caa20df8";

    private static final String urlBase = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/ahorasimentendo";
    private static final String urlCompeticiones = urlBase + "competitions?plan=TIER_ONE";


    private static final String listaDeCompeticiones = "competitions";
    private static final String idCompeticion = "id";
    private static final String nombreCompeticion = "name";
    private static final String areaDeCompeticion = "area";
    private static final String nombreArea = "name";
    private static final String temporada = "currentSeason";
    private static final String inicioTemporada = "startDate";
    private static final String finTemporada = "endDate";

    private static final String clasificacion = "standings";
    private static final String tipoClasificacion = "type";
    private static final String valorTipo = "TOTAL";
    private static final String tabla = "table";
    private static final String posicion = "position";
    private static final String idEquipo = "id";
    private static final String nombreEquipo = "name";
    private static final String partidosJugados = "playedGames";
    private static final String partidosGanados = "won";
    private static final String partidosEmpatados = "draw";
    private static final String partidosPerdidos = "lost";
    private static final String puntos = "points";
    private static final String golesFavor = "goalsFor";
    private static final String golesContra = "goalsAgainst";

    private static final String listaEqupios = "teams";
    private static final String nombreCortoEquipo = "shortName";
    private static final String web = "website";
    private static final String colores = "clubColors";
    private static final String fundado = "founded";
    private static final String venue = "venue";






/*
    private RequestQueue requestQueue;

    public ApiAccess(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }



    private void sendRequest(String url, Response.Listener<JSONObject> listener , Response.ErrorListener errorListener){
        Log.d("myTag", "SEND REQUEST");
        JsonRequest request = new JsonObjectRequest(Request.Method.GET, url,null,listener,errorListener ){
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> headers = new HashMap<>();
                headers.put(token,miClave);
                return headers;
            }

        };
        requestQueue.add(request);
    }



    public void getPlayer(String name, final Response.Listener<String> stringListener,final Response.ErrorListener errorListener) {
       String url ="https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/ahorasimentendo?api_key=RGAPI-8d9af321-bd0a-43da-9d4f-b008b3ada463";
        //String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/ahorasimentendo";
        sendRequest(url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("myTag", "Parse respuesta");
                        String id = parsePlayer(response);
                        stringListener.onResponse(id);
                    }
                }
                , errorListener);
    }

    private String parsePlayer(JSONObject response) {
        Log.d("myTag", "PARSE");
        String id ="";

        try{

            for(int i = 0; i < response.length();i++){
                id = response.getString("id");

            }
        } catch (JSONException e) {
            return null;
        }
        return id;
    }*/
private RequestQueue requestQueue;

    public ApiAccess(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    private void sendRequest(String url, Response.Listener<JSONObject> listener , Response.ErrorListener errorListener){//Funcion a la que llamaremos para construir y mandar las peticiones

        JsonRequest request = new JsonObjectRequest(Request.Method.GET, url,null,listener,errorListener ){
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> headers = new HashMap<>();
                headers.put(token,miClave);
                return headers;
            }

        };
        requestQueue.add(request);
    }

    private void sendRequestArray(String url, Response.Listener<JSONArray> listener , Response.ErrorListener errorListener){//Funcion a la que llamaremos para construir y mandar las peticiones

        JsonRequest request = new JsonArrayRequest(Request.Method.GET, url,null,listener,errorListener ){
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> headers = new HashMap<>();
                headers.put(token,miClave);
                return headers;
            }

        };
        requestQueue.add(request);
    }

    private void imageRequest(String url, Response.Listener<Bitmap> listener , Response.ErrorListener errorListener,int width,int heigth){
        Log.d("myTag", "IMAGE REQUEST");
        ImageRequest request = new ImageRequest(url,listener,width,heigth,null,null,errorListener){
        };
        requestQueue.add(request);
    }

    private void sendRequestNoHeaders(String url,Response.Listener<JSONObject> listener,Response.ErrorListener errorListener){
        JsonRequest request = new JsonObjectRequest(Request.Method.GET, url,null,listener,errorListener );
        requestQueue.add(request);
    }

    public void getPlayer(String name, final Response.Listener<Player> listener, final Response.ErrorListener errorListener ){//Funcion que le pasa los argumentos a SendRequest para obtener una lista de ligas de la API
        sendRequest("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+name
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("myTag", "Respuesta");
                Player jugador = parsePlayer(response);
                listener.onResponse(jugador);
            }
        }, errorListener);
    }

    public void getInfoMatch(Long integer, final String nombre, final Response.Listener<Match> matchListener, Response.ErrorListener errorListener) {
        sendRequest("https://euw1.api.riotgames.com/lol/match/v4/matches/" + integer, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Match matches = parseMatchInfo(response,nombre);
                matchListener.onResponse(matches);
            }
        },errorListener);
    }



    public void getMatches(String accountId, final Response.Listener<List<Long>> listListener,final Response.ErrorListener errorListener) {
        sendRequest("https://euw1.api.riotgames.com/lol/match/v4/matchlists/by-account/"+accountId
                , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Long> matches = parseMatches(response);
                        listListener.onResponse(matches);
                    }
                }, errorListener);
    }


    public void getMaestries(String id,final Response.Listener<List<Integer>> listener,final Response.ErrorListener errorListener) {
        Log.d("myTag", id);
        sendRequestArray("https://euw1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"+id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Integer> maes = parseMaestries(response);
                listener.onResponse(maes);
            }
        },errorListener);
    }


    public void getIcons(int id, final Response.Listener<Bitmap> listener, final Response.ErrorListener errorListener){
        imageRequest("https://ddragon.leagueoflegends.com/cdn/10.9.1/img/profileicon/"+Integer.toString(id)+".png", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                listener.onResponse(response);
            }
        }, errorListener,300,300);
    }

    public void getMiniatura(String s, final Response.Listener<Bitmap> listener, Response.ErrorListener errorListener) {

        s=s.replaceAll("\\s+","");
        if(s.equals("Cho'Gath")) s="Chogath";
        else if(s.equals("Kai'Sa")) s="Kaisa";
        imageRequest("https://ddragon.leagueoflegends.com/cdn/10.10.3208608/img/champion/"+s+".png", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                listener.onResponse(response);
            }
        }, errorListener,120,120);
    }

    public void getSplashArt(String name, final Response.Listener<Bitmap> listener, final Response.ErrorListener errorListener){
        imageRequest("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/"+name+"_0.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                listener.onResponse(response);
            }
        }, errorListener,308,560);
    }

    public void getChampions(final Response.Listener<List<Champions>> listener,final Response.ErrorListener errorListener) {
        sendRequestNoHeaders("https://ddragon.leagueoflegends.com/cdn/10.9.1/data/en_US/champion.json", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("myTag", "Respuesta");
                List<Champions> campeones = parseChampions(response);
                listener.onResponse(campeones);
            }
        },errorListener);
    }

    private List<Long> parseMatches(JSONObject response) {
        List<Long> lista = new ArrayList<>();
        try{
            JSONArray matches = response.getJSONArray("matches");

            for(int i = 0; i <11;i++){

                JSONObject match = matches.getJSONObject(i);
                Long id = match.getLong("gameId");
                lista.add(id);
            }
        } catch (JSONException e) {
            return null;
        }
        return lista;
    }

    private Match parseMatchInfo(JSONObject response,String nombre) {
        Log.d("myTag", nombre);
        int jugador = 0;
        int kills = 0;
        int deaths = 0;
        int assists = 0;
        String win = null;
        int championId = 0;
        int j1 = 0;
        int j2 = 0;
        int j3 = 0;
        int j4 = 0;
        int j5 = 0;
        int j6 = 0;
        int j7 = 0;
        int j8 = 0;
        int j9 = 0;
        int j10 = 0;
        Match partida = null;
        int team1Towers;
        int team2Towers;
        int team1Inhibitors;
        int team2Inhibitors;
        int team1Baron;
        int team2Baron;
        int team1Herald;
        int team2Herald;
        int team1Dragon;
        int team2Dragon;


        try{
            JSONArray participants = response.getJSONArray("participantIdentities");
            JSONArray participantes = response.getJSONArray("participants");
            for(int i = 0; i <participants.length();i++){
                JSONObject participante = participants.getJSONObject(i);
                int participantId = participante.getInt("participantId");
                JSONObject player = participante.getJSONObject("player");
                String name = player.getString("summonerName");
                if(name.equals(nombre) ) {
                    jugador =participantId;
                    break;
                }
            }

            for(int i = 0; i <participantes.length();i++){
                JSONObject jug = participantes.getJSONObject(i);
                int id = jug.getInt("participantId");
                if(id== jugador){
                    championId=jug.getInt("championId");
                    JSONObject stats = jug.getJSONObject("stats");
                     win = stats.getString("win");
                     kills = stats.getInt("kills");
                     deaths = stats.getInt("deaths");
                     assists = stats.getInt("assists");
                }

                if (id==1){
                     j1=jug.getInt("championId");
                }
                else if (id==2){
                     j2=jug.getInt("championId");
                }
                else if (id==3){
                     j3=jug.getInt("championId");
                }
                else if (id==4){
                     j4=jug.getInt("championId");
                }
                else if (id==5){
                     j5=jug.getInt("championId");
                }
                else if (id==6){
                     j6=jug.getInt("championId");
                }
                else if (id==7){
                     j7=jug.getInt("championId");
                }
                else if (id==8){
                     j8=jug.getInt("championId");
                }
                else if (id==9){
                     j9=jug.getInt("championId");
                }
                else if (id==10){
                     j10=jug.getInt("championId");
                }

            }
            JSONArray teams = response.getJSONArray("teams");
            JSONObject team1 = teams.getJSONObject(0);
            JSONObject team2 = teams.getJSONObject(1);

            team1Towers = team1.getInt("towerKills");
            team1Inhibitors = team1.getInt("inhibitorKills");
            team1Baron = team1.getInt("baronKills");
            team1Herald = team1.getInt("riftHeraldKills");
            team1Dragon = team1.getInt("dragonKills");

            team2Towers = team2.getInt("towerKills");
            team2Inhibitors = team2.getInt("inhibitorKills");
            team2Baron = team2.getInt("baronKills");
            team2Herald = team2.getInt("riftHeraldKills");
            team2Dragon = team2.getInt("dragonKills");


             partida = new Match(kills,deaths,assists,win,championId,"nada",j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,team1Towers,team1Inhibitors,team1Baron,team1Herald,team1Dragon,
                     team2Towers,team2Inhibitors,team2Baron,team2Herald,team2Dragon);

        } catch (JSONException e) {
            return null;
        }
        return partida;
    }

    private List<Champions> parseChampions(JSONObject response) {
        List<Champions> lista = new ArrayList<>();
        try{
            JSONObject campeones = response.getJSONObject("data");

            Iterator keys = campeones.keys();
            while (keys.hasNext()) {
                Object key = keys.next();
                JSONObject campeon = campeones.getJSONObject((String) key);
                String nombre = campeon.getString("name");
                int id = campeon.getInt("key");
                String title = campeon.getString("title");
                String blurb = campeon.getString("blurb");
                lista.add(new Champions(id,nombre,title,blurb));
            }
        } catch (JSONException e) {
            return null;
        }
        return lista;
    }

    private List<Integer> parseMaestries(JSONArray response) {
        List<Integer> lista = new ArrayList<>();
        try{
            for(int i = 0; i <3;i++){

                JSONObject campeon = response.getJSONObject(i);
                int id = campeon.getInt("championId");
                lista.add(id);
            }
        } catch (JSONException e) {
            return null;
        }
        return lista;

    }




    private Player parsePlayer(JSONObject response) {
            Player jugador = null;
        try{

            for(int i = 0; i < response.length();i++){
                String id = response.getString("id");
                String name = response.getString("name");
                String accountId = response.getString("accountId");
                int iconId = response.getInt("profileIconId");
                int level = response.getInt("summonerLevel");

                 jugador = new Player(name,id,accountId,iconId,level);
            }
           
        } catch (JSONException e) {
            return null;
        }
        return jugador;
    }


}
