package com.bikeworld.bikeworld.CodigoNuevo.TablasUsuario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bikeworld.bikeworld.CodigoNuevo.NuevoMenuMiPerfil;
import com.bikeworld.bikeworld.CodigoNuevo.ObjetosNuevos.NuevoVideo;
import com.bikeworld.bikeworld.R;
import com.firebase.client.Firebase;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTabla2 extends NuevoMenuMiPerfil{
    String emailT2;
    String nombreT2;

    public FragmentTabla2() {
        super();
        // Required empty public constructor
    }

    public FragmentTabla2(String nombreUsuario, String emailUsuario, String emailT2, String nombreT2) {
        super(nombreUsuario, emailUsuario);
        this.emailT2 = emailT2;
        this.nombreT2 = nombreT2;
    }

    public FragmentTabla2(String emailT2, String nombreT2) {
        this.emailT2 = emailT2;
        this.nombreT2 = nombreT2;
    }

    public String getEmailT2() {
        return emailT2;
    }

    public void setEmailT2(String emailT2) {
        this.emailT2 = emailT2;
    }

    public String getNombreT2() {
        return nombreT2;
    }

    public void setNombreT2(String nombreT2) {
        this.nombreT2 = nombreT2;
    }

    private EditText titulo;
    private EditText descripcion;
    private EditText URL;
    private Button cargar;

    public static String rutaGeneral = "https://dazzling-inferno-4414.firebaseio.com/";

    NuevoVideo nuevoVideo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_fragment_tabla2, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        titulo = (EditText) rootView.findViewById(R.id.idTituloNuevoVideo);
        descripcion = (EditText) rootView.findViewById(R.id.idDescripcionNuevoVideo);
        URL = (EditText) rootView.findViewById(R.id.idURLNuevoVideo);
        cargar = (Button) rootView.findViewById(R.id.idBotonCargarNuevoVideo);



        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hace el click");
                String strTitulo = titulo.getText().toString();
                String strDescripcion = descripcion.getText().toString();
                String strUrl = URL.getText().toString();
                Date date = new Date();
                nuevoVideo = new NuevoVideo(f2.getEmailT2(),strTitulo,strDescripcion,date,strUrl);
                pathGeneral.child("videos").child("video_"+date).setValue(nuevoVideo);
                titulo.setText("");
                descripcion.setText("");
                URL.setText("");
            }
        });






        // Inflate the layout for this fragment
        return rootView;
    }

}
