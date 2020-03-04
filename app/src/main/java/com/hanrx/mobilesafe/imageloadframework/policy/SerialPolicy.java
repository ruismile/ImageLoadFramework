package com.hanrx.mobilesafe.imageloadframework.policy;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

public class SerialPolicy implements LoadPolicy{

    @Override
    public int compareto(BitmapRequest request1, BitmapRequest request2) {
        return request1.getSerialNo() - request2.getSerialNo();
    }
}
