package com.hanrx.mobilesafe.imageloadframework.loader;

import android.graphics.Bitmap;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public class NullLoader extends AbstarctLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        return null;
    }
}
