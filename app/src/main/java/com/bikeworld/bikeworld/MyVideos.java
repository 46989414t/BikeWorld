package com.bikeworld.bikeworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MyVideos extends AppCompatActivity {

    public EditText url;
    public String strUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        url = (EditText) findViewById(R.id.idURL);
        strUrl = url.getText().toString();

    }

    public void onReproducir(View view) {
    }
}
