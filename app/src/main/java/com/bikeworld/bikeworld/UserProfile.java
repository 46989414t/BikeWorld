package com.bikeworld.bikeworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserProfile extends AppCompatActivity {
    public ListView listaElementos;
    public ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*String[] myStringArray={"A", "B", "C", "D", "e", "f", "g", "h",};

        listaElementos = (ListView) findViewById(R.id.listView);
        adaptador =  new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myStringArray);

        listaElementos.setAdapter(adaptador);*/

    }



}
