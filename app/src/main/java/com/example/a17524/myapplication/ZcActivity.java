package com.example.a17524.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import io.realm.Realm;
import models.Usermodel;
import util.Realmutil;

public class ZcActivity extends AppCompatActivity {
    public Context mContext;
    EditText editText;
    EditText editText1;
    EditText editText2;
    boolean qwer=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        Button button = (Button) findViewById(R.id.button2);
        editText=(EditText)findViewById(R.id.editText) ;
        editText1=(EditText)findViewById(R.id.editText4) ;
        editText2=(EditText)findViewById(R.id.editText5) ;
        editText.toString();
        Realm.init(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qr()){
                    Usermodel usermodel = new Usermodel();
                    usermodel.setPhone(editText.getText().toString());
                    usermodel.setPassword(EncryptUtils.encryptMD5ToString(editText1.getText().toString()));
                    savedata(usermodel);
                    Intent intent=new Intent(ZcActivity.this,DlActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean qr() {
        if (!RegexUtils.isMobileExact(editText.getText())) {
            Toast.makeText(ZcActivity.this,"格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pd()){
            Toast.makeText(ZcActivity.this, "该账号已注册", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(editText1.getText().toString())) {
            Toast.makeText(ZcActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!editText1.getText().toString().equals(editText2.getText().toString())){
            Toast.makeText(ZcActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return qwer;
    }

    private boolean pd() {
        Realmutil realmutil=new Realmutil();
        List<Usermodel> po=realmutil.getalluser();
        for(int i=0;i<po.size();i++) {
            if (po.get(i).getPhone().equals(editText.getText().toString())) {
                return true;
            }
        }
        return false;
    }

    public void savedata(Usermodel usermodel){
        Realmutil realmutil=new Realmutil();
        realmutil.savedata(usermodel);
        realmutil.close();
    }
}