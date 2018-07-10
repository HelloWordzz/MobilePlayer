package com.gnohz.oahz.mobileplay2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import com.gnohz.oahz.mobileplay2.base.BasePager;
import com.gnohz.oahz.mobileplay2.pager.LocalMusicPager;
import com.gnohz.oahz.mobileplay2.pager.FindMusicPager;
import com.gnohz.oahz.mobileplay2.pager.MyMusicPager;
import com.gnohz.oahz.mobileplay2.pager.MinePager;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private static ArrayList<BasePager> basePagers;
    private static int position;
    private Fragment con;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_activity);
        radioGroup = findViewById(R.id.radio_group);
        basePagers = new ArrayList<>();
        basePagers.add(new LocalMusicPager(this));
        basePagers.add(new FindMusicPager(this));
        basePagers.add(new MyMusicPager(this));
        basePagers.add(new MinePager(this));
        radioGroup.setOnCheckedChangeListener(new MyonCheckedChangeListener());
        radioGroup.check(R.id.radio_button1);




    }

    class MyonCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                default:
                    position = 0;
                    break;
                case R.id.radio_button2:
                    position = 1;
                    break;
                case R.id.radio_button3:
                    position = 2;
                    break;
                case R.id.radio_button4:
                    position = 3;
                    break;

            }
            Fragment  to = new Fragment01();
            setFrameLayout(to);
            setFramedata();
        }


    }
    private void setFrameLayout(Fragment to) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_activity,to).commit();
//            if(!to.isAdded()){
////                ft.hide(con);
//                ft.add(R.id.frame_activity,to).commit();
//            }else{
////                ft.hide(con);
//                    ft.show(to).commit();
//            }
////            con = to;

    }

//    private void setFrameLayout(Fragment01 from,Fragment01  to) {
//        /*
//        1.获取FrameManger
//         */
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction ft = manager.beginTransaction();
//
//        ft.replace(R.id.frame_activity,f1);
//        ft.commit();
//
//    }
    public static BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        basePager.intiview();
        return basePager;
    }
    private void setFramedata(){
        BasePager basePager02 = basePagers.get(position);
        basePager02.intidata();
    }



}
