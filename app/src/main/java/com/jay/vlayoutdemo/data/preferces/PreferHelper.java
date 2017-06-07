package com.jay.vlayoutdemo.data.preferces;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jay.vlayoutdemo.App;
import com.jay.vlayoutdemo.model.UserInfoModel;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Administrator on 2016/12/22.
 */

public class PreferHelper {
    private String preferFileName="appdata";

    private static  final String FIELD_USER="user";
    private SharedPreferences getSharedPreferences(){
        return App.getInstance().getSharedPreferences(preferFileName, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor(){
        SharedPreferences sharedPreferences=getSharedPreferences();
        return  sharedPreferences.edit();
    }
    public void savaUser(UserInfoModel appdata){
        SharedPreferences.Editor editor=getEditor();
        String user= new Gson().toJson(appdata);
        editor.putString(FIELD_USER,user);
        editor.apply();
    }

    public Observable<UserInfoModel> getUserInfo(){
        Observable<UserInfoModel> observable=Observable.fromCallable(new Func0<UserInfoModel>() {
            @Override
            public UserInfoModel call() {

                SharedPreferences sharedPreferences=getSharedPreferences();
                String userJson= sharedPreferences.getString(FIELD_USER,"");
                if(!TextUtils.isEmpty(userJson)){
                    return new Gson().fromJson(userJson,UserInfoModel.class);
                }
                return null;
            }
        });
        return observable;
    }
}
