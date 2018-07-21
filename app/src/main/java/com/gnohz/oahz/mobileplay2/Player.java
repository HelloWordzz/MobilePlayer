package com.gnohz.oahz.mobileplay2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


import java.text.SimpleDateFormat;

import static com.gnohz.oahz.mobileplay2.pager.FindMusicPager.mediaItems;
import static com.gnohz.oahz.mobileplay2.pager.LocalMusicPager.list;

public class Player extends Activity {

//    private Button isPlay;
//    private Button quit;
    private TextView textView;
    private TextView totalTime;
    private TextView playingTime;
    private ImageButton imageButton;
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;



    private SeekBar seekBar;

    protected static String music_uri;
    private int music_position;
    private int music_netposition;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        textView = findViewById(R.id.musicname);
        Intent intent = getIntent();
        if(intent.getExtras().containsKey("bun")){
            Bundle bundle = intent.getBundleExtra("bun");
            music_position = bundle.getInt("data1");
            textView.setText(list.get(music_position).getItemname());
        }
        if(intent.getExtras().containsKey("bunnet")){
            Bundle bundle = intent.getBundleExtra("bunnet");
            music_netposition = bundle.getInt("datanet");
            textView.setText(mediaItems.get(music_netposition).getItemname());
        }



//         data03 = bundle.getString("data3");
//         data04 = bundle.getString("data4");
//         data05 = bundle.getString("data5");
//         data06 = bundle.getString("data6");
//        music_name = data01;
//        music_uri = data02;
        bindServiceConnection();
        musicService = new MusicService();


//        isPlay =  findViewById(R.id.playbutton);
//        isPlay.setOnClickListener(new myOnClickListener());
        imageButton =  findViewById(R.id.imagebutton);
        imageButton1 = findViewById(R.id.last_music);
        imageButton2 = findViewById(R.id.star_music);
        imageButton3 = findViewById(R.id.pause_music);
        imageButton4 = findViewById(R.id.next_music);

//        quit =  findViewById(R.id.pausebutton);
//        quit.setOnClickListener(new myOnClickListener());
        imageButton1.setOnClickListener(new myOnClickListener());
        imageButton2.setOnClickListener(new myOnClickListener());
        imageButton3.setOnClickListener(new myOnClickListener());
        imageButton4.setOnClickListener(new myOnClickListener());
        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
        seekBar.setMax(musicService.mediaPlayer.getDuration());
        totalTime = findViewById(R.id.totalTime);
        playingTime =  findViewById(R.id.playingTime);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });




    }

    private MusicService musicService;
    private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
    private ServiceConnection sc = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicService = ((MusicService.MyBinder) iBinder).getService();
        }


        public void onServiceDisconnected(ComponentName componentName) {
            musicService = null;
        }
    };

    private void bindServiceConnection() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, sc, this.BIND_AUTO_CREATE);
    }
    public Handler handler = new Handler();
    public Runnable runnable = new Runnable() {

        public void run() {


            if(musicService.mediaPlayer.isPlaying()) {
//                stateText.setText("Playing");
            } else {
                if (musicService.which.equals("stop"))  {
//                    stateText.setText("Stop");
                } else if (musicService.which.equals("pause")){
//                    stateText.setText("Pause");
                }
            }
            playingTime.setText(time.format(musicService.mediaPlayer.getCurrentPosition()));
            totalTime.setText(time.format(musicService.mediaPlayer.getDuration()));
            seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        musicService.mediaPlayer.seekTo(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            handler.postDelayed(runnable, 100);
        }
    };

    protected void onResume() {

        if(musicService.mediaPlayer.isPlaying()) {
//            stateText.setText("Playing");
        } else {
            if (musicService.which.equals("stop"))  {
//                stateText.setText("Stop");
            } else if (musicService.which.equals("pause")){
//                stateText.setText("Pause");
            }
        }
        seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
        seekBar.setMax(musicService.mediaPlayer.getDuration());
        handler.post(runnable);
        super.onResume();
        Log.d("hint", "handler post runnable");
    }

    private class myOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.last_music:
                    musicService.lastmusic();
                    break;
                case R.id.star_music:
                    musicService.starmusic();
                    break;
                case R.id.pause_music:
                    changeStop();
                    musicService.stopmuscic();
                    break;
                case R.id.next_music:
                    musicService.nextmusic();
                    break;

                default:
                    break;
            }
        }
    }




    private void changeStop() {
//        stateText.setText("Stop");
        seekBar.setProgress(0);
        //animator.pause();
    }

    private void quit() {
        handler.removeCallbacks(runnable);
//        unbindService(sc);
        try {
            finish();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroy() {
        unbindService(sc);
        super.onDestroy();
    }


}