package com.hanrx.mobilesafe.imageloadframework.cache;

import android.graphics.Bitmap;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public class DoubleCache implements BitmapCache{
    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {

    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {

    }
}
