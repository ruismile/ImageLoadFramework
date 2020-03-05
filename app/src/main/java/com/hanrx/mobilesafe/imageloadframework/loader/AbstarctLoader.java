package com.hanrx.mobilesafe.imageloadframework.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.hanrx.mobilesafe.imageloadframework.cache.BitmapCache;
import com.hanrx.mobilesafe.imageloadframework.config.DisplayConfig;
import com.hanrx.mobilesafe.imageloadframework.request.BitmapRequest;

/**
 * 抽象加载器
 */
public abstract class AbstarctLoader implements Loader{
    //拿到用户自定义配置的缓存策略
    private BitmapCache bitmapCache = SimpleImageLoader.getInstance().getConfig().getBitmapCache();
    //拿到显示配置
    private DisplayConfig displayConfig = SimpleImageLoader.getInstance().getConfig().getDisplayConfig();

    @Override
    public void loadImage(BitmapRequest request) {
        //从缓存中取到bitmap
        Bitmap bitmap = bitmapCache.get(request);
        if (bitmap == null) {
            //显示默认加载图片
            showLoadingImage(request);
            //开始真正加载图片
            bitmap = onLoad(request);
            //缓存图片
            cacheBitmap(request, bitmap);
        }
    }

    /**
     * 缓存图片
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if (request != null && bitmap != null) {
            synchronized (AbstarctLoader.class) {
                bitmapCache.put(request, bitmap);
            }
        }
    }

    //抽象加载策略，因为加载网络图片和本地图片有差异
    protected abstract Bitmap onLoad(BitmapRequest request);

    /**
     * 加载前显示的图片
     * @param request
     */
    private void showLoadingImage(BitmapRequest request) {
        //指定了，显示配置
        if(hasLoadingPlaceHolder()){
            final ImageView imageView = request.getImageView();
            if(imageView!=null)
            {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(displayConfig.loadingImage);
                    }
                });
            }
        }
    }

    protected boolean hasLoadingPlaceHolder(){
        return (displayConfig != null && displayConfig.loadingImage > 0);
    }
}
