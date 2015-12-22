package com.bikeworld.bikeworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyVideos extends UserProfile {
    //https://www.youtube.com/watch?v=yT7XYeGemUw
    //http://androideity.com/2012/05/13/reproducir-videos-de-youtube-desde-tu-app-android/
    //http://www.webempresa.com/blog/item/1873-como-obtener-la-api-key-de-youtube-sin-aburrirse-en-el-proceso.html

    //comprobar proyecto carlos: https://github.com/lawer/RottenTomatoesClient/blob/f8e78c5c9828cd661ad64f203fb5f964e750fac0/app/src/main/java/poblenou/rottentomatoesclient/MoviesAdapter.java

    //apiYoutube: AIzaSyCTgsEtqcWmE8NTsS0gKT_ZAmOqsq9L1Tk

    public ArrayList<VideoObject> datos;

    public VideosAdapterList adapter;

    private ListView lista;
    public EditText url;
    public String strUrl;
    Button viewVideoButton;

    public EditText titulo;
    public EditText descripcion;
    public String strTitulo;
    public String strDescripcion;

    public String apiKeyYoutubue = "AIzaSyCTgsEtqcWmE8NTsS0gKT_ZAmOqsq9L1Tk";
    public String videoId = "n62Zpz0qdSI";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AlertDialog.Builder dialogoEliminar = new AlertDialog.Builder(this);
        dialogoEliminar.setTitle("Eliminar");
        dialogoEliminar.setMessage("¿Desea eliminar el elemento seleccionado?");
        /*dialogoEliminar.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminar();
            }
        });*/

        System.out.println("Llega a myVideos de: " + user.getUserName());

        datos = new ArrayList<VideoObject>();


        final String logUser = user.getUserName().toString();
        String userBBDD = "userLog_"+logUser;

        //para hacer los gets

        final Firebase referencia = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/videos/");

        lista = (ListView) findViewById(R.id.listaVideos);
        //final VideosAdapterList adapter = new VideosAdapterList(this, datos);
        adapter = new VideosAdapterList(this, datos);

        lista.setAdapter(adapter);

        /*referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VideoObject video = postSnapshot.getValue(VideoObject.class);
                    if (video.getUserName().equals(logUser)) {
                        datos.add(video);
                    }
                    //datos.add(video);
                    System.out.println("Los videos guardados" + video);
                    System.out.println("Los videos a mostrar: " + datos);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/


        referencia.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
                System.out.println("ha actualizado los datos del adapter");
                //for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VideoObject video = dataSnapshot.getValue(VideoObject.class);
                    if (video.getUserName().equals(logUser)) {
                        datos.add(video);
                   // }
                    //datos.add(video);
                    System.out.println("Los videos guardados" + video);
                    System.out.println("Los videos a mostrar: " + datos);
                        /*adapter.notifyDataSetChanged();
                        System.out.println("ha actualizado los datos del adapter");*/
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
                System.out.println("ha actualizado los datos del adapter");
                /*for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VideoObject video = postSnapshot.getValue(VideoObject.class);
                    if (video.getUserName().equals(logUser)) {
                        datos.add(video);
                    }
                    //datos.add(video);
                    System.out.println("Los videos guardados" + video);
                    System.out.println("Los videos a mostrar: " + datos);
                }
                //adapter.notifyDataSetChanged();
                //System.out.println("ha actualizado los datos del adapter");
                /*VideoObject video = dataSnapshot.getValue(VideoObject.class);
                if (video.getUserName().equals(logUser)) {
                    datos.add(video);

                }
                //datos.add(video);
                System.out.println("Los videos guardados" + video);
                System.out.println("Los videos a mostrar: " + datos);*/

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

       referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VideoObject video = postSnapshot.getValue(VideoObject.class);
                    if (video.getUserName().equals(logUser)) {
                        datos.add(video);
                    }
                    //datos.add(video);
                    System.out.println("Los videos guardados" + video);
                    System.out.println("Los videos a mostrar: " + datos);
                }*/
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        /*listView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // Tu código para recargar los datos

                // ...

                // Asegurate de llamar a listView.onRefreshComplete()

                listView.onRefreshComplete();
            }
        });*/



        //lista.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

        //});
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final VideoObject elegido = (VideoObject) parent.getItemAtPosition(position);
                System.out.println("Llega al click largo con Objeto seleccionado: " + elegido);
                dialogoEliminar.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //elimina el objeto
                        datos.remove(elegido);
                        //lo elimina de la base de datos
                        referencia.child("videoObject_" + elegido.getTitle().toString()).removeValue();

                        adapter.notifyDataSetChanged();
                        System.out.println("ha actualizado los datos del adapter");
                    }
                });
                dialogoEliminar.show();

                return true;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VideoObject elegido = (VideoObject) parent.getItemAtPosition(position);
                String urlSelecc = elegido.getUrl();

                //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc));
                //startActivity(i);
                goToVideoView();

            }
        });

    }

    public void onAddVideo(View view) {

        url = (EditText) findViewById(R.id.idURL);
        titulo=(EditText) findViewById(R.id.idTitulo);
        descripcion = (EditText) findViewById(R.id.idDescription);
        viewVideoButton = (Button) findViewById(R.id.button3);
        strUrl = url.getText().toString();
        strTitulo=titulo.getText().toString();
        strDescripcion=descripcion.getText().toString();
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

        String logUser = user.getUserName().toString();
        String userBBDD = "userLog_"+logUser;
        //AÑADIR VIDEO A BBDD********************************************************
        //Firebase firebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/"+userBBDD+"/userMenu/userProfile/myVideos/videoObject_"+strTitulo);
        Firebase profile = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/");
        Firebase videos = profile.child("videos");
        Firebase newVideo = videos.child("videoObject_" + strTitulo);

        //crea el nuevo video
        VideoObject videoObject = new VideoObject(user.getUserName(),user.getPassword(),fechaActual, strUrl,strTitulo,strDescripcion);
        newVideo.setValue(videoObject);

        System.out.println("objeto de video añadido a la BBDD");
/*
        datos.add(videoObject);
        adapter.notifyDataSetChanged();
*/
        System.out.println("El video añadido es: " + videoObject.getTitle());
        System.out.println("imprimir DATOS: " + datos);


    }
    public void goToVideoView(){
        Intent act2 = new Intent(this, VideoViewMostrar.class);
        startActivity(act2);
    }
}
