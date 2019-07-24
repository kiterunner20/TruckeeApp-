
package com.task.truckeeweatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("temperatureLow")
    @Expose
    private double temparatureLow;
    @SerializedName("temperatureHigh")
    @Expose
    private double temparatureHigh;
    @SerializedName("precipType")
    @Expose
    private String precipType;


    public String getSummary() {
        return summary;
    }


    public String getIcon() {
        return icon;
    }

    public double getTemperatureLow() {
        return temparatureLow;
    }

    public double getTemperatureHigh() {
        return temparatureHigh;
    }

    public String getPrecipType() {
        return precipType;
    }


}
