package com.bikeworld.bikeworld;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTabla1 extends Fragment {


    public FragmentTabla1() {
        // Required empty public constructor
    }

    public static String rutaGeneral = "https://dazzling-inferno-4414.firebaseio.com/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nuevo_menu_mi_perfil, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        //extraerVideos(pathGeneral, )

        return rootView;
    }


}
