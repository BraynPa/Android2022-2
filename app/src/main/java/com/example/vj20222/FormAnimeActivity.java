package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vj20222.entities.Anime;

import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormAnimeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnGuardarAf;
    EditText etNameAf, etImageAf;
    Spinner spinner;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anime);
        Retrofit retrofit = new RetrofitFactory(this).build();
        AnimeService service = retrofit.create(AnimeService.class);
        etNameAf = findViewById(R.id.etNameAf);
        spinner = findViewById(R.id.etDescAf);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setAdapter(adapter);
        etImageAf = findViewById(R.id.etImageAf);
        btnGuardarAf = findViewById(R.id.btnGuardarAf);
        btnGuardarAf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNameAf.getText().toString().equals("") || text.equals("") || etImageAf.getText().toString().equals("") ){
                    Toast.makeText(FormAnimeActivity.this,"Ingresar Campos",Toast.LENGTH_LONG).show();
                }else{
                    Anime anime = new Anime();
                    anime.name = etNameAf.getText().toString();
                    anime.tipo = text;
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}