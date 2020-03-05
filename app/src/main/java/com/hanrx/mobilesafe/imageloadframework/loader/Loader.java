package com.hanrx.mobilesafe.imageloadframework.loader;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public interface Loader {

    /**
     * 加载图片
     * @param request
     */
    void loadImage(BitmapRequest request);
}
