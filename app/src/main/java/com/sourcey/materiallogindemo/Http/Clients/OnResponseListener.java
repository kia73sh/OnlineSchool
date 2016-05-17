package com.sourcey.materiallogindemo.Http.Clients;

import java.util.Objects;

public interface OnResponseListener {
    void onSuccess(Object[] objects);
    void onFailed(int responseCode);
}
