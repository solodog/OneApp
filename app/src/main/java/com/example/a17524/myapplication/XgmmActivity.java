package com.example.a17524.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.StringUtils;

import io.realm.Realm;
import models.Usermodel;
import util.Realmutil;

public class XgmmActivity extends AppCompatActivity {
    String password,password1,password2,ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xgmm);
        final EditText editText=(EditText)findViewById(R.id.editText6);
        final EditText editText1=(EditText)findViewById(R.id.editText7);
        final EditText editText2=(EditText)findViewById(R.id.editText8);
        Button button=(Button)findViewById(R.id.button4);
        Realm.init(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realmutil r=new Realmutil();
                SharedPreferences s=getSharedPreferences("data",MODE_PRIVATE);
                ph=s.getString("phone",null);
                password=editText.getText().toString();
                password1=editText1.getText().toString();
                password2=editText2.getText().toString();
                if(StringUtils.isEmpty(password)){
                    Toast.makeText(XgmmActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
                }else if(StringUtils.isEmpty(password1)){
                    Toast.makeText(XgmmActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if(StringUtils.isEmpty(password2)){
                    Toast.makeText(XgmmActivity.this,"请输入确认密码",Toast.LENGTH_SHORT).show();
                }else if(!password1.equals(password2)){
                    Toast.makeText(XgmmActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                }else if(password1.equals(password)){
                    Toast.makeText(XgmmActivity.this,"新密码与原密码不能相同",Toast.LENGTH_SHORT).show();
                }else if(!r.yzi(ph, EncryptUtils.encryptMD5ToString(password))){
                    Toast.makeText(XgmmActivity.this,"原密码错误",Toast.LENGTH_SHORT).show();
                    r.close();
                }else {
                    Usermodel usermodel=new Usermodel();
                    usermodel.setPhone(ph);
                    usermodel.setPassword(EncryptUtils.encryptMD5ToString(password1));
                    xgmm(usermodel);
                    Toast.makeText(XgmmActivity.this,"完成",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void xgmm(Usermodel usermodel) {
        Realmutil realmutil=new Realmutil();
        realmutil.savedata(usermodel);
        realmutil.close();

    }
}
