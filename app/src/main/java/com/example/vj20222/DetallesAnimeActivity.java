package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vj20222.entities.Anime;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetallesAnimeActivity extends AppCompatActivity {
    TextView tvNameAd, tvDescAd;
    ImageView ivImageAd;
    Button btnEditarAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_anime);
        Retrofit retrofit = new RetrofitFactory(this).build();
        AnimeService service = retrofit.create(AnimeService.class);
        int id = Integer.parseInt(getIntent().getStringExtra("dato"));
        String datos = id+"";
        tvDescAd = findViewById(R.id.tvDescAd);
        tvNameAd = findViewById(R.id.tvNameAd);
        ivImageAd = findViewById(R.id.ivImageAd);
        btnEditarAd = findViewById(R.id.btnEditarAd);
        service.get(id).enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                Anime data = response.body();
                tvDescAd.setText(data.Desc);
                tvNameAd.setText(data.name);
                Picasso.get().load(data.avatar).into(ivImageAd);
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {

            }
        });
        btnEditarAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallesAnimeActivity.this, EditAnimeAtivity.class);
                intent.putExtra("datos",datos.toString());
                startActivity(intent);
            }
        });
    }
}