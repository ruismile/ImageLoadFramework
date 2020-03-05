package com.hanrx.mobilesafe.imageloadframework.request;

import android.util.Log;

import com.hanrx.mobilesafe.imageloadframework.loader.Loader;
import com.hanrx.mobilesafe.imageloadframework.loader.LoaderManager;

import java.util.concurrent.BlockingQueue;

/**
 * 转发器
 * 请求转发线程 不断从请求队列中获取请求
 */
public class RequestDispatcher extends Thread{
    private static final String TAG = "RequestDispatcher";
    //请求队列
    private BlockingQueue<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                BitmapRequest request = mRequestQueue.take();
                //处理请求对象
                String schema = pareSchema(request.getImageUrl());
                //获取加载器
                Loader loader = LoaderManager.getInstance().getLoader(schema);
                loader.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String pareSchema(String imageUrl) {
        if (imageUrl.contains("://")) {
            return imageUrl.split("://")[0];
        } else {
            Log.i(TAG, "不支持此类型");
        }
        return null;
    }
}
