package com.example.a17524.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import static android.R.attr.bitmap;

public class XqjmActivity extends AppCompatActivity {
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xqjm);
        Intent intent=getIntent();
        bitmap=intent.getParcelableExtra("bitmap");
        ImageView imageView=(ImageView)findViewById(R.id.imageView9);
        imageView.setImageBitmap(bitmap);
    }
}
