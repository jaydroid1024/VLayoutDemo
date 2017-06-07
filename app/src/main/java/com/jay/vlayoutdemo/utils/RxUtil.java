package com.jay.vlayoutdemo.utils;

import android.util.Log;

import com.jay.vlayoutdemo.data.http.ApiException;
import com.jay.vlayoutdemo.model.ResponseModel;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/16.
 */

public class RxUtil {

    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
    public static <T> Observable.Transformer<ResponseModel<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<ResponseModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<ResponseModel<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<ResponseModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResponseModel<T> tGankHttpResponse) {//errorMessage
                        Log.i("ray","tGankHttpResponse.getStatus():"+tGankHttpResponse.getStatus());
                        if(tGankHttpResponse.getStatus()==1) {
                            return createData(tGankHttpResponse.getLandingList());
                        } else {
                            return Observable.error(new ApiException(tGankHttpResponse.getErrorMessage()));
                        }
                    }
                });
            }
        };
    }

}
