package com.bikeworld.bikeworld.ObjetosAntiguos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bikeworld.bikeworld.R;

import java.util.ArrayList;

/**
 * Created by 46989414t on 17/12/15.
 */
public class VideosAdapterList extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<VideoObject> items;

    public VideosAdapterList (Activity activity, ArrayList<VideoObject> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<VideoObject> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.estilo_my_video_list, null);
        }

        VideoObject dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.idTitulo);
        title.setText(dir.getTitle());

        TextView description = (TextView) v.findViewById(R.id.idDescripcion);
        description.setText(dir.getDescription());

        TextView fecha = (TextView) v.findViewById(R.id.idFecha);
        fecha.setText(dir.getDate());

        /*ImageView imagen = (ImageView) v.findViewById(R.id.imageView);
        imagen.setImageDrawable(dir.getImage());*/

        return v;
    }
}
