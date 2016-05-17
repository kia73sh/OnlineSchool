package com.sourcey.materiallogindemo.Http.Clients;

import com.sourcey.materiallogindemo.Config.ResponseCode;
import com.sourcey.materiallogindemo.Config.Paths;
import com.sourcey.materiallogindemo.Http.Http;
import com.sourcey.materiallogindemo.Models.User;
import com.sourcey.materiallogindemo.Views.Components;
import com.squareup.okhttp.Response;

import java.util.ArrayList;

public class AuthClient {

    public static void authorization(User user, final OnResponseListener onResponseListener){
        Http.postInBackground(Paths.AUTHENTICATION_URL, user.convertToJson(), new Http.OnResponseAnswer() {
            @Override
            public void onResponse(Response response) {
                responseMessaging(onResponseListener, response);
            }
        });
    }

    private static void responseMessaging(OnResponseListener onResponseListener, Response response){

        if  (response == null)
            onResponseListener.onFailed(ResponseCode.INTERNET_CONNECTION);
        else if (response.isSuccessful()){
            onResponseListener.onSuccess(Components.createObjectList(new ArrayList<>()));
        }
        else onResponseListener.onFailed(response.code());
    }

}
