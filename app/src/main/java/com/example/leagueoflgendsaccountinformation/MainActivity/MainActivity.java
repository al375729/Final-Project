package com.example.leagueoflgendsaccountinformation.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leagueoflgendsaccountinformation.AccountActivity.AccountActivity;
import com.example.leagueoflgendsaccountinformation.Model;
import com.example.leagueoflgendsaccountinformation.Classes.Player;
import com.example.leagueoflgendsaccountinformation.R;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText nameInput;

    Button sumbitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model modelo = Model.getInstance(this);
        final MainPresenter presenter = new MainPresenter(this,modelo);


        nameInput = findViewById(R.id.editText2);
        sumbitButton = findViewById(R.id.button);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();

                presenter.getUser(name);
            }
        });

    }

    public void showToast(String name) {
        Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
    }

    public void infoCuenta(Player response) {
        Intent intent = new Intent(MainActivity.this , AccountActivity.class);
        intent.putExtra("id",response);
        startActivity(intent);
    }
}
