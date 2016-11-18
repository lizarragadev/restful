package com.cognos.listapersonalizada;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ANDROID on 7/11/2016.
 */

public class ItemAdapter extends BaseAdapter {
    protected ArrayList<Item> items;
    protected Activity activity;

    public ItemAdapter(ArrayList<Item> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item, null);
        }

        Item item = items.get(i);

        TextView tvTitulo = (TextView)v.findViewById(R.id.tituloItem);
        tvTitulo.setText(item.getTitulo());

        TextView tvSubTitulo = (TextView)v.findViewById(R.id.subTituloItem);
        tvSubTitulo.setText(item.getSubTitulo());

        ImageView ivImagen = (ImageView)v.findViewById(R.id.imagenItem);
        ivImagen.setImageDrawable(item.getImagen());

        return v;
    }
}
