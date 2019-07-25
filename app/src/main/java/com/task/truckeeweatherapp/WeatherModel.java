package com.task.truckeeweatherapp;

import com.task.truckeeweatherapp.model.WeatherDetails;
import com.task.truckeeweatherapp.network.WeatherClient;
import com.task.truckeeweatherapp.network.WeatherInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.task.truckeeweatherapp.network.WeatherClient.API_KEY;

public class WeatherModel implements WeatherContract.Model {


    @Override
    public void getDailyWeather(final OnFinishedListener onFinishedListener, String date) {
        WeatherInterface apiService =
                WeatherClient.getClient().create(WeatherInterface.class);
        Call<WeatherDetails> call = apiService.getWeatherData(API_KEY, 39.3280, -120.1833,
                date, "hourly,currently,flags");
        call.enqueue(new Callback<WeatherDetails>() {
            @Override
            public void onResponse(Call<WeatherDetails> call, Response<WeatherDetails> response) {
                if (response.body().getDaily() != null) {
                    if (response.body().getDaily().getData() != null) {
                        onFinishedListener.setWeatherData(response.body().getDaily().getData());
                    }
                }else{
                    onFinishedListener.setEmptyData();
                }
            }

            @Override
            public void onFailure(Call<WeatherDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });


    }
}
