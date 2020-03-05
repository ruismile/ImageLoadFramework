package com.hanrx.mobilesafe.imageloadframework.loader;

import java.util.HashMap;
import java.util.Map;

public class LoaderManager {
    private static final String TAG = "LoaderManager";
    //缓存所以支持的Loader类型
    private Map<String, Loader> mLoaderMap = new HashMap<>();

    private static LoaderManager mInstance = new LoaderManager();
    private LoaderManager() {
        register("http", new UrlLoader());
        register("https", new UrlLoader());
        register("file", new UrlLoader());
    }

    public static LoaderManager getInstance(){
        return mInstance;
    }

    private void register(String schema, UrlLoader loader){
        mLoaderMap.put(schema,loader);
    }

    public Loader getLoader(String schema) {
        if (mLoaderMap.containsKey(schema)) {
            return mLoaderMap.get(schema);
        }
        return new NullLoader();
    }
}
