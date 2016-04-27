package com.leichao.network.net.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.leichao.network.config.NetConfig;
import com.leichao.network.util.Debug;

import java.io.InputStream;

/**
 * 自定义GlideModule
 * 参考资料http://mrfu.me/2016/02/27/Glide_Getting_Started/
 * 需要在AndroidManifest.xml中application标签下增加
 *  <meta-data
 *      android:name="com.leichao.network.net.glide.CustomGlideModule"
 *      android:value="GlideModule" />
 * Created by leichao on 2016/4/20.
 */
public class CustomGlideModule implements GlideModule {

    private static final String TAG = "CustomGlideModule";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        Debug.e(TAG, "applyOptions");
        builder.setDiskCache(new DiskLruCacheFactory(NetConfig.GLIDE_CACHE_DIR, NetConfig.GLIDE_CACHE_SIZE));
        builder.setDecodeFormat(NetConfig.GLIDE_DECODE_FORMAT);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        Debug.e(TAG, "registerComponents");
        glide.register(GlideUrl.class, InputStream.class, new OkHttpModeLoader.Factory());
    }
}
