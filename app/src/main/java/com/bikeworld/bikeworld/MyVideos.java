package com.bikeworld.bikeworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyVideos extends AppCompatActivity {
    //https://www.youtube.com/watch?v=yT7XYeGemUw
    //http://androideity.com/2012/05/13/reproducir-videos-de-youtube-desde-tu-app-android/

    public EditText url;
    public String strUrl;
    Button viewVideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        url = (EditText) findViewById(R.id.idURL);
        viewVideoButton = (Button) findViewById(R.id.button3);

        viewVideoButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                strUrl = url.getText().toString();
                //Intent lVideoIntent = new Intent(null, Uri.parse("http://www.youtube.com/watch?v=_JkkkdH6zTQ"), MyVideos.this, OpenYouTubePlayerActivity.class);
                //startActivity(lVideoIntent);

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yT7XYeGemUw"));
                startActivity(i);
            }
        });

        //strUrl = url.getText().toString();

    }

    /*public void onReproducir(View view) {



        Intent lVideoIntent = new Intent(null, Uri.parse("https://www.youtube.com/watch?v=yT7XYeGemUw"), MyVideos.this, OpenYouTubePlayerActivity.class);
        startActivity(lVideoIntent);
    }*/
}
