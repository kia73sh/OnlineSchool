package com.sourcey.materiallogindemo.Views.Pages;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sourcey.materiallogindemo.Http.Clients.HomeClient;
import com.sourcey.materiallogindemo.Http.Clients.OnResponseListener;
import com.sourcey.materiallogindemo.Models.Restaurant;
import com.sourcey.materiallogindemo.Views.Authentication.LoginActivity;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Views.Components;
import com.sourcey.materiallogindemo.Views.SimpleActivity;

import java.util.ArrayList;


public class HomeActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);

        HomeClient.getHomeBanners(new OnResponseListener() {
            @Override
            public void onSuccess(Object[] objects) {
                Restaurant restaurant = (Restaurant) objects[0];
                Log.d("name", restaurant.getName());
            }

            @Override
            public void onFailed(int responseCode) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
