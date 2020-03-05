package com.hanrx.mobilesafe.imageloadframework.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;
import com.hanrx.mobilesafe.imageloadframework.utils.BitmapDecoder;
import com.hanrx.mobilesafe.imageloadframework.utils.ImageViewHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlLoader extends AbstarctLoader{
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BitmapDecoder bitmapDecoder = null;
        try {
            connection = (HttpURLConnection)(new URL(request.getImageUrl())).openConnection();
            //转化成bufferInputStream流
            inputStream = new BufferedInputStream(connection.getInputStream());
            //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.mark(inputStream.available());
            //解码图片
            final InputStream finalInputStream = inputStream;
            bitmapDecoder = new BitmapDecoder() {
                @Override
                public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                    //option是入参出参对象
                    Bitmap bitmap = BitmapFactory.decodeStream(finalInputStream,null,options);
                    //流这里是第一次读 在BitmapDecoder里设置inJustDecodeBounds true
                    if (options.inJustDecodeBounds) {
                        try {
                            //第一次读宽高信息，读完之后必须为第二次大户整个照片进行准备，将流重置
                            finalInputStream.reset();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            //第二次读关闭流,在BitmapDecoder里设置inJustDecodeBounds false
                            finalInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return bitmap;
                }
            };
            return bitmapDecoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()),
                    ImageViewHelper.getImageViewHeight(request.getImageView()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
