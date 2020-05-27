package com.example.vita_1.CustomListMaker;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable drawable;
    private String title;

    public Drawable getDrawable(){
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
