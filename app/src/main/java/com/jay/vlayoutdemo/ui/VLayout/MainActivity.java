package com.jay.vlayoutdemo.ui.VLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jay.vlayoutdemo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.linearLayoutHelper:
                startActivity(new Intent(this, LinearLayoutHelperActivity.class));
                break;
            case R.id.gridLayoutHelper:
                startActivity(new Intent(this, GridLayoutHelperActivity.class));
                break;
            case R.id.staggeredGridLayoutHelper:
                startActivity(new Intent(this, StaggeredGridLayoutHelperActivity.class));
                break;
            case R.id.fixLayoutHelper:
                startActivity(new Intent(this, FixLayoutHelperActivity.class));
                break;
            case R.id.scrollFixLayoutHelper:
                startActivity(new Intent(this, ScrollFixLayoutHelperActivity.class));
                break;
            case R.id.columnLayoutHelper:
                startActivity(new Intent(this, ColumnLayoutHelperActivity.class));
                break;
            case R.id.singleLayoutHelper:
                startActivity(new Intent(this, SingleLayoutHelperActivity.class));
                break;
            case R.id.onePlusNLayoutHelper:
                startActivity(new Intent(this, OnePlusNLayoutHelperActivity.class));
                break;
            case R.id.floatLayoutHelper:
                startActivity(new Intent(this, FloatLayoutHelperActivity.class));
                break;
            case R.id.stickyLayoutHelper:
                startActivity(new Intent(this, StickyLayoutHelperActivity.class));
                break;
            case R.id.all:
                startActivity(new Intent(this,AllActivity.class));
                break;
            case R.id.jd_normal:
                startActivity(new Intent(this,AllActivity.class));
                break;
            case R.id.jd_vlayout:
                startActivity(new Intent(this,AllActivity.class));
                break;
        }
    }


}
