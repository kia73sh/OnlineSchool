package com.sourcey.materiallogindemo.Views.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Views.Authentication.LoginActivity;
import com.sourcey.materiallogindemo.Views.SimpleActivity;


public class HomeActivity extends SimpleActivity {
    private ImageView Acc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Acc = (ImageView) findViewById(R.id.Acc);
        Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




        
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
