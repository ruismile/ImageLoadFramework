package com.hanrx.mobilesafe.imageloadframework.request;

import java.util.concurrent.BlockingQueue;

/**
 * 转发器
 * 请求转发线程 不断从请求队列中获取请求
 */
public class RequestDispatcher extends Thread{
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
