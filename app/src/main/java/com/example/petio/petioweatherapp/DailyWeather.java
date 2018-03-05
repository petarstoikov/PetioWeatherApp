package com.example.petio.petioweatherapp;

import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by Petio on 26.2.2018 г..
 */

public class DailyWeather {
    public class WeTemp
    {
        Double temp;

    }
    public class WeIcon
    {
        String icon;
    }
    @SerializedName("weather")
    private List<WeIcon> icon;
    @SerializedName("main")
    private WeTemp temp;
     @SerializedName("name")
     private String city;

    public DailyWeather(WeTemp temp, List<WeIcon> icon)
    {
        this.temp=temp;
        this.icon=icon;
    }



    public String getTempWithDegree()
    {
        return String.valueOf(temp.temp.intValue()) + "\u00B0";
    }

    public String getCity()
    {
        return city;
    }

    public String getIcon()

    {
        return icon.get(0).icon;
    }

    public String getIconUrl()
    {
        return "http://openweathermap.org/img/w/" + icon.get(0).icon + ".png";
    }

}
