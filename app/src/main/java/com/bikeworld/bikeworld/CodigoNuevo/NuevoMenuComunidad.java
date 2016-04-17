package com.bikeworld.bikeworld.CodigoNuevo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bikeworld.bikeworld.R;
import com.bikeworld.bikeworld.CodigoNuevo.TablasComunidad.TablaCom1;
import com.bikeworld.bikeworld.CodigoNuevo.TablasComunidad.TablaCom2;
import com.bikeworld.bikeworld.CodigoNuevo.TablasComunidad.TablasComunidad;


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

    public static TablaCom2 f22;
    public static TablaCom1 f11;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nuevo_menu_comunidad, container, false);


        f22 = new TablaCom2();
        f22.setEmailT22(emailUsuario);
        f22.setNombreT22(nombreUsuario);

        f11 = new TablaCom1();
        f11.setEmailT11(emailUsuario);
        f11.setNombreT11(nombreUsuario);

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
