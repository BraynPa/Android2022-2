package com.example.vj20222.services;

import com.example.vj20222.entities.Anime;
import com.example.vj20222.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnimeService {
    @GET("Anime")
    Call<List<Anime>> all();
    @GET("Anime/{id}")
    Call<Anime> get(@Path("id") int id);
    @POST("Anime")
    Call<Void> create(@Body Anime anime);
    @PUT("Anime/{id}")
    Call<Anime> update(@Body Anime anime, @Path("id") int id);
    @DELETE("Anime/{id}")
    Call<Void> delete(@Path("id") int id);
}
