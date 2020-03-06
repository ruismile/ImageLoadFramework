package com.hanrx.mobilesafe.imageloadframework.config;

import com.hanrx.mobilesafe.imageloadframework.cache.BitmapCache;
import com.hanrx.mobilesafe.imageloadframework.cache.DoubleCache;
import com.hanrx.mobilesafe.imageloadframework.cache.MemoryCache;
import com.hanrx.mobilesafe.imageloadframework.policy.LoadPolicy;
import com.hanrx.mobilesafe.imageloadframework.policy.ReversePolicy;

public class ImageLoaderConfig {
    //缓存策略
    private BitmapCache bitmapCache = new MemoryCache();
    //加载策略
    private LoadPolicy loadPolicy = new ReversePolicy();
    //默认线程数
    private int threadCount = Runtime.getRuntime().availableProcessors();
    //显示配置
    private DisplayConfig displayConfig = new DisplayConfig();

    private ImageLoaderConfig() {

    }

    /**
     * 建造者模式
     * 和AlterDialog 建造过程类似
     */
    public static class Builder {
        private ImageLoaderConfig config;
        public Builder() {
            config = new ImageLoaderConfig();
        }

        /**
         * 设置缓存策略
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(BitmapCache bitmapCache) {
            config.bitmapCache = bitmapCache;
            return this;
        }

        /**
         * 设置加载策略
         * @param loadPolicy
         * @return
         */
        public Builder setLoadPolicy(LoadPolicy loadPolicy) {
            config.loadPolicy = loadPolicy;
            return this;
        }

        /**
         * 设置线程个数
         * @param count
         * @return
         */
        public Builder setThreadCount(int count) {
            config.threadCount = count;
            return this;
        }

        /**
         * 设置加载过程中的图片
         * @param resID
         * @return
         */
        public Builder setLoadingImage(int resID) {
            config.displayConfig.loadingImage = resID;
            return this;
        }

        /**
         * 设置加载失败的图片
         * @param resID
         * @return
         */
        public Builder setFailedImage(int resID) {
            config.displayConfig.failedImage = resID;
            return this;
        }

        public ImageLoaderConfig build() {
            return config;
        }

    }

    public BitmapCache getBitmapCache() {
        return bitmapCache;
    }

    public void setBitmapCache(BitmapCache bitmapCache) {
        this.bitmapCache = bitmapCache;
    }

    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public void setLoadPolicy(LoadPolicy loadPolicy) {
        this.loadPolicy = loadPolicy;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public void setDisplayConfig(DisplayConfig displayConfig) {
        this.displayConfig = displayConfig;
    }
}
