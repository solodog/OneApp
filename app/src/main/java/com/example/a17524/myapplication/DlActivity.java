package com.example.a17524.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.util.Util;

import java.util.List;

import io.realm.Realm;
import models.Usermodel;
import util.Realmutil;

public class DlActivity extends AppCompatActivity {
    EditText editText;
    EditText editText1;
    String phone="",passwrod="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl);
        Utils.init(getApplication());
        editText=(EditText)findViewById(R.id.editText2);
        editText1=(EditText)findViewById(R.id.editText3);
        TextView textView=(TextView)findViewById(R.id.textView);
        Button button=(Button)findViewById(R.id.button) ;
        Realm.init(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DlActivity.this,ZcActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=editText1.getText().toString();
                passwrod= EncryptUtils.encryptMD5ToString(editText.getText().toString());
                if(!RegexUtils.isMobileExact(editText1.getText())){
                    Toast.makeText(DlActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(DlActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(cx()){
                    Toast.makeText(DlActivity.this, "未注册", Toast.LENGTH_SHORT).show();
                }else if(RegexUtils.isMobileExact(editText1.getText())&&!TextUtils.isEmpty(editText.getText()) && !cx()){
                    if(yz(phone,passwrod)){
                        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("phone",phone);
                        editor.apply();
                        Intent intent=new Intent(DlActivity.this,Main2Activity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(DlActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public boolean cx(){
        Realmutil realmutil=new Realmutil();
        List<Usermodel> po= realmutil.getalluser();
        for(int i=0;i<po.size();i++) {
            if (po.get(i).getPhone().equals(editText1.getText().toString())) {
                return false;
            }
        }
        return true;
    }
    public boolean yz(String phone,String passwrod){
        boolean yzi;
        Realmutil realmutil=new Realmutil();
        yzi=realmutil.yzi(phone,passwrod);
        realmutil.close();
        return yzi;
    }

}
