package com.jay.vlayoutdemo.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jay.vlayoutdemo.R;
import com.jay.vlayoutdemo.model.UserInfoModel;
import com.jay.vlayoutdemo.ui.base.BaseActivity;
import com.jay.vlayoutdemo.ui.product.ProductActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//
public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.tv_curr_info)
    TextView tvCurrInfo;
    private MainPresenter mainPresenter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(MainActivity.this);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getUserInfo();
    }



    public void setUserInfoView(UserInfoModel userInfo){
        if(userInfo==null){
            tvCurrInfo.setVisibility(View.GONE);
        }else{
            tvCurrInfo.setVisibility(View.VISIBLE);
            tvCurrInfo.setText("current User:\n name:"+userInfo.getFirstName()+" "+userInfo.getLastName()+"\n Email:"+userInfo.getEmail());
        }
    }

    @Override
    public void showProgressDialog() {
        mDialog = ProgressDialog.show(this, "", "loading");
    }

    @Override
    public void dimssProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    //    @Override
    public void startHomePage() {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

    public void showErrorMsg(String errorMsg) {
        Toast.makeText(MainActivity.this, "" + errorMsg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_save)
    public void onClick() {
        String username = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        showProgressDialog();
        mainPresenter.login(username, pwd);
    }
}
