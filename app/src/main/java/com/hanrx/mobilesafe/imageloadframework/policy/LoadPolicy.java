package com.hanrx.mobilesafe.imageloadframework.policy;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

/**
 * 加载策略
 */
public interface LoadPolicy {
    /**
     * 两个BitmapRequest进行优先级比较
     * @param request1
     * @param request2
     * @return
     */
    int compareto(BitmapRequest request1, BitmapRequest request2);
}
