package com.sourcey.materiallogindemo.Views;

import android.graphics.Typeface;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Components {

    public static Typeface getIransansFont(){
        return Typeface.createFromAsset(SimpleActivity.getContext().getAssets(),
                "fonts/iransans/iran_sans.ttf");
    }

    public static Typeface getIransansBoldFont(){
        return Typeface.createFromAsset(SimpleActivity.getContext().getAssets(),
                "fonts/iransans/iran_sans_bold.ttf");
    }

    public static <T> Object[] createObjectList(ArrayList<T> list) {
        Object[] outList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++)
            outList[i] = list.get(i);
        return outList;
    }

    public static JSONObject getJsonByPath(String path) {

        String json;
        try {
            InputStream is = SimpleActivity.getContext().getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            Log.d("json objects", json);
            return new JSONObject(json);

        } catch (IOException ex) {
            Log.d("json objects", "null");
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
