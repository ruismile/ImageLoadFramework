package com.hanrx.mobilesafe.imageloadframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.hanrx.mobilesafe.imageloadframework.cache.DisCache;
import com.hanrx.mobilesafe.imageloadframework.config.ImageLoaderConfig;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setMarqueeRepeatLimit(-1); //设置循环次数，-1表示无限循环
        textView.setSingleLine(true);
        ImageLoaderConfig.Builder builder = new ImageLoaderConfig.Builder();
        builder.setCachePolicy(new DisCache()).setLoadingImage().setCachePolicy();
    }
}
