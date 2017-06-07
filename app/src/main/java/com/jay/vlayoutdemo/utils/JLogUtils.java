package com.jay.vlayoutdemo.utils;

import android.util.Log;


/**
 * Created by imaginato on 2015/6/10.
 */
public class JLogUtils {
    public static final boolean isDebug = true ;

    public static void i(String tag, String msg) {
        if (isDebug) {
                Log.i(tag, msg);
        }

    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isDebug) {
                Log.i(tag, msg, tr);
            }


    }

    public static void e(String tag, String msg) {
        if (isDebug) {
                Log.e(tag, msg);
        }

    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isDebug) {
                Log.e(tag, msg, tr);
        }

    }

    public static void d(String tag, String msg) {
        if (isDebug) {

                Log.d(tag, msg);
        }

    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isDebug) {
                Log.d(tag, msg, tr);
        }

    }

    public static void w(String tag, String msg) {
        if (isDebug) {
                Log.w(tag, msg);
        }

    }

    public static void w(String tag, String msg, Throwable tr) {
        if (isDebug) {
                Log.w(tag, msg, tr);
        }

    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isDebug) {
                Log.v(tag, msg, tr);
        }

    }

    public static void v(String tag, String msg) {
        if (isDebug) {
                Log.v(tag, msg);
        }

    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (isDebug) {
                Log.wtf(tag, msg, tr);
        }

    }

    public static void wtf(String tag, String msg) {
        if (isDebug) {
                Log.wtf(tag, msg);
        }
    }
    public static void json(String tag,int requestCode, String json) {
        if (isDebug) {
                Log.d(tag, requestCode+"-->"+json);
        }
    }
    public static void json(String tag, String json) {
        if (isDebug) {
                Log.d(tag,json);
        }
    }
    public static void xml(String tag,String xml) {
        if (isDebug) {
                Log.d(tag,xml);
        }
    }
}
