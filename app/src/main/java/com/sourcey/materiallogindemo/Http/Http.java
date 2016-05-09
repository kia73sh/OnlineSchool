package com.sourcey.materiallogindemo.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Http {

    private static final String TAG = "HttpClass";
    private static OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void Http(){
        client = new OkHttpClient();
    }

    public interface OnResponseAnswer{
        void onResponse(Response response);
    }

    private static Response post(String url , JSONObject json) throws Exception {

        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public static void postInBackground
            (final String url , final JSONObject json, final OnResponseAnswer onResponseAnswer){

        class Operation extends AsyncTask<Void, Void, Response> {

            @Override
            protected Response doInBackground(Void... params) {
                try {
                    return post(url, json);
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Response result) {
                super.onPostExecute(result);
                onResponseAnswer.onResponse(result);
            }

        }

        new Operation().execute();
    }

}
