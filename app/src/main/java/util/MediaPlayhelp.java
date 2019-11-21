package util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by 17524 on 2019/10/23.
 */

public class MediaPlayhelp  {
    private Context mContext;
    public static MediaPlayhelp instance;
    private MediaPlayer mediaPlayr;
    private onMediaListener onMediaListener;
    private String mpath;
    int duration=10;
    String mdurations;
    public static MediaPlayhelp getInstance(Context context){
        if(instance ==null){
            synchronized (MediaPlayhelp.class){
                if(instance==null){
                    instance=new MediaPlayhelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayhelp(Context context){
        mContext=context;
        mediaPlayr=new MediaPlayer();
    }

        public void setpath(String path){

        //重置
            if(mediaPlayr.isPlaying()||!path.equals(mpath)){
                mediaPlayr.reset();

            }
            mpath=path;
        //设置路径
        try {
            mediaPlayr.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //准备开始
        mediaPlayr.prepareAsync();
        mediaPlayr.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(onMediaListener!=null){
                    onMediaListener.onprepared(mp);
                }
            }
        });
        mediaPlayr.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(onMediaListener!=null){
                    onMediaListener.onCompletion(mp);
                }
            }
        });
    }

        public void  statr(){
            if(mediaPlayr.isPlaying()) return ;
            mediaPlayr.start();
        }

        public void pause(){
            mediaPlayr.pause();
        }

        public String getpath(){
            return mpath;
        }
    public void setOnMediaListener(MediaPlayhelp.onMediaListener onMediaListener) {
        this.onMediaListener = onMediaListener;
    }

    public int getDuration() {
        int ubmer=mediaPlayr.getDuration();
        return ubmer;
    }

    public int getCurrentPosition() {
        int ubmer=mediaPlayr.getCurrentPosition();
        return ubmer;
    }

    public void seekTo(int i) {
        mediaPlayr.seekTo(i);
    }

    public interface onMediaListener{
        void onprepared(MediaPlayer mp);
        void onCompletion(MediaPlayer mp);
    }
}
