package com.bikeworld.bikeworld.AdaptadoresNuevos;

/**
 * Created by enric on 15/4/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bikeworld.bikeworld.ObjetosNuevos.NuevoVideo;
import com.bikeworld.bikeworld.R;

import java.util.ArrayList;


/**
 * Created by enric on 27/3/16.
 */
public class AdaptadorVideos extends BaseAdapter {
    private static ArrayList<NuevoVideo> listContact;

    private LayoutInflater mInflater;

    public AdaptadorVideos(Context photosFragment, ArrayList<NuevoVideo> results){
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
            convertView = mInflater.inflate(R.layout.item_video, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.idNuevoNombreVideo);
            holder.fecha = (TextView) convertView.findViewById(R.id.idNuevaFechaVideo);
            holder.descripcion = (TextView) convertView.findViewById(R.id.idNuevaDescripcionVideo);
            //holder.txtDescripcion = (TextView) convertView.findViewById(R.id.idDescripcionEtapa);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nombre.setText(listContact.get(position).getTitulo());
        holder.fecha.setText(listContact.get(position).getFecha().toString());
        holder.descripcion.setText(listContact.get(position).getDescripcion());
        //holder.txtDescripcion.setText(listContact.get(position).getDescripcion());
        //holder.txtphone.setText(listContact.get(position).GetPhone());

        return convertView;
    }

    static class ViewHolder{
        TextView nombre;
        TextView fecha;
        TextView descripcion;
        //TextView txtDescripcion;
    }
}