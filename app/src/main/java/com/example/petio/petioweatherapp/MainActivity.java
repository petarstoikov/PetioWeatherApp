package com.example.petio.petioweatherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String TAG = "WEATHER";
    TextView tvTemp;
    ImageView tvImage;

    OpenWeatherAPI.ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemp = (TextView)findViewById(R.id.tvTemp);
        tvImage = (ImageView) findViewById(R.id.ivImage);


        api = OpenWeatherAPI.getClient().create(OpenWeatherAPI.ApiInterface.class);
    }

    public void getWeather(View v) {
        Double lat = 43.204666;
        Double lng = 27.910543;
        String units = "metric";
        String key = OpenWeatherAPI.KEY;

        Log.d(TAG, "OK");


        Call<DailyWeather> callToday;
        callToday = api.getToday(lat, lng, units, key);
        callToday.enqueue(new Callback<DailyWeather>() {
            @Override
            public void onResponse(Call<DailyWeather> call, Response<DailyWeather> response) {
                Log.e(TAG, "onResponse");
                DailyWeather data = response.body();
                Log.d(TAG, response.toString());

                if (response.isSuccessful()) {
                    tvTemp.setText(data.getCity() + " " + data.getTempWithDegree());
                    Glide.with(MainActivity.this).load(data.getIconUrl()).into(tvImage);
                }
            }

            @Override
            public void onFailure(Call<DailyWeather> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

    }
}
