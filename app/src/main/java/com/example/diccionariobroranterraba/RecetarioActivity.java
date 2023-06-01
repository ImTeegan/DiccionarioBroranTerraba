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
        r3 = (CardView) findViewById(R.id.receta3);
        r4 = (CardView) findViewById(R.id.receta4);
        r5 = (CardView) findViewById(R.id.receta5);
        r6 = (CardView) findViewById(R.id.receta6);
        r7 = (CardView) findViewById(R.id.receta7);
        r8 = (CardView) findViewById(R.id.receta8);
        r9 = (CardView) findViewById(R.id.receta9);
        r10 = (CardView) findViewById(R.id.receta10);
        r11 = (CardView) findViewById(R.id.receta11);
        r12 = (CardView) findViewById(R.id.receta12);
        r13 = (CardView) findViewById(R.id.receta13);
        r14 = (CardView) findViewById(R.id.receta14);
        r15 = (CardView) findViewById(R.id.receta15);
        r16 = (CardView) findViewById(R.id.receta16);
        r17 = (CardView) findViewById(R.id.receta17);
        r18 = (CardView) findViewById(R.id.receta18);
        r19 = (CardView) findViewById(R.id.receta19);
        r20 = (CardView) findViewById(R.id.receta20);


        r1.setOnClickListener((View.OnClickListener) this);
        r2.setOnClickListener((View.OnClickListener) this);
        r3.setOnClickListener((View.OnClickListener) this);
        r4.setOnClickListener((View.OnClickListener) this);
        r5.setOnClickListener((View.OnClickListener) this);
        r6.setOnClickListener((View.OnClickListener) this);
        r7.setOnClickListener((View.OnClickListener) this);
        r8.setOnClickListener((View.OnClickListener) this);
        r9.setOnClickListener((View.OnClickListener) this);
        r10.setOnClickListener((View.OnClickListener) this);
        r11.setOnClickListener((View.OnClickListener) this);
        r12.setOnClickListener((View.OnClickListener) this);
        r13.setOnClickListener((View.OnClickListener) this);
        r14.setOnClickListener((View.OnClickListener) this);
        r15.setOnClickListener((View.OnClickListener) this);
        r16.setOnClickListener((View.OnClickListener) this);
        r17.setOnClickListener((View.OnClickListener) this);
        r18.setOnClickListener((View.OnClickListener) this);
        r19.setOnClickListener((View.OnClickListener) this);
        r20.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick (View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.receta1: i = new Intent(this, Receta1Activity.class); startActivity(i); break;
            case R.id.receta2: i = new Intent(this, Receta2Activity.class); startActivity(i); break;
            case R.id.receta3: i = new Intent(this, Receta3Activity.class); startActivity(i); break;
            case R.id.receta4: i = new Intent(this, Receta4Activity.class); startActivity(i); break;
            case R.id.receta5: i = new Intent(this, Receta5Activity.class); startActivity(i); break;
            case R.id.receta6: i = new Intent(this, Receta6Activity.class); startActivity(i); break;
            case R.id.receta7: i = new Intent(this, Receta7Activity.class); startActivity(i); break;
            case R.id.receta8: i = new Intent(this, Receta8Activity.class); startActivity(i); break;
            case R.id.receta9: i = new Intent(this, Receta9Activity.class); startActivity(i); break;
            case R.id.receta10: i = new Intent(this, Receta10Activity.class); startActivity(i); break;
            case R.id.receta11: i = new Intent(this, Receta11Activity.class); startActivity(i); break;
            case R.id.receta12: i = new Intent(this, Receta12Activity.class); startActivity(i); break;
            case R.id.receta13: i = new Intent(this, Receta13Activity.class); startActivity(i); break;
            case R.id.receta14: i = new Intent(this, Receta14Activity.class); startActivity(i); break;
            case R.id.receta15: i = new Intent(this, Receta15Activity.class); startActivity(i); break;
            case R.id.receta16: i = new Intent(this, Receta16Activity.class); startActivity(i); break;
            case R.id.receta17: i = new Intent(this, Receta17Activity.class); startActivity(i); break;
            case R.id.receta18: i = new Intent(this, Receta18Activity.class); startActivity(i); break;
            case R.id.receta19: i = new Intent(this, Receta19Activity.class); startActivity(i); break;
            case R.id.receta20: i = new Intent(this, Receta20Activity.class); startActivity(i); break;

        }
    }

}