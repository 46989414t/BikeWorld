package com.bikeworld.bikeworld.CodigoNuevo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bikeworld.bikeworld.R;
import com.bikeworld.bikeworld.CodigoNuevo.TablasUsuario.FragmentTabla1;
import com.bikeworld.bikeworld.CodigoNuevo.TablasUsuario.FragmentTabla2;
import com.bikeworld.bikeworld.CodigoNuevo.TablasUsuario.Tablas;

/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoMenuMiPerfil extends Fragment {

    //NuevoUsuarioPerfil usuarioPerfil;

    String nombreUsuario;
    String emailUsuario;

    private TextView nombreMostrar;
    private TextView emailMostrar;

    private FragmentTabHost mTabHost;

    public NuevoMenuMiPerfil(String nombreUsuario, String emailUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
    }

    public NuevoMenuMiPerfil() {

    }

    public static FragmentTabla1 f1;
    public static FragmentTabla2 f2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nuevo_menu_mi_perfil, container, false);

        nombreMostrar = (TextView) rootView.findViewById(R.id.idNombrePerfil);
        emailMostrar = (TextView) rootView.findViewById(R.id.idEmailUsuario);

        System.out.println("DATOS ANTERIORES: " + nombreUsuario + " " + emailUsuario);

        nombreMostrar.setText(nombreUsuario);
        emailMostrar.setText(emailUsuario);

        f1 = new FragmentTabla1();
        f1.setEmailT1(emailUsuario);
        f1.setNombreT1(nombreUsuario);

        f2 = new FragmentTabla2();
        f2.setEmailT2(emailUsuario);
        f2.setNombreT2(nombreUsuario);


        Tablas fragmenttab = new Tablas();
        Bundle parametro = new Bundle();
        parametro.putString("key", emailUsuario);
        /*getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragmenttab).commit();*/
        final FragmentTransaction ft = getFragmentManager()
                .beginTransaction();
        ft.replace(R.id.item_detail_container, fragmenttab, "tag");
        ft.addToBackStack("tag");
        ft.commit();

        return rootView;
    }


}
