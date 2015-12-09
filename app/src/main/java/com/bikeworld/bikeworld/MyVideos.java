package com.bikeworld.bikeworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

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



        url = (EditText) findViewById(R.id.idURL);
        titulo=(EditText) findViewById(R.id.idTitulo);
        descripcion = (EditText) findViewById(R.id.idDescription);
        viewVideoButton = (Button) findViewById(R.id.button3);
        strUrl = url.getText().toString();
        strTitulo=titulo.getText().toString();
        strDescripcion=descripcion.getText().toString();

        VideoObject video1=new VideoObject(user.getUserName(),user.getPassword(),"fecha1", "https://www.youtube.com/watch?v=yT7XYeGemUw", "Titulo video 1", "Descripcion video1");
        VideoObject video2=new VideoObject(user.getUserName(),user.getPassword(),"fecha2", "https://vimeo.com/113199166", "Titulo video 2", "Descripcion video1");

        datos = new ArrayList<VideoObject>();
        datos.add(video1);
        datos.add(video2);
        //datos.add(reportajes);


        String logUser = user.getUserName().toString();
        String userBBDD = "userLog_"+logUser;

        //para hacer los gets

            final Firebase referencia = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/"+userBBDD+"/userMenu/userProfile/myVideos/");
            referencia.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    /*System.out.println("DATOS PARA VIDEOS" + dataSnapshot.getValue());
                    String data = dataSnapshot.getValue().toString();
                    System.out.println("VIDEOS: " + data);
                    //extraer titulos
                    String[] arrayTitulos = data.split(",");
                    for (int i=0; i < arrayTitulos.length; i++){
                        System.out.println(arrayTitulos);
                    }*/
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        VideoObject video = postSnapshot.getValue(VideoObject.class);
                        System.out.println(video);
                    }


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });



        lista = (ListView) findViewById(R.id.listaVideos);
        lista.setAdapter(new Lista_adaptador(this, R.layout.estilo_my_video_list, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.idTitulo);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((VideoObject) entrada).getTitle());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.idDescripcion);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((VideoObject) entrada).getDescription());

                    TextView imagen_entrada = (TextView) view.findViewById(R.id.idFecha);
                    if (imagen_entrada != null)
                        imagen_entrada.setText(((VideoObject) entrada).getDate());
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                VideoObject elegido = (VideoObject) pariente.getItemAtPosition(posicion);

                String urlSelecc = elegido.getUrl();

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc));
                startActivity(i);

            }
        });


        /*viewVideoButton.setOnClickListener(new View.OnClickListener(){
            //strUrl = url.

            @Override
            public void onClick(View v) {

                VideoObject video3 = new VideoObject(user.userName,user.getPassword(), "date nueva", "https://www.youtube.com/watch?v=ZnYoKMzeVgo", "Titulo nuevo", "description nueva");

                datos.add(video3);

            }
        });*/

    }

    public void onAddVideo(View view) {
        url = (EditText) findViewById(R.id.idURL);
        titulo=(EditText) findViewById(R.id.idTitulo);
        descripcion = (EditText) findViewById(R.id.idDescription);
        viewVideoButton = (Button) findViewById(R.id.button3);
        strUrl = url.getText().toString();
        strTitulo=titulo.getText().toString();
        strDescripcion=descripcion.getText().toString();

        String logUser = user.getUserName().toString();
        String userBBDD = "userLog_"+logUser;
        //AÑADIR VIDEO A BBDD********************************************************
        //Firebase firebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/"+userBBDD+"/userMenu/userProfile/myVideos/videoObject_"+strTitulo);
        Firebase profile = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/"+userBBDD+"/userMenu/userProfile/");
        Firebase videos = profile.child("myVideos");
        Firebase newVideo = videos.child("videoObject_" + strTitulo);

        //crea el nuevo video
        VideoObject videoObject = new VideoObject(user.getUserName(),user.getPassword(),"falta extraer date", strUrl,strTitulo,strDescripcion);
        newVideo.setValue(videoObject);

//        firebase.child("date").setValue(videoObject.getDate());
//        firebase.child("description").setValue(videoObject.getDescription());
//        firebase.child("title").setValue(videoObject.getTitle());
//        firebase.child("url").setValue(videoObject.getUrl());

        System.out.println("objeto de video añadido a la BBDD");
        datos.add(videoObject);
        System.out.println("El video añadido es: "+videoObject.getTitle());

    }
}
