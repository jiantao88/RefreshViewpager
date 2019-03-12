package com.refreshviewpager;

import android.app.ActivityManager;
import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;



@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        /**
         * 使用 ActivityManager 获取当前设备的内存情况，如果是处于
         * lowMemory 的时候，将图片的 DecodeFormat 设置为 RGB_565 ， RGB_565 和默认的 ARGB_8888 比，每个像素会少 2 个byte，这
         * 样，等于一张同样的图片，加载到内存中会少一半内存的占用（ARGB_8888 每个像素占 4 byte）
         */
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (null != activityManager) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            builder.setDefaultRequestOptions(new RequestOptions().format(memoryInfo.lowMemory ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888));
        }
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
            .setMemoryCacheScreens(2)
            .setBitmapPoolScreens(3)
            .build();
        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));

    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
