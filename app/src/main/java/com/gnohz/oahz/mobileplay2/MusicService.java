package com.gnohz.oahz.mobileplay2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.HashMap;

import static com.gnohz.oahz.mobileplay2.Player.music_uri;
import static com.gnohz.oahz.mobileplay2.pager.LocalMusicPager.list;


public class MusicService extends Service {
    private ArrayList<String> list1;
    public static int positionmusic;
    public final IBinder binder = new MyBinder();
    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    //    public static int isReturnTo = 0;
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    //    public static ObjectAnimator animator;
    public MusicService() {
        initMediaPlayer();
    }


    public void initMediaPlayer() {
        try {
            //String file_path = "/storage/0123-4567/K.Will-Melt.mp3";
//            String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/123.mp3";
            //String file_path = "/data/K.Will-Melt.mp3";
            list1 = new ArrayList<>();
            for(int i = 0; i<list.size(); i++){
                list1.add(list.get(i).getItemUri());
            }
            mediaPlayer.setDataSource(list1.get(positionmusic));
            mediaPlayer.prepare();

            mediaPlayer.setLooping(true);  // 设置循环播放
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void starmusic(){
        mediaPlayer.start();
    }
    public void stopmuscic(){
        mediaPlayer.pause();
    }
    public void lastmusic(){

        positionmusic--;
        initMediaPlayer();
        mediaPlayer.start();
    }
    public void nextmusic(){

        positionmusic++;
        initMediaPlayer();
        mediaPlayer.start();
    }




    private int flag = 0;
    public static String which = "";


    @Override
    public void onDestroy() {

        mediaPlayer.release();
        super.onDestroy();
    }
    /**
     * onBind 是 Service 的虚方法，因此我们不得不实现它。
     * 返回 null，表示客服端不能建立到此服务的连接。
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}