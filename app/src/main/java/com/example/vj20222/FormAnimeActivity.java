package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vj20222.entities.Anime;

import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormAnimeActivity extends AppCompatActivity {
    Button btnGuardarAf;
    EditText etNameAf, etDescAf, etImageAf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anime);
        Retrofit retrofit = new RetrofitFactory(this).build();
        AnimeService service = retrofit.create(AnimeService.class);
        etNameAf = findViewById(R.id.etNameAf);
        etDescAf = findViewById(R.id.etDescAf);
        etImageAf = findViewById(R.id.etImageAf);
        btnGuardarAf = findViewById(R.id.btnGuardarAf);
        btnGuardarAf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNameAf.getText().toString().equals("") || etDescAf.getText().toString().equals("") || etImageAf.getText().toString().equals("") ){
                    Toast.makeText(FormAnimeActivity.this,"Ingresar Campos",Toast.LENGTH_LONG);
                }else{
                    Anime anime = new Anime();
                    anime.name = etNameAf.getText().toString();
                    anime.Desc = etDescAf.getText().toString();
                    anime.avatar = etImageAf.getText().toString();
                    service.create(anime).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent intent = new Intent(FormAnimeActivity.this, PaginaInicioActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Intent intent = new Intent(FormAnimeActivity.this, PaginaInicioActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}