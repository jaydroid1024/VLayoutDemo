package com.jay.vlayoutdemo;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/22.
 */

public class GlobalData {
    public static String appVersion="";
    public static String  serviceVersion="";
    public static String  appName;
    public static String  jumpMarketUrl;
    public static String  authName;
    public static String  authPwd;
    public static String  gcmSendId;
    public static String  checkoutHashKey;
    public static String  gaTrackId;
    public static String  facebookId;
    public static String  serviceRequestUrl;
    public static String   downloadImageUrl;
    public static String  upLoadFileUrl;
    public static String useHlb="1";  //1  userHlb
    private  static Evn  currEvn= Evn.api;
    enum  Evn{
        appdev,api,uat,staging,live
    }

    public static void init(Context context){
        authName="gemfive";
        authPwd="gogogo";
        serviceRequestUrl=context.getString(R.string.service_url_appdev);
    }

}
