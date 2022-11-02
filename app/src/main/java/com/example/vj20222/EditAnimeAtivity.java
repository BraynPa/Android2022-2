package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vj20222.entities.Anime;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditAnimeAtivity extends AppCompatActivity {
    EditText etNamaAe, etDescAe, etIageAe;
    Button btnEditarAe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_anime_ativity);
        etDescAe = findViewById(R.id.etDescAe);
        etNamaAe = findViewById(R.id.etNamaAe);
        etIageAe = findViewById(R.id.etIageAe);
        btnEditarAe = findViewById(R.id.btnEditarAe);
        int id = Integer.parseInt(getIntent().getStringExtra("datos"));
        Log.e("MAIN_APP", String.valueOf(id));
        Retrofit retrofit = new RetrofitFactory(this).build();
        AnimeService service = retrofit.create(AnimeService.class);
        service.get(id).enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                Anime data = response.body();
                etDescAe.setText(data.Desc);
                etNamaAe.setText(data.name);
                etIageAe.setText(data.avatar);
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {

            }
        });

        btnEditarAe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Anime anime = new Anime();
                anime.Desc = etDescAe.getText().toString();
                anime.avatar = etIageAe.getText().toString();
                anime.name = etNamaAe.getText().toString();

                service.update(anime,id).enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        Intent intent = new Intent(EditAnimeAtivity.this, PaginaInicioActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                    }
                });
            }
        });

    }
}