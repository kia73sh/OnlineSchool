package com.sourcey.materiallogindemo.UI;

import android.graphics.Typeface;

import com.sourcey.materiallogindemo.SimpleActivity;

public class Components {

    public static Typeface getIransansFont(){
        return Typeface.createFromAsset(SimpleActivity.getContext().getAssets(),
                "fonts/iransans/iran_sans.ttf");
    }

    public static Typeface getIransansBoldFont(){
        return Typeface.createFromAsset(SimpleActivity.getContext().getAssets(),
                "fonts/iransans/iran_sans_bold.ttf");
    }

}
