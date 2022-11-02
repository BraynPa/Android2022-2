package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaginaInicioActivity extends AppCompatActivity {

    Button btnListaA, btnCrearA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicio);

        btnCrearA = findViewById(R.id.btnCrearA);
        btnListaA = findViewById(R.id.btnListaA);
        btnCrearA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicioActivity.this, FormAnimeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnListaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicioActivity.this, AnimesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}