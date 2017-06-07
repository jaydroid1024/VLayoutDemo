package com.jay.vlayoutdemo;

import android.app.Application;

import com.jay.vlayoutdemo.utils.CrashHandlerUtil;


/**
 * Created by Administrator on 2016/12/22.
 */

public class App extends Application{
    private static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        GlobalData.init(this);
        CrashHandlerUtil.getInstance().init(this);
    }
    public static  App getInstance(){
        return mInstance;
    }
}
