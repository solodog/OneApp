package util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a17524.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.path;

/**
 * Created by 17524 on 2019/10/21.
 */

public class PlayMusicView extends FrameLayout {
    Context mContext;
    FrameLayout mFlplaymusic;
    View mview;
    TextView textView1,textView2;
    String mpath;
    int mduration;
    SeekBar seekBar;
    MediaPlayhelp mediaPlayhelp;
    ImageView imageView,abc,ann;
    List<data> mdata;
    boolean isplaying;
    Timer timer;
    Animation mplayMusicAin,mplayneedleAin,mspotneedleAin;
    public PlayMusicView(Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private void init(Context context){
        mContext =context;
        mview=LayoutInflater.from(mContext).inflate(R.layout.play,this,false);
        mFlplaymusic=(FrameLayout) mview.findViewById(R.id.fl_play_music) ;
        textView1=(TextView)mview.findViewById(R.id.lefttext);
        textView2=(TextView)mview.findViewById(R.id.righttext);
        seekBar=(SeekBar)mview.findViewById(R.id.seekBar2) ;
        abc=(ImageView)mview.findViewById(R.id.Abc);
        ann=(ImageView)mview.findViewById(R.id.imageView8) ;
        imageView=(ImageView)mview.findViewById(R.id.iv_icon) ;
        mplayMusicAin=(Animation) AnimationUtils.loadAnimation(mContext,R.anim.play_music);
        mplayneedleAin=(Animation)AnimationUtils.loadAnimation(mContext,R.anim.play_needle);
        mspotneedleAin=(Animation)AnimationUtils.loadAnimation(mContext,R.anim.spot_needle);
        mFlplaymusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addView(mview);
        mediaPlayhelp=MediaPlayhelp.getInstance(mContext);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Log.i("TAG:", "" + progress + "");
                int musicMax =mediaPlayhelp.getDuration(); //得到该首歌曲最长秒数
                int seekBarMax = seekBar.getMax();
                mediaPlayhelp.seekTo(musicMax * progress / seekBarMax);//跳到该曲该秒
                int musicdate=mediaPlayhelp.getCurrentPosition();
                String date=settext(musicdate);
                textView1.setText(date);
            }
        });
        mduration=mediaPlayhelp.getDuration();
        setdate(mduration);
        handler.post(run);
    }
    Handler handler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            //player.getCurrentPosition()获取音乐的当前进度
            seekBar.setProgress(mediaPlayhelp.getCurrentPosition()*seekBar.getMax()/mediaPlayhelp.getDuration());
            int musicdate=mediaPlayhelp.getCurrentPosition();
            String date=settext(musicdate);
            textView1.setText(date);
            handler.postDelayed(run, 100);
        }
    };

    void setdate(int mduration){
        String date=settext(mduration);
        textView2.setText(date);
    }

    public void trigger(ImageView imageView){
        if(isplaying){
            spotmusic();
            imageView.setImageResource(R.mipmap.play_music);
        }else {
            playmusic(mpath);
            imageView.setImageResource(R.mipmap.zt);
        }
    }
    public void playmusic(String path){
        mpath=path;
        isplaying=true;
        mFlplaymusic.startAnimation(mplayMusicAin);
        abc.startAnimation(mplayneedleAin);

        if(mediaPlayhelp.getpath()!=null&&mediaPlayhelp.getpath().equals(path)){
            mediaPlayhelp.statr();
        }else {
            mediaPlayhelp.setpath(path);
            mediaPlayhelp.setOnMediaListener(new MediaPlayhelp.onMediaListener() {
                @Override
                public void onprepared(MediaPlayer mp) {
                    mediaPlayhelp.statr();
                    mduration=mediaPlayhelp.getDuration();
                    setdate(mduration);
                }
                @Override
                public void onCompletion(MediaPlayer mp) {

                }
            });
        }
    }
    

    public void spotmusic(){
        isplaying=false;
        mFlplaymusic.clearAnimation();
        abc.startAnimation(mspotneedleAin);

        mediaPlayhelp.pause();
    }
    public void seticon(String icon){
        Glide.with(mContext)
                .load(icon)
                .into(imageView);
    }

    public String settext(int time) {
        SimpleDateFormat dateformate = new SimpleDateFormat("mm:ss");
        String t = dateformate.format(time);
        return t;
    }

    public void setdata(List<data> stu) {
        mdata=stu;
    }
}
