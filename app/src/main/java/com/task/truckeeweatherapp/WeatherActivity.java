package com.task.truckeeweatherapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.task.truckeeweatherapp.model.Datum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View, DatePickerDialog.OnDateSetListener {

    private WeatherPresenter presenter;
    TextView tvLowTemparature;
    TextView tvHighTemparature;
    TextView tvDate;
    TextView weatherType;
    ImageView weatherIcon;
    RelativeLayout progressBarLayout;
    RelativeLayout imageLayout;

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
        setDateAttribs();


    }

    private void setDateAttribs() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        String[] separated = formattedDate.split("-");
        tvDate.setText("  " + months[Integer.parseInt(separated[1])-1] + " " + separated[2] + "," + separated[0]);
        presenter = new WeatherPresenter(this);
        presenter.getDailyWeatherData(formattedDate + "T19:06:32");

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
        progressBarLayout = findViewById(R.id.rl_progressbar);
        imageLayout = findViewById(R.id.rl_image_layout);

    }


    @Override
    public void showProgress() {
        imageLayout.setVisibility(View.GONE);
        progressBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        imageLayout.setVisibility(View.VISIBLE);
        progressBarLayout.setVisibility(View.GONE);
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
                if (data.get(0).getIcon() != null) {
                    if (data.get(0).getIcon().equals("clear-day") || data.get(0).getIcon().equals("clear-night")) {
                        weatherIcon.setImageResource(R.drawable.sunny);
                        weatherType.setText("SUNNY");
                    } else if (data.get(0).getIcon().equals("rain")) {
                        weatherIcon.setImageResource(R.drawable.rain);
                        weatherType.setText("RAIN");
                    } else if (data.get(0).getIcon().equals("snow")) {
                        weatherIcon.setImageResource(R.drawable.snow);
                        weatherType.setText("SNOW");
                    } else if (data.get(0).getIcon().equals("sleet")) {
                        weatherIcon.setImageResource(R.drawable.sleet);
                        weatherType.setText("SLEET");
                    } else if (data.get(0).getIcon().equals("wind")) {
                        weatherIcon.setImageResource(R.drawable.wind);
                        weatherType.setText("WIND");
                    } else if (data.get(0).getIcon().equals("fog")) {
                        weatherIcon.setImageResource(R.drawable.fog);
                        weatherType.setText("FOG");
                    } else if (data.get(0).getIcon().equals("cloudy")) {
                        weatherIcon.setImageResource(R.drawable.cloudy);
                        weatherType.setText("CLOUDY");
                    } else if (data.get(0).getIcon().equals("partly-cloudy-day") || data.get(0).getIcon().equals("partly-cloudy-night")) {
                        weatherIcon.setImageResource(R.drawable.partlycloudy);
                        weatherType.setText("PARTLY CLOUDY");

                    }

                }


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
