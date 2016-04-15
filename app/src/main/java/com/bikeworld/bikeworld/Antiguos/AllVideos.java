package com.bikeworld.bikeworld.Antiguos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bikeworld.bikeworld.ObjetosAntiguos.VideoObject;
import com.bikeworld.bikeworld.ObjetosAntiguos.VideosAdapterList;
import com.bikeworld.bikeworld.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class AllVideos extends MenuPrincipalTipo2 {

    public ArrayList<VideoObject> datosAll;

    public VideosAdapterList adapterAll;

    public String titulosVideos;

    private ListView listaAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        System.out.println("llega a mostrar todos los videos");

        datosAll = new ArrayList<>();
        final Firebase todosVideos = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/videos");

        listaAll = (ListView) findViewById(R.id.listAllVideos);
        adapterAll = new VideosAdapterList(this, datosAll);

        listaAll.setAdapter(adapterAll);

        todosVideos.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapterAll.notifyDataSetChanged();
                System.out.println("ha actualizado los datos del adapter");
                VideoObject video = dataSnapshot.getValue(VideoObject.class);
                System.out.println("Videos para Mostrar: " + video);
                datosAll.add(video);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        listaAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VideoObject elegido = (VideoObject) parent.getItemAtPosition(position);
                String urlSelecc = elegido.getUrl();

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc));
                startActivity(i);
                //goToVideoView();

            }
        });

    }

}
