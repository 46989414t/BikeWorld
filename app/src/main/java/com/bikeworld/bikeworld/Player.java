package com.bikeworld.bikeworld;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class Player extends YouTubeBaseActivity implements YouTubeThumbnailView.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player_view);
        youTubePlayerView.initialize("AIzaSyCTgsEtqcWmE8NTsS0gKT_ZAmOqsq9L1Tk", (YouTubePlayer.OnInitializedListener) this);
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
        //Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();
    }
}
