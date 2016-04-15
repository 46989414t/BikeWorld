package com.bikeworld.bikeworld;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nuevo_menu_mi_perfil, container, false);

        nombreMostrar = (TextView) rootView.findViewById(R.id.idNombrePerfil);
        emailMostrar = (TextView) rootView.findViewById(R.id.idEmailUsuario);

        nombreMostrar.setText(nombreUsuario);
        emailMostrar.setText(emailUsuario);


        Tablas fragmenttab = new Tablas();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragmenttab).commit();

        return rootView;
    }


}
