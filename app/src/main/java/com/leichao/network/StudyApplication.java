package com.leichao.network;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.leichao.network.config.CommonConfig;
import com.leichao.network.util.SDCardUtil;

import java.io.File;

/**
 *
 * Created by leichao on 2016/4/19.
 */
public class StudyApplication extends Application {

    private static StudyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static StudyApplication getInstance() {
        if (instance == null) {
            throw new RuntimeException("应用还未初始化");
        }
        return instance;
    }

    public File getPrivateDir() {
        return getDir(CommonConfig.STORAGE_NAME, Context.MODE_PRIVATE);
    }

    public File getPublicDir() {
        File publicPath = getExternalCacheDir();
        if (publicPath == null) {
            if (SDCardUtil.isSDCardEnable()) {
                return new File(Environment.getExternalStorageDirectory() + File.separator
                        + "Android" + File.separator
                        + "data" + File.separator
                        + getPackageName() + File.separator
                        + CommonConfig.STORAGE_NAME);
            } else {
                return getPrivateDir();
            }
        } else {
            return new File(publicPath.getParentFile(), CommonConfig.STORAGE_NAME);
        }
    }
}
