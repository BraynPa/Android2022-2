package com.example.vj20222.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vj20222.DetallesAnimeActivity;
import com.example.vj20222.EditContactActivity;
import com.example.vj20222.R;
import com.example.vj20222.entities.Anime;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.AnimeService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Retrofit;

public class AnimeAdapter extends RecyclerView.Adapter {

    List<Anime> data;
    private SharedPreferences sharedPreferences;
    public AnimeAdapter(List<Anime> data){
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_anime, parent, false);
        return new AnimeAdapter.AnimeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvDescAi = holder.itemView.findViewById(R.id.tvDescAi);
        tvDescAi.setText(data.get(position).tipo);
        TextView tvNameAi = holder.itemView.findViewById(R.id.tvNameAi);
        tvNameAi.setText(data.get(position).name);
        ImageView ivImgAi = holder.itemView.findViewById(R.id.ivImgAi);
        //ivContac.setImageResource(R.drawable.avatar2);
        Picasso.get().load(data.get(position).avatar).into(ivImgAi);
        Button btnDetallesAi= holder.itemView.findViewById(R.id.btnDetallesAi);
        int id = data.get(position).id;
        String dato = id+"";
        btnDetallesAi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetallesAnimeActivity.class);
                intent.putExtra("dato",dato.toString());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class AnimeViewHolder extends RecyclerView.ViewHolder{

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
