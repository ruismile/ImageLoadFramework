package com.hanrx.mobilesafe.imageloadframework.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestQueue {
    private static final String TAG = "RequestQueue";
    /**
     * 阻塞式队列
     * 多线程共享
     * 生产效率和消费效率相差太远
     * disPlayImage()
     * 使用优先级队列
     * 优先级高的先被消费
     * 每个产品都有编号
     */
    private BlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<>();

    //转发器的数量
    private int threadCount;

    private AtomicInteger i = new AtomicInteger(0);
    //一组转发器
    private RequestDispatcher[] mDispachers;

    public RequestQueue(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * 添加请求对象
     * @param request
     */
    public void addRequest(BitmapRequest request) {
        //判断请求队列是否包含请求
        if (!mRequestQueue.contains(request)) {
            //给请求进行编号
            request.setSerialNo(i.incrementAndGet());
            mRequestQueue.add(request);
        } else {
            Log.i(TAG,"请求已经存在 编号 ：" + request.getSerialNo());
        }
    }

    /**
     * 开启请求
     */
    public void start() {
        //先停止
        stop();
        startDispatchers();
    }

    /**
     * 开启转发器
     */
    private void startDispatchers() {
        mDispachers = new RequestDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            RequestDispatcher p = new RequestDispatcher(mRequestQueue);
            mDispachers[i] = p;
            mDispachers[i].start();
        }
    }

    /**
     * 停止请求
     */
    public void stop() {

    }
}
