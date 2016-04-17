package com.bikeworld.bikeworld.CodigoNuevo.TablasComunidad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bikeworld.bikeworld.CodigoNuevo.AdaptadoresNuevos.AdaptadorUsuarios;
import com.bikeworld.bikeworld.CodigoNuevo.NuevoMenuComunidad;
import com.bikeworld.bikeworld.CodigoNuevo.ObjetosNuevos.NuevoUsuarioPerfil;
import com.bikeworld.bikeworld.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablaCom1 extends NuevoMenuComunidad {
    String emailT11;
    String nombreT11;

    public TablaCom1(String emailT11, String nombreT11) {
        this.emailT11 = emailT11;
        this.nombreT11 = nombreT11;
    }

    public TablaCom1(String nombreUsuario, String emailUsuario, String emailT11, String nombreT11) {
        super(nombreUsuario, emailUsuario);
        this.emailT11 = emailT11;
        this.nombreT11 = nombreT11;
    }

    public TablaCom1() {
        // Required empty public constructor
    }

    public String getEmailT11() {
        return emailT11;
    }

    public void setEmailT11(String emailT11) {
        this.emailT11 = emailT11;
    }

    public String getNombreT11() {
        return nombreT11;
    }

    public void setNombreT11(String nombreT11) {
        this.nombreT11 = nombreT11;
    }
    public static String rutaGeneral = "https://dazzling-inferno-4414.firebaseio.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tabla_com1, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        final ArrayList<NuevoUsuarioPerfil> listaUsuarios= new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.idListaUsuarios);
        final AdaptadorUsuarios adaptadorUsr = new AdaptadorUsuarios(getActivity(), listaUsuarios);
        lv.setAdapter(adaptadorUsr);

        extraerUsuarios(pathGeneral, adaptadorUsr, listaUsuarios);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void extraerUsuarios(Firebase pathGeneral, final AdaptadorUsuarios adaptadorUsr, final ArrayList<NuevoUsuarioPerfil> listaUsuarios) {
        pathGeneral.child("usuarios").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adaptadorUsr.notifyDataSetChanged();
                NuevoUsuarioPerfil usr = dataSnapshot.getValue(NuevoUsuarioPerfil.class);
                if(!usr.getEmail().equalsIgnoreCase(f11.getEmailT11())){
                    listaUsuarios.add(usr);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adaptadorUsr.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adaptadorUsr.notifyDataSetChanged();

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
