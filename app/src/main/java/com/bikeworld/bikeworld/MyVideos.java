package com.bikeworld.bikeworld;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyVideos extends UserProfile {
    //https://www.youtube.com/watch?v=yT7XYeGemUw
    //http://androideity.com/2012/05/13/reproducir-videos-de-youtube-desde-tu-app-android/

    //comprobar proyecto carlos: https://github.com/lawer/RottenTomatoesClient/blob/f8e78c5c9828cd661ad64f203fb5f964e750fac0/app/src/main/java/poblenou/rottentomatoesclient/MoviesAdapter.java

    public ArrayList<VideoObject> datos;

    private ListView lista;
    public EditText url;
    public String strUrl;
    Button viewVideoButton;

    public EditText titulo;
    public EditText descripcion;
    public String strTitulo;
    public String strDescripcion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        System.out.println("Llega a myVideos de: " + user.getUserName());

        datos = new ArrayList<VideoObject>();

        String logUser = user.getUserName().toString();
        String userBBDD = "userLog_"+logUser;

        //para hacer los gets

            final Firebase referencia = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/videos/");
            referencia.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        VideoObject video = postSnapshot.getValue(VideoObject.class);
                        datos.add(video);
                        System.out.println("Los videos guardados" + video);
                        System.out.println("Los videos a mostrar: "+datos);
                    }


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        lista = (ListView) findViewById(R.id.listaVideos);

        VideosAdapterList adapter = new VideosAdapterList(this, datos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                VideoObject elegido = (VideoObject) pariente.getItemAtPosition(posicion);

                String urlSelecc = elegido.getUrl();

                //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc));
                //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc), MyVideos.this, OpenYouTubePlayerActivity.class);
                //startActivity(i);
                Uri uri = Uri.parse(urlSelecc);
                String videoId = uri.getQueryParameter("v").toString();
                /*Intent lVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("ytv://" + id), MyVideos.this, OpenYouTubePlayerActivity.class);
                startActivity(lVideoIntent);*/
               // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc)));
                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(urlSelecc);
                    mp.setScreenOnWhilePlaying(true);
                    //mp.setDisplay(holder);
                    mp.prepare();
                    mp.start();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }



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
        String fechaActual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

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
        datos.add(videoObject);
        System.out.println("El video añadido es: " + videoObject.getTitle());
        System.out.println("imprimir DATOS: "+datos);

    }
}
