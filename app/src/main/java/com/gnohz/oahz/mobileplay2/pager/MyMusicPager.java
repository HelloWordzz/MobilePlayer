package com.gnohz.oahz.mobileplay2.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gnohz.oahz.mobileplay2.R;
import com.gnohz.oahz.mobileplay2.base.BasePager;

public class MyMusicPager extends BasePager {
    private TextView textView;
    public MyMusicPager(Context context) {
        super(context);
    }

    @Override
    public View intiview() {
        View view = View.inflate(context, R.layout.activity_mymusic,null);
        textView = view.findViewById(R.id.text);
        return view;
    }

    @Override
    public void intidata() {
        super.intidata();
        textView.setText("测试返回数据3");
    }
}
