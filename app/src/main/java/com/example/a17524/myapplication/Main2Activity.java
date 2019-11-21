package com.example.a17524.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.ListAdapter;
import adapter.MusicAdapter;
import util.Httphelp;
import util.data;
import util.listdatas;

public class Main2Activity extends AppCompatActivity {
    RecyclerView mlist;
    RecyclerView mlit;
    LinearLayout mone,mtwo;
    ListAdapter listAdapter;
    MusicAdapter musicAdapter;
    List<listdatas> mdata;
    List<data> mdate;
    public String data[]=new String[]{
            "http://hiphotos.qianqian.com/ting/pic/item/c83d70cf3bc79f3d98ca8e36b8a1cd11728b2988.jpg",
            "http://hiphotos.qianqian.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg",
            "http://hiphotos.qianqian.com/ting/pic/item/738b4710b912c8fca95d9ecbfe039245d688210d.jpg",
            "http://hiphotos.qianqian.com/ting/pic/item/f703738da97739121a5aed67fa198618367ae2bc.jpg",
            "http://hiphotos.qianqian.com/ting/pic/item/6f061d950a7b0208b85e57e760d9f2d3572cc825.jpg",
            "http://hiphotos.qianqian.com/ting/pic/item/8d5494eef01f3a291bf6bec89b25bc315c607cfd.jpg",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mlist=(RecyclerView)findViewById(R.id.Recycler1);
        mlit=(RecyclerView)findViewById(R.id.Recycler2);
        mone=(LinearLayout)findViewById(R.id.one);
        mtwo=(LinearLayout)findViewById(R.id.two);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.to1);
        setSupportActionBar(toolbar);
        mdata=new ArrayList<>();
        mdate=new ArrayList<>();
        initdata();
        showstag();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                 window.setStatusBarColor(Color.WHITE);
                 window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
             }
        getdata();
        mone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initdata() {
        for(int i=0;i<6;i++){
            listdatas data=new listdatas();
            data.bg=this.data[i];
            mdata.add(data);
        }
    }

    private void getdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Httphelp h=new Httphelp();
                ssw(h.getdata("最新"));
            }
        }).start();
    }
    void ssw(final List<data> date){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               mdate=date;
                showlist();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.one:
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.two:
                Intent intent1=new Intent(Main2Activity.this,GrzxActivity.class);
                startActivity(intent1);
                break;
            case R.id.ss:
                Intent intent2=new Intent(Main2Activity.this,SsjmActivity.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
    void showstag() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mlist.setLayoutManager(gridLayoutManager);
        musicAdapter = new MusicAdapter(this, mdata);
        mlist.setAdapter(musicAdapter);

    }
    void showlist(){
        mlit.setLayoutManager(new LinearLayoutManager(this));
        mlit.setNestedScrollingEnabled(false);
        listAdapter=new ListAdapter(this,mlit,mdate);
        mlit.setAdapter(listAdapter);
    }
}
