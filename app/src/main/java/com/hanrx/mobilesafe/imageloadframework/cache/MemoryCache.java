package com.hanrx.mobilesafe.imageloadframework.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public class MemoryCache implements BitmapCache{
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        int maxSize = (int)(Runtime.getRuntime().freeMemory()/1024/8);
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };

    }
    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        mLruCache.put(bitmapRequest.getImageUriMD5(), bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mLruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUriMD5());
    }
}
