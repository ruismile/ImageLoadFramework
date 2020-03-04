package com.hanrx.mobilesafe.imageloadframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hanrx.mobilesafe.imageloadframework.config.ImageLoaderConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfig.Builder builder = new ImageLoaderConfig.Builder();
        builder.setCachePolicy().setLoadingImage().setCachePolicy()
    }
}
