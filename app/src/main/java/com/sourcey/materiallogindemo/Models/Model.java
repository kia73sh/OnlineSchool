package com.sourcey.materiallogindemo.Models;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class Model {

    public static Gson buildGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public static <T> JSONObject createJson(T object) {
        return Json.createJson(buildGson().toJson(object));
    }

    public static class Json {

        public static JSONObject cleanJson() {
            try {
                return new JSONObject("{}");
            } catch (Exception e) {
                return null;
            }
        }

        public static JSONObject createJson(String s) {
            try {
                return new JSONObject(s);
            } catch (Exception e) {
                return cleanJson();
            }
        }

    }

}
