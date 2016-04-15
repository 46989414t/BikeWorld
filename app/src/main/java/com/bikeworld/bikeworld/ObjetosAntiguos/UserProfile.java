package com.bikeworld.bikeworld.ObjetosAntiguos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.bikeworld.bikeworld.Antiguos.MenuPrincipalTipo2;
import com.bikeworld.bikeworld.Antiguos.MyVideos;
import com.bikeworld.bikeworld.R;

public class UserProfile extends MenuPrincipalTipo2 {
    public ListView listaElementos;
    public ArrayAdapter<String> adaptador;
    public EditText userName;

    //public User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        System.out.println("Llega al PERFIL de: " + user.getUserName());

        //userName = (EditText)findViewById(R.id.idUserName);
        //userName = user.getUserName().toString();



        /*String[] myStringArray={"A", "B", "C", "D", "e", "f", "g", "h",};

        listaElementos = (ListView) findViewById(R.id.listView);
        adaptador =  new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myStringArray);

        listaElementos.setAdapter(adaptador);*/

    }


    public void onGoToMyPhotos(View view) {
    }

    public void onGoToMyVideos(View view) {
        System.out.println("ir a myVideos");
        /*Intent act2 = new Intent(this, VideoViewMostrar.class);
        startActivity(act2);*/

        Intent act2 = new Intent(this, MyVideos.class);
        startActivity(act2);
    }
}
