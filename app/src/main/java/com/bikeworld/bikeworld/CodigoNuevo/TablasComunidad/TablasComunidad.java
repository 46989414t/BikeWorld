package com.bikeworld.bikeworld.CodigoNuevo.TablasComunidad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bikeworld.bikeworld.R;


/**
 * Created by enric on 15/4/16.
 */
public class TablasComunidad extends Fragment {
    private FragmentTabHost mTabHost;

    //Mandatory Constructor
    public TablasComunidad() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tablas, container, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Usuarios"),
                TablaCom1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Videos"),
                TablaCom2.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("Noticias"),
                TablaCom3.class, null);


        return rootView;
    }
}