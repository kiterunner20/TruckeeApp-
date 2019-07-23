package com.task.truckeeweatherapp.network;

import com.task.truckeeweatherapp.model.WeatherDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherInterface {

    @GET("forecast/{api_key}/{latitude},{longitude},{date}")
    Call<WeatherDetails> getWeatherData(@Path("api_key") String apiKey, @Path("latitude") double latitude,
                                        @Path("longitude") double longitude, @Path("date") String date,
                                        @Query("exclude") String exclude);
}
