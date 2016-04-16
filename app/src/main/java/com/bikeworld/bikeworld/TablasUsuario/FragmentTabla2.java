package com.bikeworld.bikeworld.TablasUsuario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bikeworld.bikeworld.NuevoMenuMiPerfil;
import com.bikeworld.bikeworld.ObjetosNuevos.NuevoVideo;
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

    public FragmentTabla2(String nombreUsuario, String emailUsuario, String emailT1, String nombreT1) {
        super(nombreUsuario, emailUsuario);
        this.emailT2 = emailT1;
        this.nombreT2 = nombreT1;
    }

    public FragmentTabla2(String emailT1, String nombreT1) {
        this.emailT2 = emailT1;
        this.nombreT2 = nombreT1;
    }

    public String getEmailT1() {
        return emailT2;
    }

    public void setEmailT1(String emailT1) {
        this.emailT2 = emailT1;
    }

    public String getNombreT1() {
        return nombreT2;
    }

    public void setNombreT1(String nombreT1) {
        this.nombreT2 = nombreT1;
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

        nuevoVideo = new NuevoVideo();


       cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nuevoVideo.getTitulo().equals("") || nuevoVideo.getDescripcion().equals("") || nuevoVideo.getUrl().equals("")){
                    Toast toast = Toast.makeText(getActivity(), "Campos vacíos", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Date date =new Date();

                    nuevoVideo.setTitulo(titulo.getText().toString());
                    nuevoVideo.setDescripcion(descripcion.getText().toString());
                    nuevoVideo.setUrl(URL.getText().toString());
                    nuevoVideo.setEmaiUser(f2.getEmailT1());
                    nuevoVideo.setFecha(date);
                    pathGeneral.child("videos").child("video_"+nuevoVideo.getFecha()).setValue(nuevoVideo);
                    titulo.setText("");
                    descripcion.setText("");
                    URL.setText("");
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
