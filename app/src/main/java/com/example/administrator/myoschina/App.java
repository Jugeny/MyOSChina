package com.example.administrator.myoschina;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2017/4/27.
 */

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        OkGo.init(this);//初始化okgo框架
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);//二维码初始化
    }
    public static Context getContext(){
        return context;
    }
}
