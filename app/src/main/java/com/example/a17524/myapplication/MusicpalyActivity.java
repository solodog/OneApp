package com.example.a17524.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import util.MediaPlayhelp;
import util.PlayMusicView;
import util.data;

public class MusicpalyActivity extends AppCompatActivity {
    Context mContext;
    ImageView mimageView,imageView,imageView1,imageView2;
    TextView textView,textView1;
    MediaPlayhelp mediaPlayhelp;
    PlayMusicView playMusicView;
    RelativeLayout mFlplaymusic;
    int i;
    public String pic,url,title;
    List<data> stu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicpaly);
        mediaPlayhelp=MediaPlayhelp.getInstance(mContext);
        stu = (List<data>) getIntent().getSerializableExtra("mdata");
        imageView=(ImageView)findViewById(R.id.imageView8);
        imageView1=(ImageView)findViewById(R.id.imageView10);
        imageView2=(ImageView)findViewById(R.id.imageView11);
        i = getIntent().getIntExtra("position", 0);
        initview();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusicView.trigger(imageView);
            }
        });
        mediaPlayhelp.setOnMediaListener(new MediaPlayhelp.onMediaListener() {
            @Override
            public void onprepared(MediaPlayer mp) {
                i++;
            }

            @Override
            public void onCompletion(MediaPlayer mp) {
                if(i==stu.size()){
                    i=0;
                }
               initview();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sets();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setx();
            }
        });
    }
    void initview(){
        mimageView=(ImageView)findViewById(R.id.iv_bg);
        textView=(TextView)findViewById(R.id.textView7);
        textView.setText( stu.get(i).title);
        Glide.with(this)
                .load(stu.get(i).pic)
                .bitmapTransform(new BlurTransformation(this, 14, 3))
                .into(mimageView);
        playMusicView=(PlayMusicView)findViewById(R.id.play_music_view);
        playMusicView.playmusic(stu.get(i).url);
        playMusicView.seticon(stu.get(i).pic);
    }
    void sets(){
        if(i==0){
            i=stu.size()-1;
        }else {
            i--;
        }
        initview();
    }
    void setx(){
        if(i==stu.size()-1){
            i=0;
        }else {
            i++;
        }
        initview();
    }
}
