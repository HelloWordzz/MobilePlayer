package com.gnohz.oahz.mobileplay2.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.gnohz.oahz.mobileplay2.R;
import com.gnohz.oahz.mobileplay2.Utils.Constants;
import com.gnohz.oahz.mobileplay2.base.BasePager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class FindMusicPager extends BasePager {
    @ViewInject(R.id.find_music)
    private ListView list;

   @ViewInject(R.id.find_text)
    private TextView findtextView;

    @ViewInject(R.id.find_progressbar)
    private ProgressBar findprogressBar;
    public static ArrayList<MediaItem> mediaItems  = new ArrayList<>();
    private Mediaclassadapt1 mediaclassadapt2 = new Mediaclassadapt1();
    public static int positionnetmusic;





    public FindMusicPager(Context context) {
            super(context);
        }


        @Override
        public View intiview() {
            View view = View.inflate(context, R.layout.activity_findmusic, null);
            x.view().inject(FindMusicPager.this,view);
        return view;
    }

    @Override
    public void intidata() {
        super.intidata();

        RequestParams requestParams = new RequestParams(Constants.NET_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//Toast.makeText(context,result+"",Toast.LENGTH_LONG).show();
                processData(result);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

//            MediaItem mediaItem2 = mediaItems.get(0);
//            qqq = mediaItem2.getItemname();
//            Toast.makeText(context, qqq, Toast.LENGTH_LONG).show();

//        music1.setText(""+qqq);
//        findprogressBar.setVisibility(View.GONE);
        list.setAdapter(mediaclassadapt2);
        findtextView.setVisibility(View.GONE);
        findprogressBar.setVisibility(View.GONE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionnetmusic= (int) mediaclassadapt2.getItemId(position);
//                MediaItem mediaItem03 = mediaItems.get(0);
                Intent intent = new Intent();
//                String st1 = mediaItem03.getItemname();
//                String st2 = mediaItem03.getItemUri();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClassName("com.gnohz.oahz.mobileplay2","com.gnohz.oahz.mobileplay2.Player");
                Bundle bundle = new Bundle();
                bundle.putInt("datanet",positionnetmusic);
                intent.putExtra("bunnet",bundle);
                context.startActivity(intent);

            }
        });


    }

    private void processData(String result) {

//        parsejson(result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonArray = jsonObject.optJSONObject("data");
//            int a = jsonArray.length();
//////            String b = a+"";
//////            Toast.makeText(context,b+"",Toast.LENGTH_LONG).show();



                    MediaItem mediaItem = new MediaItem();
                    String musicname = jsonArray.optString("song_name");
                    mediaItem.setItemname(musicname);
                    String musicurl = jsonArray.optString("play_url");
                    mediaItem.setItemUri(musicurl);
                    mediaItems.add(mediaItem);



        } catch (JSONException e) {
            e.printStackTrace();
        }


//        MediaItem mediaItem2 = mediaItems.get(0);
//        String qqq = mediaItem2.getItemname();
//        String www = mediaItem2.getItemUri();
//        Toast.makeText(context,qqq+"",Toast.LENGTH_LONG).show();
//        Toast.makeText(context,www+"",Toast.LENGTH_LONG).show();


//            list.setAdapter(mediaclassadapt2);
//            findtextView.setVisibility(View.GONE);
//            findprogressBar.setVisibility(View.GONE);
    }

//    private ArrayList<MediaItem> parsejson(String result) {

//        try {
//            JSONObject jsonObject = new JSONObject(result);
//            JSONArray jsonArray = jsonObject.optJSONArray("data");
////            int a = jsonArray.length();
//////            String b = a+"";
//////            Toast.makeText(context,b+"",Toast.LENGTH_LONG).show();
//            if(jsonArray!=null&&jsonArray.length()>0){
//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
//                        MediaItem mediaItem = new MediaItem();
//                        String musicname = jsonObject1.optString("song_name");
//                        mediaItem.setItemname(musicname);
//                        String musicurl = jsonObject1.optString("play_url");
//                        mediaItem.setItemUri(musicurl);
//                        mediaItems.add(mediaItem);
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mediaItems;
//    }
    class Mediaclassadapt1 extends BaseAdapter {

        @Override
        public int getCount() {
            return mediaItems.size();
        }

        @Override
        public Object getItem(int position) {

            return mediaItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewhold viewhold;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_activity_netmedia, null);
                viewhold = new Viewhold();
                viewhold.item_text = convertView.findViewById(R.id.netitem_text);
                convertView.setTag(viewhold);
            } else {
                viewhold = (Viewhold) convertView.getTag();
            }
            MediaItem mediaItem2 = mediaItems.get(position);
            viewhold.item_text.setText(mediaItem2.getItemname());
            return convertView;
        }

        public class Viewhold {
            TextView item_text;
        }
    }

}
