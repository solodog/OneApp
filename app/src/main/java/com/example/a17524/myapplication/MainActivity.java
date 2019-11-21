package com.example.a17524.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.MusicAdapter;
import adapter.StaggerAdapter;
import util.DataCleanManager;
import util.ItemBean;

public class MainActivity extends AppCompatActivity {
    RecyclerView mlist;
    List<ItemBean> mdata;
    SwipeRefreshLayout sw;
    NavigationView navigationView;
    int i=0;
    DrawerLayout drawerLayout;
    private Context mContext;
    StaggerAdapter sta;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlist=(RecyclerView)findViewById(R.id.Recycler);
        sw=(SwipeRefreshLayout)findViewById(R.id.Swipe);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout) ;
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        toolbar.setNavigationIcon(R.mipmap.to1);
        setSupportActionBar(toolbar);
        mdata=new ArrayList<>();
        initdata();
       showstagger(i);
       sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               i++;
               refe();
           }
       });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int m=item.getItemId();
                switch (m){
                    case R.id.q:
                        Toast.makeText(MainActivity.this,"one",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.w:

                        break;
                    case R.id.e:
                        sta.cleardata();
                        break;
                    case R.id.r:
                        Intent intent=new Intent(MainActivity.this,GrzxActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void refe() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (Fragment.InstantiationException e){
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mdata.clear();
                        initd();
                        showstagger(i);
                        sw.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initdata() {
        for(int i=0;i<10;i++){
        ItemBean data=new ItemBean();
            data.text="https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture";
            mdata.add(data);
        }
    }
    private void initd(){
        for(int i=0;i<10;i++){
            ItemBean data=new ItemBean();
            data.text="https://acg.toubiec.cn/random";
            mdata.add(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.one:
                showstagger(i);
                break;
            case R.id.two:
              Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.three:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void showstagger(int i){
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mlist.setLayoutManager(layoutManager);
        sta=new StaggerAdapter(mdata,mContext,i);
        sta.setHasStableIds(true);
        mlist.setAdapter(sta);
    }
}
