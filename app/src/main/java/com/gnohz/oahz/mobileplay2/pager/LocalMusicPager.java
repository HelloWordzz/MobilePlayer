package com.gnohz.oahz.mobileplay2.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gnohz.oahz.mobileplay2.Player;
import com.gnohz.oahz.mobileplay2.R;
import com.gnohz.oahz.mobileplay2.base.BasePager;
import java.util.ArrayList;

import static com.gnohz.oahz.mobileplay2.MusicService.positionmusic;


public class LocalMusicPager extends BasePager {
    private ListView listView;
    private TextView textView;
    private ProgressBar progressBar;
    public static ArrayList<MediaItem> list;
    private Mediaclassadapt mediaclassadapt3 = new Mediaclassadapt();

    public LocalMusicPager(Context context) {
        super(context);
    }



    @Override
    public View intiview() {
        View view = View.inflate(context, R.layout.activity_ametv, null);
        listView = view.findViewById(R.id.list_item);
        textView = view.findViewById(R.id.text_item);
        progressBar = view.findViewById(R.id.progress_item);
        return view;

    }


    @Override
    public void intidata() {
        super.intidata();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                mediaItems = new ArrayList<>();
//                ContentResolver contentResolver = context.getContentResolver();
//                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                String[] data = {
//                        MediaStore.Audio.Media.DISPLAY_NAME,
//                };
//                Cursor cursor = contentResolver.query(uri, data, null, null,null);
//                if(cursor!=null){
//                    while (cursor.moveToNext()){
//                        MediaItem mediaItem = new MediaItem();
//                        mediaItems.add(mediaItem);
//                        String stringname1 = cursor.getString(0);
//                        mediaItem.setItemname(stringname1);
//                    }
//                    cursor.close();
//                }
//                progressBar.setVisibility(View.GONE);
//                handler.sendEmptyMessage(1);
//
//
//
//            }
//        }.start();
        Utils();
//        handler.sendEmptyMessage(1);
    }

    class Mediaclassadapt extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {

            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewhold viewhold;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_activity_media, null);
                viewhold = new Viewhold();
                viewhold.item_text = convertView.findViewById(R.id.item_text);
                convertView.setTag(viewhold);
            } else {
                viewhold = (Viewhold) convertView.getTag();
            }
            MediaItem mediaItem2 = list.get(position);
            viewhold.item_text.setText(mediaItem2.getItemname());
            return convertView;
        }

        public class Viewhold {
            TextView item_text;
        }
    }

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (mediaItems != null && mediaItems.size() > 0) {
//                listView.setAdapter(mediaclassadapt3);
//                textView.setVisibility(View.GONE);
//            } else {
//                textView.setVisibility(View.VISIBLE);
//            }
//            progressBar.setVisibility(View.GONE);
//
//        }
//    };

    public void Utils() {
        list = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String[] data = {
//                MediaStore.Audio.Media.DISPLAY_NAME,
//        };
        Cursor cursor = contentResolver.query(uri, null, null, null,  MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MediaItem mediaItem1 = new MediaItem();
                String stringname1 = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                String st =  cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                mediaItem1.setItemname(stringname1);
                mediaItem1.setItemUri(st);
                list.add(mediaItem1);
            }
            cursor.close();
        }


        if (list != null && list.size() > 0) {
            listView.setAdapter(mediaclassadapt3);
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);

        /*
         * 定义Listview的点击事件
         *
         * */


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionmusic= (int) mediaclassadapt3.getItemId(position);
//                MediaItem mediaItem03 = list.get(i);
                Intent intent = new Intent();
//                String st1 = mediaItem03.getItemname();
//                String st2 = mediaItem03.getItemUri();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClassName("com.gnohz.oahz.mobileplay2","com.gnohz.oahz.mobileplay2.Player");
                Bundle bundle = new Bundle();
                bundle.putInt("data1",positionmusic);
//                bundle.putString("data2",st2);
                intent.putExtra("bun",bundle);
                context.startActivity(intent);

            }
        });
    }





}
