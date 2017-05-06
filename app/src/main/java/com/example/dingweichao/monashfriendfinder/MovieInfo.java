package com.example.dingweichao.monashfriendfinder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dingweichao.service.AutoUpdateService;
import com.example.dingweichao.util.HttpUtil;
import com.example.dingweichao.util.Utility;

import java.io.IOException;
import java.net.*;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by tangqu on 17/5/5.
 */

public class MovieInfo extends AppCompatActivity {
    private ImageView posterImage;

    private TextView movieNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        posterImage = (ImageView) findViewById(R.id.poster_image);
        movieNameText = (TextView) findViewById(R.id.movie_name_text);
        final String q = "titanic";
        requestmovie(q);
    }

    public class Result {
        public String title;
        public String description;
        public String provider;
        public String awards;
        public String poster_path;
    }


    /**
     * 根据关键字q请求影片信息。
     */
    public void requestmovie(final String q) {
        String movieUrl = "http://api.themoviedb.org/3/search/movie?api_key=7efc6c8b6fc455fb8637c6c5d9248e49&query=" + q;
        HttpUtil.sendOkHttpRequest(movieUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final MovieInfo.Result result = Utility.handleMovieResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {

                            showMovieInfo(result);


                        } else {
                            Toast.makeText(MovieInfo.this, "123", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MovieInfo.this, "获取影片信息失败2", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 处理并展示Result实体类中的数据。
     */
    private void showMovieInfo(MovieInfo.Result result) {

        String movieName = result.title;
        String path = "https://image.tmdb.org/t/p/w500" + result.poster_path;
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader loader=ImageLoader.getInstance();
        loader.init(config);
        loader.displayImage(path, posterImage);
        movieNameText.setText(movieName);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);


    }




}
