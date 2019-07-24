package com.task.truckeeweatherapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.task.truckeeweatherapp.model.Datum;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View, DatePickerDialog.OnDateSetListener {

    private WeatherPresenter presenter;
    TextView tvLowTemparature;
    TextView tvHighTemparature;
    TextView tvDate;
    TextView weatherType;
    ImageView weatherIcon;
    int tempratureLow;
    int temparatureHigh;
    StringBuilder dayCalender = new StringBuilder();
    StringBuilder monthCalender = new StringBuilder();
    String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        setListner();
        presenter = new WeatherPresenter(this);
        presenter.getDailyWeatherData("2019-06-24T19:06:32");
    }

    public void setListner() {
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog =
                        new DatePickerDialog(WeatherActivity.this, WeatherActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE));
                dialog.show();
            }


        });

    }


    public void initUi() {
        tvLowTemparature = findViewById(R.id.tv_low_temp);
        tvHighTemparature = findViewById(R.id.tv_high_temparature);
        tvDate = findViewById(R.id.tv_date);
        weatherIcon = findViewById(R.id.im_weather_icon);
        weatherType = findViewById(R.id.tv_weather_type);

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
        if (data != null) {
            if (data.get(0) != null) {
                tempratureLow = (int) Math.round(data.get(0).getTemperatureLow());
                temparatureHigh = (int) Math.round(data.get(0).getTemperatureHigh());
                tvLowTemparature.setText(String.valueOf(tempratureLow) + (char) 0x00B0);
                tvHighTemparature.setText(String.valueOf(temparatureHigh) + (char) 0x00B0);


            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dayCalender = new StringBuilder();
        monthCalender = new StringBuilder();


        if (dayOfMonth > 9) {
            dayCalender.append(dayOfMonth);
        } else {
            dayCalender.append(0);
            dayCalender.append(dayOfMonth);
        }
        if (month > 8) {
            monthCalender.append(month + 1);
        } else {
            monthCalender.append(0);
            monthCalender.append(month + 1);
        }
        tvDate.setText("  " + months[month] + " " + dayCalender.toString() + "," + year);
        String date = year + "-" + monthCalender.toString() + "-" + dayCalender.toString() + "T19:06:32";
        presenter.getDailyWeatherData(date);
    }
}
