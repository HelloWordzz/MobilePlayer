package com.gnohz.oahz.mobileplay2.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.gnohz.oahz.mobileplay2.HtmlActivity;
import com.gnohz.oahz.mobileplay2.R;
import com.gnohz.oahz.mobileplay2.base.BasePager;

public class MinePager extends BasePager{
    private TextView mineText1;
    private TextView mineText2;
    private TextView mineText3;
    private TextView mineText4;
    private TextView mineText5;
    private TextView mineText6;
   private int i;

    public MinePager(Context context) {
        super(context);
    }

    @Override
    public View intiview() {
        View view = View.inflate(context, R.layout.activity_mine, null);
        mineText1 = view.findViewById(R.id.mine_text1);
        mineText2 = view.findViewById(R.id.mine_text2);
        mineText3 = view.findViewById(R.id.mine_text3);
        mineText4 = view.findViewById(R.id.mine_text4);
        mineText5 = view.findViewById(R.id.mine_text5);
        mineText6 = view.findViewById(R.id.mine_text6);
        return view;
    }

    @Override
    public void intidata() {
        super.intidata();
        mineText1.setOnClickListener(new ButtonListener());
        mineText2.setOnClickListener(new ButtonListener());
        mineText3.setOnClickListener(new ButtonListener());
        mineText4.setOnClickListener(new ButtonListener());
        mineText5.setOnClickListener(new ButtonListener());
        mineText6.setOnClickListener(new ButtonListener());



    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mine_text1:
                    i = 1;
                    break;
                case R.id.mine_text2:
                    i = 2;
                    break;
                case R.id.mine_text3:
                    i = 3;
                    break;
                case R.id.mine_text4:
                    i = 4;
                    break;
                case R.id.mine_text5:
                    i = 5;
                    break;
                case R.id.mine_text6:
                    i = 6;
                    break;

            }
            Intent intent = new Intent(context,HtmlActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("data",i);
            intent.putExtra("bun",bundle);
            context.startActivity(intent);
        }
    }




}



