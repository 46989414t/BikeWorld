package com.bikeworld.bikeworld.CodigoNuevo.AdaptadoresNuevos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bikeworld.bikeworld.CodigoNuevo.ObjetosNuevos.NuevoUsuarioPerfil;
import com.bikeworld.bikeworld.R;

import java.util.ArrayList;

/**
 * Created by enric on 17/4/16.
 */
public class AdaptadorUsuarios extends BaseAdapter {
    private static ArrayList<NuevoUsuarioPerfil> listContact;

    private LayoutInflater mInflater;

    public AdaptadorUsuarios(Context photosFragment, ArrayList<NuevoUsuarioPerfil> results){
        listContact = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listContact.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listContact.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_usuario, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.idNombreUsuarioLista);
            holder.email = (TextView) convertView.findViewById(R.id.idEmailUsuarioLista);
            //holder.descripcion = (TextView) convertView.findViewById(R.id.idNuevaDescripcionVideo);
            //holder.txtDescripcion = (TextView) convertView.findViewById(R.id.idDescripcionEtapa);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nombre.setText(listContact.get(position).getNombre());
        holder.email.setText(listContact.get(position).getEmail());
        //holder.descripcion.setText(listContact.get(position).getDescripcion());
        //holder.txtDescripcion.setText(listContact.get(position).getDescripcion());
        //holder.txtphone.setText(listContact.get(position).GetPhone());

        return convertView;
    }

    static class ViewHolder{
        TextView nombre;
        TextView email;
        //TextView descripcion;
        //TextView txtDescripcion;
    }
}