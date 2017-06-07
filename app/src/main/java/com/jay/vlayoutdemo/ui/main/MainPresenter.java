package com.jay.vlayoutdemo.ui.main;

import android.text.TextUtils;

import com.jay.vlayoutdemo.data.DataManager;
import com.jay.vlayoutdemo.model.UserInfoModel;
import com.jay.vlayoutdemo.ui.base.BasePresenter;
import com.jay.vlayoutdemo.utils.RxUtil;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/12/23.
 */
public class MainPresenter extends BasePresenter<MainMvpView> {
    public MainPresenter(MainMvpView view){
        this.mView=view;
    }
    public void  getUserInfo(){
       Observable<UserInfoModel> observable= DataManager.getInstance().getPreferHelper().getUserInfo();
         observable.compose(RxUtil.<UserInfoModel>rxSchedulerHelper()).subscribe(new Action1<UserInfoModel>() {
             @Override
             public void call(UserInfoModel userInfoModel) {
                 mView.setUserInfoView(userInfoModel);
             }
         });
    }
    public void login(String username,String pwd){
        if(checkData(username,pwd)){
          DataManager.getInstance().getAccountApi().login(username,pwd)
                     .compose(RxUtil.<UserInfoModel>rxSchedulerHelper())
                    .subscribe(new Action1<UserInfoModel>() {
                @Override
                public void call(UserInfoModel userInfoModel) {
                    mView.dimssProgressDialog();
                    DataManager.getInstance().getPreferHelper().savaUser(userInfoModel);
                    mView.startHomePage();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                     mView.showErrorMsg(getErrorMsg(throwable));
                }
            });
        }
    }
    private boolean checkData(String username,String pwd){
        if(TextUtils.isEmpty(username)){
            mView.showErrorMsg("username is not  empty");
            return false ;
        }
        if(TextUtils.isEmpty(pwd)){
            mView.showErrorMsg("pwd is not empty");
            return false;
        }
        return true;
    }
}
