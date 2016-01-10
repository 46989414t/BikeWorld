package com.bikeworld.bikeworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MenuPrincipalTipo2 extends PantallaLogin{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_tipo2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //User a = new User();
        System.out.println("LLega el menu principal de usuario: " + user.getUserName());

    }

    public void onGoToProfile(View view) {
        Intent act2 = new Intent(this, UserProfile.class);
        startActivity(act2);
    }

    public void onAllVideos(View view) {
        Intent act2 = new Intent(this, AllVideos.class);
        startActivity(act2);
    }
}
