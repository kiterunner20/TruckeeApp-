package com.task.truckeeweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.task.truckeeweatherapp.model.Datum;

import java.util.List;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    private WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new WeatherPresenter(this);

        presenter.getDailyWeatherData("2019-06-24T19:06:32");

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void setWeatherData(List<Datum> data) {
        Log.v("jish","1");
        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
    }
}
