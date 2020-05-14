package com.example.leagueoflgendsaccountinformation.MatchesActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.leagueoflgendsaccountinformation.ChampionActivity.ChampionActivity;
import com.example.leagueoflgendsaccountinformation.Database.Champions;
import com.example.leagueoflgendsaccountinformation.Classes.Match;
import com.example.leagueoflgendsaccountinformation.MainActivity.MainActivity;
import com.example.leagueoflgendsaccountinformation.Model;
import com.example.leagueoflgendsaccountinformation.Classes.Player;
import com.example.leagueoflgendsaccountinformation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchInfoActivity extends AppCompatActivity {

    private Player jugador;
    private LastMatchesPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        Intent intent = getIntent();
        jugador = (Player)intent.getSerializableExtra("id");
        Model model = Model.getInstance(this);
         presenter = new LastMatchesPresenter(this,model);
    }

    public Player getJugador() {
        return jugador;
    }

    public void llenarVista(final List<Match> matches) {
    Adapter CustomAdapter= new Adapter (this,matches);
    View footerView = getLayoutInflater().inflate(R.layout.header,null);

        final ListView listView= findViewById(R.id.listView);
        listView.addHeaderView(footerView,"Header",false);
        listView.setAdapter(CustomAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imagenes(position-1,matches);
            }
        });
    }

    private void imagenes(int id, List<Match> matches) {
        final Match match = matches.get(id);

        List<Integer> ids = Arrays.asList(match.J1Campeon,match.J2Campeon,match.J3Campeon,match.J4Campeon,match.J5Campeon,match.J6Campeon,match.J7Campeon,match.J8Campeon,match.J9Campeon,match.J10Campeon);
        presenter.imagenesCampeones(ids,match);
    }


    public void openDialog(Match match, List<Bitmap> imagenes, List<Integer> ids, final List<Champions> campeones, final List<String> nombresOrd) {


        List<Bitmap> imgs = new ArrayList<Bitmap>(imagenes);


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchInfoActivity.this);
        View dialog = getLayoutInflater().inflate(R.layout.dialog,null);
        mBuilder.setView(dialog);
        final AlertDialog dialogo =mBuilder.create();
        dialogo.show();

        TextView towers1 = dialog.findViewById(R.id.towers1);
        towers1.setText(String.valueOf(match.team1Towers));

        TextView towers2 = dialog.findViewById(R.id.towers2);
        towers2.setText(String.valueOf(match.team2Towers));

        TextView inhib = dialog.findViewById(R.id.inhibitors1);
        inhib.setText(String.valueOf(match.team1Inhibitors));

        TextView inhib2 = dialog.findViewById(R.id.inhibitors2);
        inhib2.setText(String.valueOf(match.team2Inhibitors));

        TextView baron = dialog.findViewById(R.id.barons1);
        baron.setText(String.valueOf(match.team1Baron));

        TextView baron2 = dialog.findViewById(R.id.baron2);
        baron2.setText(String.valueOf(match.team2Baron));

        TextView herald = dialog.findViewById(R.id.herald1);
        herald.setText(String.valueOf(match.team1Herald));

        TextView herald2 = dialog.findViewById(R.id.herald2);
        herald2.setText(String.valueOf(match.team2Herald));

        TextView dragon = dialog.findViewById(R.id.dragon1);
        dragon.setText(String.valueOf(match.team1Dragon));

        TextView dragon2 = dialog.findViewById(R.id.dragon2);
        dragon2.setText(String.valueOf(match.team2Dragon));

        ImageView j1 = dialog.findViewById(R.id.c1);
        j1.setImageBitmap(imagenes.get(0));
        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(0));
            }
        });

        ImageView j2 = dialog.findViewById(R.id.c2);
        j2.setImageBitmap(imagenes.get(1));
        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(1));
            }
        });

        ImageView j3 = dialog.findViewById(R.id.c3);
        j3.setImageBitmap(imagenes.get(2));
        j3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(2));
            }
        });

        ImageView j4 = dialog.findViewById(R.id.c4);
        j4.setImageBitmap(imagenes.get(3));
        j4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(3));
            }
        });

        ImageView j5 = dialog.findViewById(R.id.c5);
        j5.setImageBitmap(imagenes.get(4));
        j5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(4));
            }
        });

        ImageView j6 = dialog.findViewById(R.id.c6);
        j6.setImageBitmap(imagenes.get(5));
        j6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(5));
            }
        });

        ImageView j7 = dialog.findViewById(R.id.c7);
        j7.setImageBitmap(imagenes.get(6));
        j7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(6));
            }
        });

        ImageView j8 = dialog.findViewById(R.id.c8);
        j8.setImageBitmap(imagenes.get(7));
        j8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(7));
            }
        });

        ImageView j9 = dialog.findViewById(R.id.c9);
        j9.setImageBitmap(imagenes.get(8));
        j9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(8));
            }
        });

        ImageView j10 = dialog.findViewById(R.id.c10);
        j10.setImageBitmap(imagenes.get(9));
        j10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campeonActivity(nombresOrd.get(9));
            }
        });

    }

    public void campeonActivity(String campeon){
        Intent intent = new Intent(MatchInfoActivity.this , ChampionActivity.class);
        intent.putExtra("id",campeon);
        startActivity(intent);
    }

    public void showToast(String name) {
        Toast.makeText(MatchInfoActivity.this,name,Toast.LENGTH_SHORT).show();
    }

    public void hideProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar2) ;
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showAll() {
        ListView listView = findViewById(R.id.listView);
        listView.setVisibility(View.VISIBLE);
    }
}
