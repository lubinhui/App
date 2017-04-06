package com.biyesheji.android.actionbar;
import android.graphics.Color;

import com.biyesheji.android.actionbar.ActionBar.Action;


public abstract class AbstractAction implements Action {
    private int mDrawable;
    private String text;

    public AbstractAction(String text) {
        this.text = text;
    }

    public AbstractAction(int drawable) {
        mDrawable = drawable;
    }

    @Override
    public int getDrawable() {
        return mDrawable;
    }

    @Override
    public String getText() {return text;}

    public void setText(String text) {
        this.text = text;
    }
    
    public int getTextColor() {
    	return Color.WHITE;
    }
}