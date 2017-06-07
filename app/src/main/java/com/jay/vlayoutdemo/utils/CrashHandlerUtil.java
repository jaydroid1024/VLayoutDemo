package com.jay.vlayoutdemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.jay.vlayoutdemo.R;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Properties;

public class CrashHandlerUtil implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandlerUtil";
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";

    private static final String CRASH_REPORTER_EXTENSION = ".cr";

    private static CrashHandlerUtil INSTANCE;

    private UncaughtExceptionHandler mDefaultHandler;

    private Context mContext;

    private Properties mDeviceCrashInfo = new Properties();


    private CrashHandlerUtil() {
    }


    public static CrashHandlerUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandlerUtil();
        }
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

    private Handler mHandler=new Handler();
    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (JLogUtils.isDebug) {
            mDefaultHandler.uncaughtException(thread, ex);
            return;
        }


        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            JLogUtils.w(TAG, "handleException --- ex==null");
            return true;
        } else {
            JLogUtils.w(TAG, "handleException --- ex==null", ex);
        }
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        printWriter.close();
        sendCrashReportsToServer(result);

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, mContext.getResources().getString(R.string.app_crash_friendly_hint), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        if (result == null) {
            return false;
        }

        collectCrashDeviceInfo(mContext);
        return true;
    }
    private void sendCrashReportsToServer(String msg) {
//        OtherDao crashDao=new OtherDao("CrashHandlerUtil",mHandler);
//        String servercommonaddress = GemfiveApplication.getAppConfiguration().getHttpServerAddress();
//        String servercommonparameter = GemfiveApplication.getAppConfiguration().getHttpGlobalParameter();
//        if (TextUtils.isEmpty(servercommonaddress)) {
//            return;
//        }
//        StringBuffer sb = new StringBuffer();
//        try {
//            PackageManager pm = mContext.getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
//            if (pi != null) {
//                sb.append("\nVersonName: " + (pi.versionName == null ? "not set" : pi.versionName));
//                sb.append("\nVersonCode: " + ("" + pi.versionCode));
//            }
//        } catch (Exception e) {
//            JLogUtils.e(TAG, "sendCrashReportsToServer", e);
//        }
//        sb.append("\nOS-VERSION: Android" + Build.VERSION.RELEASE);
//        sb.append("\nMOBILE: " + Build.MODEL);
//        sb.append("\nERROR: " + msg);
//        msg = sb.toString();
//
//        String svrUrl = servercommonaddress + "/appservice/crash/log?";
//        ConcurrentHashMap<String, String> svrParams = new ConcurrentHashMap<String, String>();
//        svrParams.put("crash_text", msg.replace("=", ":"));
//        svrParams.put("client", "1");//1 means android
//        try {
//            if (!TextUtils.isEmpty(servercommonparameter)) {
//                String[] globalparameterlist = servercommonparameter.split("&");
//                if (globalparameterlist != null && globalparameterlist.length > 0) {
//                    int length = globalparameterlist.length;
//                    for (int index = 0; index < length; ++index) {
//                        String tmpparameter = globalparameterlist[index];
//                        if (!TextUtils.isEmpty(tmpparameter)) {
//                            try {
//                                String[] keyvalue = tmpparameter.split("=");
//                                if (keyvalue != null && keyvalue.length == 2) {
//                                    final String keystring = keyvalue[0];
//                                    final String valuestring = keyvalue[1];
//                                    svrParams.put(keystring, valuestring);
//                                }
//                            } catch (Exception ex) {
//                                JLogUtils.e(TAG, "sendCrashReportsToServer", ex);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            JLogUtils.e(TAG, "sendCrashReportsToServer", ex);
//        }
//        crashDao.sendCrash(svrParams);
    }

    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, "" + pi.versionCode);
            }
        } catch (NameNotFoundException e) {
            JLogUtils.e(TAG, "collectCrashDeviceInfo", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), "" + field.get(null));
                if (JLogUtils.isDebug) {
                    JLogUtils.d(TAG, field.getName() + " : " + field.get(null));
                }
            } catch (Exception e) {
                JLogUtils.e(TAG, "Error while collect crash info", e);
            }
        }
    }

}