package com.example.diccionariobroranterraba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class RecetarioActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recetario);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recetario);
        setContentView(R.layout.activity_recetario);

        r1 = (CardView) findViewById(R.id.receta1);
        r2 = (CardView) findViewById(R.id.receta2);

        r1.setOnClickListener((View.OnClickListener) this);
        r2.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick (View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.receta1: i = new Intent(this, Receta1Activity.class); startActivity(i); break;
            case R.id.receta2: i = new Intent(this, Receta2Activity.class); startActivity(i); break;
        }
    }

}