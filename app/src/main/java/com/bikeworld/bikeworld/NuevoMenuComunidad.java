package com.bikeworld.bikeworld;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bikeworld.bikeworld.TablasComunidad.TablaCom2;
import com.bikeworld.bikeworld.TablasComunidad.TablasComunidad;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoMenuComunidad extends Fragment {
    String nombreUsuario;
    String emailUsuario;

    public NuevoMenuComunidad() {
        // Required empty public constructor
    }

    public NuevoMenuComunidad(String nombreUsuario, String emailUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
    }

    public static TablaCom2 f1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nuevo_menu_comunidad, container, false);


        f1 = new TablaCom2();
        f1.setEmailT1(emailUsuario);
        f1.setNombreT1(nombreUsuario);

        TablasComunidad fragmenttab = new TablasComunidad();
        Bundle parametro = new Bundle();

        /*getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragmenttab).commit();*/
        final FragmentTransaction ft = getFragmentManager()
                .beginTransaction();
        ft.replace(R.id.item2_detail_container, fragmenttab, "tag");
        ft.addToBackStack("tag");
        ft.commit();
        // Inflate the layout for this fragment
        return rootView;
    }


}
