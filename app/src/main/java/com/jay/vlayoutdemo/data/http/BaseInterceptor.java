package com.jay.vlayoutdemo.data.http;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/21.
 */

public class BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(request.method().equals("POST")){
            String params="";
            FormBody formBody = (FormBody) request.body();
            FormBody.Builder builder1=new FormBody.Builder();
            Request.Builder requestBuilder = request.newBuilder();
            for (int i = 0; i < formBody.size(); i++) {
                params+=formBody.encodedName(i)+"="+ formBody.encodedValue(i)+"&";
                builder1.addEncoded(formBody.encodedName(i),formBody.encodedValue(i));
            }
            if(!TextUtils.isEmpty(params)){
                params.substring(0,params.length()-1);
            }
            builder1.addEncoded("versionNumber","2.0.3");
            builder1.addEncoded("serviceVersion","1.0.4");
            builder1.addEncoded("platformId","2");
            requestBuilder.method(request.method(),builder1.build());
            request=requestBuilder.build();
            Log.i("retrofit","request:"+request.url().url().getPath()+"?"+params);
        }else{
            Log.i("retrofit","request:"+request.url().toString());
        }
        return chain.proceed(request);
    }
}
