package com.hanrx.mobilesafe.imageloadframework.policy;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public class ReversePolicy implements LoadPolicy{
    @Override
    public int compareto(BitmapRequest request1, BitmapRequest request2) {
        return request2.getSerialNo() - request1.getSerialNo();
    }
}
