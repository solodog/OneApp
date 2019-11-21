package com.example.a17524.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import adapter.ZjlistAdapter;
import util.Httphelp;
import util.data;

public class SsjmActivity extends AppCompatActivity {
    ZjlistAdapter listAdapter;
    RecyclerView mlit;
    List<data> mdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssjm);
        final EditText editText=(EditText)findViewById(R.id.editText9);
        ImageView imageView=(ImageView)findViewById(R.id.imageView7);
        mlit=(RecyclerView)findViewById(R.id.Recycler4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText.getText().toString();
                getdata(name);
            }
        });

    }

    private void getdata(final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Httphelp h=new Httphelp();
                ssw(h.getdata(name));
            }
        }).start();
    }

    private void ssw(final List<data> data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mdate=data;
                showlist();
            }
        });
    }

    private void showlist() {
        mlit.setLayoutManager(new LinearLayoutManager(this));
        mlit.setNestedScrollingEnabled(false);
        listAdapter=new ZjlistAdapter(this,mlit,mdate);
        mlit.setAdapter(listAdapter);
    }
}
