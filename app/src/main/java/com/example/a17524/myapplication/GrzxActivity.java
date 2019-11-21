package com.example.a17524.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GrzxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzx);
        TextView textView=(TextView)findViewById(R.id.textView2);
        TextView textView1=(TextView)findViewById(R.id.textView3);
        Button button=(Button)findViewById(R.id.button3) ;
        Toolbar t=(Toolbar)findViewById(R.id.toolbar);
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        t.setTitle("个人中心");
        textView.setText("用户名："+sp.getString("phone",null));
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GrzxActivity.this,XgmmActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.remove("phone");
                editor.apply();
                Intent i=new Intent(GrzxActivity.this,DlActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}
