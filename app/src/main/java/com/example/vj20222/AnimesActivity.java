package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vj20222.Adapter.AnimeAdapter;
import com.example.vj20222.Adapter.ContactAdapter;
import com.example.vj20222.entities.Anime;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnimesActivity extends AppCompatActivity {
    RecyclerView rvAnime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animes);
        Retrofit retrofit = new RetrofitFactory(this).build();
        AnimeService service = retrofit.create(AnimeService.class);
        service.all().enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                List<Anime> data = response.body();
                rvAnime = findViewById(R.id.rvAnime);
                rvAnime.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvAnime.setAdapter(new AnimeAdapter((data)));
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {

            }
        });
    }
}