package com.jay.vlayoutdemo.data.http;


import com.jay.vlayoutdemo.model.UserInfoModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/16.serviceVersion=1.0.4&versionNumber=2.0.1 platformId=2
 */

public interface AccountApi {
    @FormUrlEncoded
    @POST("appservice/customer/login")
     Observable<UserInfoModel> login(@Field("email") String email, @Field("password") String password);
}
