package com.example.dingweichao.monashfriendfinder;

/**
 * Created by Ding Weichao on 2017/5/4.
 */


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dingweichao.service.AutoUpdateService;
import com.example.dingweichao.util.HttpUtil;
import com.example.dingweichao.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class WeatherActivity extends AppCompatActivity {


    private TextView degreeText;

    private TextView weatherInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        final String weatherId="beijing";
        // 无缓存时去服务器查询天气
       // weatherId = getIntent().getStringExtra("weather_id");
        requestWeather(weatherId);
    }
    public class Weather {
        public Basic basic;
        public String status;
        public Now now;

    }
    public class Basic {
        public String city;
        private String cnty;
        public String id;


    }
    public class Now {
        public Cond cond;
        public class Cond
        {
            public String code;
            public String txt;
        }
        public String fl;
        public String hum;
        public String pcpn;
        public String pres;
        public String tmp;
        public String vis;
        public Wind wind;
        public class Wind
        {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    /**
     * 根据天气id请求城市天气信息。
     */
    public void requestWeather(final String weatherId) {
        String weatherUrl = "https://free-api.heweather.com/v5/now?city=" + weatherId + "&key=943badd9f8fb4ffa85486c9a25340466";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            //SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            //editor.putString("weather", responseText);
                            //editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败2", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.city;
        String degree = weather.now.tmp + "℃";
        String weatherInfo = weather.now.cond.txt;
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }
}

