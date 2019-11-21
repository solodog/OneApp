package com.example.a17524.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WecomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(dl()) {
                    Intent intent = new Intent(WecomeActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(WecomeActivity.this, DlActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }

    private boolean dl() {
        boolean result=false;
        SharedPreferences pf=getSharedPreferences("data",MODE_PRIVATE);
        String phone=pf.getString("phone",null);
        if(phone!=null){
            result=true;
        }
        return result;
    }
}
