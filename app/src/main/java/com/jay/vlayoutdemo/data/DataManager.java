package com.jay.vlayoutdemo.data;

import android.text.TextUtils;

import com.jay.vlayoutdemo.Constants;
import com.jay.vlayoutdemo.GlobalData;
import com.jay.vlayoutdemo.data.http.AccountApi;
import com.jay.vlayoutdemo.data.http.BaseInterceptor;
import com.jay.vlayoutdemo.data.http.CheckoutApi;
import com.jay.vlayoutdemo.data.http.OthersApi;
import com.jay.vlayoutdemo.data.http.ProductApi;
import com.jay.vlayoutdemo.data.http.ShoppingCartApi;
import com.jay.vlayoutdemo.data.preferces.PreferHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataManager {
    private static OkHttpClient  mOkHttpClient;
    private static DataManager mHttpManage;
    private AccountApi mAccountApi;
    private CheckoutApi mCheckoutApi;
    private OthersApi mOtherApi;
    private ProductApi mProductApi;
    private ShoppingCartApi mShoppingCartApi;
    private PreferHelper preferHelper;
    private Object monitor = new Object();



    private DataManager(){
        initOkHttpClient();
    }
    public static DataManager getInstance(){
        if (mHttpManage == null) {
            synchronized (DataManager.class) {
                if (mHttpManage == null) {
                    mHttpManage = new DataManager();
                }
            }
        }
        return mHttpManage;
    }

    public PreferHelper getPreferHelper(){
        if(preferHelper==null){
            preferHelper=new PreferHelper();
        }
        return preferHelper;

    }


    private  void  initOkHttpClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Constants.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(new BaseInterceptor());
        if(!TextUtils.isEmpty(GlobalData.appName)&&!TextUtils.isEmpty(GlobalData.authPwd)){
            builder.authenticator(new okhttp3.Authenticator() {
                @Override
                public Request authenticate(Route route, okhttp3.Response response) throws IOException {
                    String credential = Credentials.basic(GlobalData.appName, GlobalData.authPwd);
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            });
        }
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);
        mOkHttpClient=builder.build();
    }

    public AccountApi getAccountApi(){
        if (mAccountApi==null){
            synchronized (monitor){
                if (mAccountApi==null){
                    mAccountApi= getRetrofit().create(AccountApi.class);
                }
            }
        }
        return mAccountApi;
    }
    public CheckoutApi getCheckoutApi(){
        if (mCheckoutApi==null){
            synchronized (monitor){
                if (mCheckoutApi==null){
                    mCheckoutApi= getRetrofit().create(CheckoutApi.class);
                }
            }
        }
        return mCheckoutApi;
    }

    public OthersApi getOtherApi(){
        if (mOtherApi==null){
            synchronized (monitor){
                if (mOtherApi==null){
                    mOtherApi= getRetrofit().create(OthersApi.class);
                }
            }
        }
        return mOtherApi;
    }

    public ProductApi getProductApi(){
        if (mProductApi==null){
            synchronized (monitor){
                if (mProductApi==null){
                    mProductApi= getRetrofit().create(ProductApi.class);
                }
            }
        }
        return mProductApi;
    }
    public ShoppingCartApi getShoppingCartApi(){
        if (mShoppingCartApi==null){
            synchronized (monitor){
                if (mShoppingCartApi==null){
                    mShoppingCartApi= getRetrofit().create(ShoppingCartApi.class);
                }
            }
        }
        return mShoppingCartApi;
    }
    private Retrofit  getRetrofit(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GlobalData.serviceRequestUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
