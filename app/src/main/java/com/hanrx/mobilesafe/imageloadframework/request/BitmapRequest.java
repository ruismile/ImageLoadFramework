package com.hanrx.mobilesafe.imageloadframework.request;

import android.widget.ImageView;

import com.hanrx.mobilesafe.imageloadframework.config.DisplayConfig;
import com.hanrx.mobilesafe.imageloadframework.loader.SimpleImageLoader;
import com.hanrx.mobilesafe.imageloadframework.policy.LoadPolicy;

import java.lang.ref.SoftReference;
import java.util.Comparator;

public class BitmapRequest implements Comparator<BitmapRequest> {
    //持有imageView的软引用
    private SoftReference<ImageView> imageViewSoft;
    //图片路径
    private String ImageUrl;

    //MD5的图片路径
    private String imageUriMD5;

    private DisplayConfig displayConfig;
    //下载完成监听
    public SimpleImageLoader.ImageListener imageListener;

    public BitmapRequest(ImageView imageView, String imageUrl, DisplayConfig displayConfig,
                         SimpleImageLoader.ImageListener imageListener) {
        this.imageViewSoft = new SoftReference<ImageView>(imageView);
        //设置可见的ImageView的Tag,要下载的图片路径
        imageView.setTag(imageUrl);
        this.ImageUrl = imageUrl;
        if (displayConfig != null) {
            this.displayConfig = displayConfig;
        }
        this.imageListener = imageListener;
    }

    //加载策略
    private LoadPolicy loadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();

    //编号
    private int serialNo;


    /**
     * 优先级的确定
     * @param bitmapRequest
     * @param t1
     * @return
     */
    @Override
    public int compare(BitmapRequest bitmapRequest, BitmapRequest t1) {
        return loadPolicy.compareto(bitmapRequest, t1);
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals() {
        return super.equals();
    }
}
