package com.bikeworld.bikeworld.ObjetosAntiguos;


import android.os.Bundle;
import android.widget.Toast;

import com.bikeworld.bikeworld.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoViewMostrar extends UserProfile implements
        YouTubePlayer.OnInitializedListener{


    public static String API_KEY = "AIzaSyCTgsEtqcWmE8NTsS0gKT_ZAmOqsq9L1Tk";
    public static String VIDEO_ID = "MWK_EGgycio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("llega al video Mostrar");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(VIDEO_ID);
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //Toast.makeText(this, "no se puede mostrar", Toast.LENGTH_LONG);
        Toast.makeText(this, getString(R.string.error_field_required), Toast.LENGTH_LONG).show();

    }

    /*@Override
    public void onInitializationFailure(Provider provider,
                                        YouTubeInitializationResult result) {
        Toast.makeText(getApplicationContext(),
                "onInitializationFailure()",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }*/


}