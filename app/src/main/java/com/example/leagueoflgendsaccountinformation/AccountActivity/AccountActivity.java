package com.example.leagueoflgendsaccountinformation.AccountActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leagueoflgendsaccountinformation.MatchesActivity.MatchInfoActivity;
import com.example.leagueoflgendsaccountinformation.Model;
import com.example.leagueoflgendsaccountinformation.Classes.Player;
import com.example.leagueoflgendsaccountinformation.R;

import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private Player jugador;
    private TextView nombre ;
    private TextView nivel ;
    private ImageView icono ;
    private ImageView maestria1 ;
    private ImageView maestria2 ;
    private ImageView maestria3 ;
    private Button boton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);
        Intent intent = getIntent();
        jugador = (Player)intent.getSerializableExtra("id");
        Model model = Model.getInstance(this);
        final AccountPresenter presenter = new AccountPresenter(this,model);

         nombre = findViewById(R.id.nombre);
         nivel = findViewById(R.id.level);
         icono = findViewById(R.id.icon);
         maestria1 = findViewById(R.id.maestria1);
         maestria2 = findViewById(R.id.maestria2);
         maestria3 = findViewById(R.id.maestria3);
         boton = findViewById(R.id.button);

         boton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(AccountActivity.this , MatchInfoActivity.class);
                 intent.putExtra("id",jugador);
                 startActivity(intent);
             }
         });

        nombre.setVisibility(View.INVISIBLE);
        nivel.setVisibility(View.INVISIBLE);
        icono.setVisibility(View.INVISIBLE);
        maestria1.setVisibility(View.INVISIBLE);
        maestria2.setVisibility(View.INVISIBLE);
        maestria3.setVisibility(View.INVISIBLE);
        boton.setVisibility(View.INVISIBLE);
    }

    public int getJugadorIcon() {
        return jugador.iconId;
    }

    public void showToast(String name) {
        Toast.makeText(AccountActivity.this,name,Toast.LENGTH_SHORT).show();
    }

    public void DisplayText(){

        nombre.setText(jugador.nombre);
        nivel.setText(Integer.toString(jugador.level));

    }

    public void DisplayImage(Bitmap img){
        icono.setImageBitmap(img);
    }

    public void DisplayMaestrias(List<Bitmap> lista){
        maestria1.setImageBitmap(lista.get(0));
        maestria2.setImageBitmap(lista.get(1));
        maestria3.setImageBitmap(lista.get(2));


    }

    public Player getJugador() {
        return jugador;
    }

    public void hideProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showAll() {
        maestria1.setVisibility(View.VISIBLE);
        maestria2.setVisibility(View.VISIBLE);
        maestria3.setVisibility(View.VISIBLE);
        icono.setVisibility(View.VISIBLE);
        nombre.setVisibility(View.VISIBLE);
        nivel.setVisibility(View.VISIBLE);
        boton.setVisibility(View.VISIBLE);
    }
}
