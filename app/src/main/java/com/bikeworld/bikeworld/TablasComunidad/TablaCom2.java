package com.bikeworld.bikeworld.TablasComunidad;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bikeworld.bikeworld.AdaptadoresNuevos.AdaptadorVideos;
import com.bikeworld.bikeworld.NuevoMenuComunidad;
import com.bikeworld.bikeworld.ObjetosNuevos.NuevoVideo;
import com.bikeworld.bikeworld.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablaCom2 extends NuevoMenuComunidad {
    String emailT1;
    String nombreT1;

    public TablaCom2(String emailT1, String nombreT1) {
        this.emailT1 = emailT1;
        this.nombreT1 = nombreT1;
    }

    public TablaCom2(String nombreUsuario, String emailUsuario, String emailT1, String nombreT1) {
        super(nombreUsuario, emailUsuario);
        this.emailT1 = emailT1;
        this.nombreT1 = nombreT1;
    }

    public String getEmailT1() {
        return emailT1;
    }

    public void setEmailT1(String emailT1) {
        this.emailT1 = emailT1;
    }

    public String getNombreT1() {
        return nombreT1;
    }

    public void setNombreT1(String nombreT1) {
        this.nombreT1 = nombreT1;
    }

    public TablaCom2() {
        // Required empty public constructor
    }
    public static String rutaGeneral = "https://dazzling-inferno-4414.firebaseio.com/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_fragment_tabla1, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        final ArrayList<NuevoVideo> listaVideos= new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.listView2);
        final AdaptadorVideos adaptador = new AdaptadorVideos(getActivity(), listaVideos);
        lv.setAdapter(adaptador);

        extraerVideos(pathGeneral, adaptador, listaVideos);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ha clicado un item de la lista");
                //Objeto elegido
                final NuevoVideo videoElegido = (NuevoVideo) parent.getItemAtPosition(position);
                //final String menRemitente = videoElegido.getTitulo();
                //System.out.println("Remitente a responder: " + menRemitente);
                //--------

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View promptView = layoutInflater.inflate(R.layout.pop_up_video_comentarios, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);
                //final TextView usuarioRespuesta = (TextView) promptView.findViewById(R.id.idUsuarioRespuesta);
                //usuarioRespuesta.setText(menRemitente);
                //final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Ver video", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String urlSelecc = videoElegido.getUrl();
                                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSelecc));
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void extraerVideos(Firebase pathGeneral, final AdaptadorVideos adaptador, final ArrayList<NuevoVideo> listaVideos) {
        pathGeneral.child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adaptador.notifyDataSetChanged();
                NuevoVideo video = dataSnapshot.getValue(NuevoVideo.class);
                if(!video.getEmaiUser().equalsIgnoreCase(f1.getEmailT1())){
                    listaVideos.add(video);
                }
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
    }


}
