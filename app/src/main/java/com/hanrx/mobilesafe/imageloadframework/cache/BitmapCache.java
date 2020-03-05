package com.hanrx.mobilesafe.imageloadframework.cache;

import android.graphics.Bitmap;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public interface BitmapCache {
    /**
     * 缓存bitmap
     * @param bitmapRequest
     * @param bitmap
     */
    void put(BitmapRequest bitmapRequest, Bitmap bitmap);

    /**
     * 通过请求取bitmap
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 移除缓存
     * @param request
     */
    void remove(BitmapRequest request);
}
