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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bikeworld.bikeworld.AdaptadoresNuevos.AdaptadorComentarios;
import com.bikeworld.bikeworld.AdaptadoresNuevos.AdaptadorVideos;
import com.bikeworld.bikeworld.NuevoMenuComunidad;
import com.bikeworld.bikeworld.ObjetosNuevos.ComentariosVideos;
import com.bikeworld.bikeworld.ObjetosNuevos.NuevoVideo;
import com.bikeworld.bikeworld.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablaCom2 extends NuevoMenuComunidad {
    String emailT22;
    String nombreT22;

    public TablaCom2(String emailT22, String nombreT22) {
        this.emailT22 = emailT22;
        this.nombreT22 = nombreT22;
    }

    public TablaCom2(String nombreUsuario, String emailUsuario, String emailT22, String nombreT22) {
        super(nombreUsuario, emailUsuario);
        this.emailT22 = emailT22;
        this.nombreT22 = nombreT22;
    }

    public String getEmailT22() {
        return emailT22;
    }

    public void setEmailT22(String emailT22) {
        this.emailT22 = emailT22;
    }

    public String getNombreT22() {
        return nombreT22;
    }

    public void setNombreT22(String nombreT22) {
        this.nombreT22 = nombreT22;
    }

    public TablaCom2() {
        // Required empty public constructor
    }
    public static String rutaGeneral = "https://dazzling-inferno-4414.firebaseio.com/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tabla_com2, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        final ArrayList<NuevoVideo> listaVideos= new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.idListaVideosComunidad);
        final AdaptadorVideos adaptador = new AdaptadorVideos(getActivity(), listaVideos);
        lv.setAdapter(adaptador);

        extraerVideos(pathGeneral, adaptador, listaVideos);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("ha clicado un item de la lista");
                //Objeto elegido
                final NuevoVideo videoSelec = (NuevoVideo) parent.getItemAtPosition(position);

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View promptView = layoutInflater.inflate(R.layout.pop_up_comentarios_videos, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);


                final ArrayList<ComentariosVideos> listMensajes= new ArrayList<>();
                ListView list = (ListView) promptView.findViewById(R.id.idListaComentariosRes);
                final AdaptadorComentarios adaptador22 = new AdaptadorComentarios(getActivity(), listMensajes);
                list.setAdapter(adaptador22);

                cargarListaComents(pathGeneral, videoSelec, adaptador22, listMensajes);

                final EditText editText = (EditText) promptView.findViewById(R.id.idEnviarComentario);
                final ImageButton botonEnviar = (ImageButton) promptView.findViewById(R.id.idBotonEnviar);
                botonEnviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ComentariosVideos nuevoComent = new ComentariosVideos();
                        Date date = new Date();
                        String mensaje = editText.getText().toString();

                        nuevoComent.setComentario(f22.getNombreT22() + ": " + mensaje);

                        pathGeneral.child("comentarios").child("video_"+videoSelec.getFecha()).child(f22.getEmailT22().replace(".","%")+date.toString()).setValue(nuevoComent.getComentario());
                        editText.setText("");
                    }
                });
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Ver video", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String urlSelecc = videoSelec.getUrl();
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
                if(!video.getEmaiUser().equalsIgnoreCase(f22.getEmailT22())){
                    listaVideos.add(video);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adaptador.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                adaptador.notifyDataSetChanged();

            }
        });
    }
    private void cargarListaComents(Firebase pathGeneral, NuevoVideo videoSelec, final AdaptadorComentarios adaptador22, final ArrayList<ComentariosVideos> listMensajes) {

        pathGeneral.child("comentarios").child("video_" + videoSelec.getFecha()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adaptador22.notifyDataSetChanged();
                ComentariosVideos video = dataSnapshot.getValue(ComentariosVideos.class);
                listMensajes.add(video);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adaptador22.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adaptador22.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adaptador22.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                adaptador22.notifyDataSetChanged();

            }
        });
    }


}
