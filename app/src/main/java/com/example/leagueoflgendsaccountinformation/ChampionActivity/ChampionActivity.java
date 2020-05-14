package com.example.leagueoflgendsaccountinformation.ChampionActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.leagueoflgendsaccountinformation.Database.Champions;
import com.example.leagueoflgendsaccountinformation.Model;
import com.example.leagueoflgendsaccountinformation.R;

public class ChampionActivity extends AppCompatActivity {

    private String campeon;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        Intent intent = getIntent();
        campeon = (String) intent.getSerializableExtra("id");
        Model model = Model.getInstance(this);
        final ChampionPresenter presenter = new ChampionPresenter(this,model);



    }

    public String getName() {
        return  campeon;
    }

    public void setText(Champions champion){
        TextView name = findViewById(R.id.name);
        name.setText(champion.name);

        TextView titulo = findViewById(R.id.titulo);
        titulo.setText(champion.title);

        TextView description = findViewById(R.id.blurb);
        description.setText(champion.blurb);
    }
}
