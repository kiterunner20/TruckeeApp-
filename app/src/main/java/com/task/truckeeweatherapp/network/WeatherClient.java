package com.task.truckeeweatherapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    public static final String BASE_URL = "https://api.darksky.net/";
    private static Retrofit retrofit = null;
    public static final String API_KEY = "YOUR_API_KEY";

    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
