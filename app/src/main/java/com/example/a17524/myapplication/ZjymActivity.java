package com.example.a17524.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import adapter.ListAdapter;
import adapter.ZjlistAdapter;
import util.Httphelp;
import util.data;

public class ZjymActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView mlit;
    ZjlistAdapter listAdapter;
    List<data> mdate;
    String name;
    int i;
    public String data[]=new String[]{
            "热歌榜",
            "新歌榜",
            "网络歌曲榜",
            "影视金曲榜",
            "经典老歌榜",
            "欧美金曲榜",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjym);
        textView=(TextView)findViewById(R.id.textzj);
        mlit=(RecyclerView)findViewById(R.id.Recycler3);
        i=getIntent().getIntExtra("position",1);
        name=data[i];
        textView.setText(name);
        getdata(name);

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
