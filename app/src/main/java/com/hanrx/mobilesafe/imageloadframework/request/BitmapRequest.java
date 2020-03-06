package com.hanrx.mobilesafe.imageloadframework.request;

import android.widget.ImageView;

import com.hanrx.mobilesafe.imageloadframework.config.DisplayConfig;
import com.hanrx.mobilesafe.imageloadframework.loader.SimpleImageLoader;
import com.hanrx.mobilesafe.imageloadframework.policy.LoadPolicy;
import com.hanrx.mobilesafe.imageloadframework.utils.MD5Utils;

import java.lang.ref.SoftReference;

public class BitmapRequest implements Comparable<BitmapRequest> {
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
        this.imageUriMD5 = MD5Utils.toMD5(imageUrl);
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
     * @param request
     * @return
     */
    @Override
    public int compareTo(BitmapRequest request) {
        return loadPolicy.compareto(request,this);
    }




    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (serialNo != that.serialNo) return false;
        return loadPolicy != null ? loadPolicy.equals(that.loadPolicy) : that.loadPolicy == null;

    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + serialNo;
        return result;
    }

    public ImageView getImageView() {
        return imageViewSoft.get();
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }

    public void setImageUriMD5(String imageUriMD5) {
        this.imageUriMD5 = imageUriMD5;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public void setDisplayConfig(DisplayConfig displayConfig) {
        this.displayConfig = displayConfig;
    }

    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public void setLoadPolicy(LoadPolicy loadPolicy) {
        this.loadPolicy = loadPolicy;
    }
}
