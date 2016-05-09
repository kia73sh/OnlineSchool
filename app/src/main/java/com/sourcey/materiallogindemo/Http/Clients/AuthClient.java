package com.sourcey.materiallogindemo.Http.Clients;

import com.sourcey.materiallogindemo.Config.ResponseCode;
import com.sourcey.materiallogindemo.Config.Url;
import com.sourcey.materiallogindemo.Http.Http;
import com.sourcey.materiallogindemo.Models.User;
import com.squareup.okhttp.Response;

public class AuthClient {

    public interface OnResponseListener{
        void onSuccess();
        void onFailed(int responseCode);
    }

    public static void authorization(User user, final OnResponseListener onResponseListener){
        Http.postInBackground(Url.AUTHENTICATION_URL, user.converToJson(), new Http.OnResponseAnswer() {
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
            onResponseListener.onSuccess();
        }
        else onResponseListener.onFailed(response.code());
    }

}
