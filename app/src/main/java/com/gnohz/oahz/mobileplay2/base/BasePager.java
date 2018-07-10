package com.gnohz.oahz.mobileplay2.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {
    public final Context context;
    public View rootview;

    public BasePager(Context context){
            this.context = context;
            rootview = intiview();
    }

    public  abstract View intiview();

    public void intidata(){
    }
}
