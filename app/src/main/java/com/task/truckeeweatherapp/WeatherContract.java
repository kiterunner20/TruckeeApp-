package com.task.truckeeweatherapp;


import com.task.truckeeweatherapp.model.Datum;

import java.util.List;

public class WeatherContract {

    interface Model {

        interface OnFinishedListener {


            void onFailure(Throwable t);

            void setWeatherData(List<Datum> data);

            void setEmptyData();
        }

        void getDailyWeather(OnFinishedListener onFinishedListener, String date);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable throwable);

        void setWeatherData(List<Datum> data);

        void setEmptyData();
    }

    interface Presenter {

        void onDestroy();

        void getDailyWeatherData(String date);
    }
}
