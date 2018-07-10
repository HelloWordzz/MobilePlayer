package com.gnohz.oahz.mobileplay2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnohz.oahz.mobileplay2.base.BasePager;


public class Fragment01 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        BasePager basePager = MainActivity.getBasePager();
        if(basePager!=null){
            return basePager.rootview;
        }
        return null;
    }





}
