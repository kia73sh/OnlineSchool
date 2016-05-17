package com.sourcey.materiallogindemo.Http.Clients;


import android.graphics.Path;
import android.util.Log;

import com.sourcey.materiallogindemo.Config.JsonKeys;
import com.sourcey.materiallogindemo.Config.Paths;
import com.sourcey.materiallogindemo.Config.ResponseCode;
import com.sourcey.materiallogindemo.Models.Restaurant;
import com.sourcey.materiallogindemo.Views.Components;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeClient {

    private static void getFromJson(String jsonKey, OnResponseListener onResponseListener){

        JSONObject jsonObject = Components.getJsonByPath(Paths.HOME_PATH_JSON);

        try {
            ArrayList<Restaurant> banners = new ArrayList<>();
            assert jsonObject != null;
            JSONArray jsonArray = jsonObject.getJSONArray(jsonKey);
            for (int i = 0 ; i < jsonArray.length() ; i++){
                banners.add(Restaurant.createFromJson(jsonArray.getJSONObject(i)));
            }
            onResponseListener.onSuccess(Components.createObjectList(banners));
        } catch (JSONException e) {
            onResponseListener.onFailed(ResponseCode.UNKNOWN_ERROR);
            e.printStackTrace();
        }
    }

    public static void getHomeBanners(OnResponseListener onResponseListener){

        getFromJson(JsonKeys.HOME_BANNER, onResponseListener);

    }

    public static void getHomeItems( OnResponseListener onResponseListener){

        getFromJson(JsonKeys.HOME_ITEMS, onResponseListener);

    }
}
