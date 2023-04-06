package com.example.diccionariobroranterraba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {
    private CardView C1, C2, C3, C4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        C1 = (CardView) findViewById(R.id.Diccionario);
        C2 = (CardView) findViewById(R.id.Recetario);
        C3 = (CardView) findViewById(R.id.Informacion);
        C4 = (CardView) findViewById(R.id.Creditos);

        C1.setOnClickListener((View.OnClickListener) this);
        C2.setOnClickListener((View.OnClickListener) this);
        C3.setOnClickListener((View.OnClickListener) this);
        C4.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick (View v) {
        Intent i;
        switch (v.getId()){
            case R.id.Diccionario: i = new Intent(this, DiccionarioActivity.class); startActivity(i); break;
            case R.id.Recetario: i = new Intent(this, RecetarioActivity.class); startActivity(i); break;
            case R.id.Informacion: i = new Intent(this, InformacionActivity.class); startActivity(i); break;
            case R.id.Creditos: i = new Intent(this, CreditosActivity.class); startActivity(i); break;
        }
    }


}