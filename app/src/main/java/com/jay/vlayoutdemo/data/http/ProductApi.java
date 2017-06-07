package com.jay.vlayoutdemo.data.http;


import com.jay.vlayoutdemo.model.ResponseModel;
import com.jay.vlayoutdemo.model.SVRAppserviceLandingPagesListLandingPageItemReturnEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface ProductApi {

    @GET("appservice/landingPages/list")
    Observable<ResponseModel<List<SVRAppserviceLandingPagesListLandingPageItemReturnEntity>>> getLandingPages(@Query("category_id") String id);

}
