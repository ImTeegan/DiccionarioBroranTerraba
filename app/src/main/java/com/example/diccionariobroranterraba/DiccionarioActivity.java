package com.example.diccionariobroranterraba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DiccionarioActivity extends AppCompatActivity {

    List<ListElement> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diccionario);

        init();
    }

    public void init() { // puede estar en un archivo
        elements = new ArrayList<>();
        elements.add(new ListElement("Aguacate", "se borraraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        elements.add(new ListElement("maiz", "se borraraaaa"));
        elements.add(new ListElement("Yisus", "se borraraaaa"));
        elements.add(new ListElement("Yogurt", "se borraraaaa"));

        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.diccionarioRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}