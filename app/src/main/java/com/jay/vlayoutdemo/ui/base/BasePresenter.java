package com.jay.vlayoutdemo.ui.base;

import android.util.Log;

import com.jay.vlayoutdemo.data.http.ApiException;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/12/23.
 */

public class BasePresenter<T extends  BaseView> implements  Presenter<T> {
    protected   T mView;
    protected CompositeSubscription  mCompositeSubscription;
    @Override
    public void attachView(T mvpView) {
        mView=mvpView;
    }


    public String getErrorMsg(Throwable throwable){
        String errorMsg="";
        if (throwable instanceof ApiException) {
            ApiException apiException = (ApiException) throwable;
            errorMsg=apiException.getMessage();
        } else {
            errorMsg=throwable.getMessage();
        }
        Log.i("ray", "errormsg:" + throwable.getMessage());
        return errorMsg;

    }

    protected   void  addSubscrebe(Subscription subscription){
        if(mCompositeSubscription==null){
            mCompositeSubscription=new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    public void unSubscrebe(){
        if(mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
        }
    }


    @Override
    public void detachView() {
        this.mView=null;


    }
}
