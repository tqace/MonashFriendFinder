package com.example.dingweichao.util;

import android.text.TextUtils;


import com.example.dingweichao.monashfriendfinder.MovieInfo;
import com.example.dingweichao.monashfriendfinder.WeatherActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {



    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static WeatherActivity.Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, WeatherActivity.Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static MovieInfo.Result handleMovieResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            String movieContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(movieContent, MovieInfo.Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
