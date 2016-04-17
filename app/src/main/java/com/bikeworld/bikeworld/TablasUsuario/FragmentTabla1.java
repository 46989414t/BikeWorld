package com.bikeworld.bikeworld.TablasUsuario;


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
import com.bikeworld.bikeworld.NuevoMenuMiPerfil;
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
public class FragmentTabla1 extends NuevoMenuMiPerfil {
    String emailT1;
    String nombreT1;

    public FragmentTabla1() {
        super();
        // Required empty public constructor
    }

    public FragmentTabla1(String nombreUsuario, String emailUsuario, String emailT1, String nombreT1) {
        super(nombreUsuario, emailUsuario);
        this.emailT1 = emailT1;
        this.nombreT1 = nombreT1;
    }

    public FragmentTabla1(String emailT1, String nombreT1) {
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

    public static String rutaGeneral = "https://dazzling-inferno-4414.firebaseio.com/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_fragment_tabla1, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        System.out.println("TABLA 1: " + f1.getEmailT1());

        //insertarVideos(pathGeneral);

        //final ArrayList<UserProfile> listContact = GetlistContact(pathGeneral, userProf);
        final ArrayList<NuevoVideo> listaVideos= new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.listView2);
        final AdaptadorVideos adaptador = new AdaptadorVideos(getActivity(), listaVideos);
        lv.setAdapter(adaptador);

        extraerVideos(pathGeneral, f1, adaptador, listaVideos);

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
                View promptView = layoutInflater.inflate(R.layout.pop_up_comentarios_videos, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);


                final ArrayList<ComentariosVideos> listMensajes= new ArrayList<>();
                ListView list = (ListView) promptView.findViewById(R.id.idListaComentariosRes);
                final AdaptadorComentarios adaptador2 = new AdaptadorComentarios(getActivity(), listMensajes);
                list.setAdapter(adaptador2);

                cargarLista(pathGeneral, videoElegido, adaptador2, listMensajes);

                //final TextView usuarioRespuesta = (TextView) promptView.findViewById(R.id.idUsuarioRespuesta);
                //usuarioRespuesta.setText(menRemitente);

                final EditText editText = (EditText) promptView.findViewById(R.id.idEnviarComentario);
                final ImageButton botonEnviar = (ImageButton) promptView.findViewById(R.id.idBotonEnviar);
                botonEnviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ComentariosVideos nuevoComent = new ComentariosVideos();
                        Date date = new Date();
                        String mensaje = editText.getText().toString();

                        nuevoComent.setComentario(f1.getNombreT1() + ": " + mensaje);

                        //videoElegido.setComentarios(nombreT1+": "+mensaje);
                        pathGeneral.child("comentarios").child("video_"+videoElegido.getFecha()).child(f1.getEmailT1().replace(".","%")+date.toString()).setValue(nuevoComent.getComentario());
                        editText.setText("");
                    }
                });
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setNeutralButton("Editar", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                abrirSegundoPopUp(videoElegido, pathGeneral);
                            }
                        })
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

        return rootView;
    }

    private void abrirSegundoPopUp(final NuevoVideo videoElegido, final Firebase pathGeneral) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        final View pop2 = layoutInflater.inflate(R.layout.pop_up_editar_video, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(pop2);

        final EditText titulo = (EditText) pop2.findViewById(R.id.idTituloEditar);
        final EditText descripcion = (EditText) pop2.findViewById(R.id.idDescripcionEditar);
        final EditText url = (EditText) pop2.findViewById(R.id.idUrlEditar);

        titulo.setText(videoElegido.getTitulo());
        descripcion.setText(videoElegido.getDescripcion());
        url.setText(videoElegido.getUrl());



        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String strTituloEdit = titulo.getText().toString();
                        String strDescripcionEdit = descripcion.getText().toString();
                        String strUrlEdit = url.getText().toString();
                        videoElegido.setTitulo(strTituloEdit);
                        videoElegido.setDescripcion(strDescripcionEdit);
                        videoElegido.setUrl(strUrlEdit);
                        pathGeneral.child("videos").child("video_"+videoElegido.getFecha()).child("titulo").setValue(strTituloEdit);
                        pathGeneral.child("videos").child("video_"+videoElegido.getFecha()).child("descripcion").setValue(strDescripcionEdit);
                        pathGeneral.child("videos").child("video_"+videoElegido.getFecha()).child("url").setValue(strUrlEdit);
                        System.out.println("DATOS DEL VIDEO: " + videoElegido.getTitulo() + videoElegido.getDescripcion());

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

    private void cargarLista(Firebase pathGeneral, NuevoVideo videoElegido, final AdaptadorComentarios adaptador2, final ArrayList<ComentariosVideos> listMensajes) {

        pathGeneral.child("comentarios").child("video_"+videoElegido.getFecha()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adaptador2.notifyDataSetChanged();
                ComentariosVideos video = dataSnapshot.getValue(ComentariosVideos.class);
                listMensajes.add(video);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adaptador2.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adaptador2.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    private void extraerVideos(Firebase pathGeneral, final FragmentTabla1 f1, final AdaptadorVideos adaptador, final ArrayList<NuevoVideo> listaVideos) {
        pathGeneral.child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adaptador.notifyDataSetChanged();
                NuevoVideo video = dataSnapshot.getValue(NuevoVideo.class);
                if(video.getEmaiUser().equalsIgnoreCase(f1.getEmailT1())){
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

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void insertarVideos(Firebase pathGeneral) {
        Date date = new Date();
        NuevoVideo v1 = new NuevoVideo("enric@admin.com", "tituilo2", "descrip2", date,"https://www.youtube.com/watch?v=R4aMHPPKYRE");
        pathGeneral.child("videos").child("video_"+date).setValue(v1);

    }


}
