package com.example.ameaasambientais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AmeacaAdapter extends BaseAdapter {
    LayoutInflater inflater;
    AmeacaSQLiteDatabase db;

    public AmeacaAdapter(Context ctx, AmeacaSQLiteDatabase db){
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount(){
        return db.getAmeacas().size();
    }

    @Override
    public Object getItem(int position){
        return db.getAmeacas().get(position);
    }

    @Override
    public long getItemId(int position){
        return db.getAmeacas().get(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent){
        v = inflater.inflate(R.layout.ameaca_list_item, null);
        TextView txtAmeacaName = v.findViewById(R.id.txtAmeacaName);
        txtAmeacaName.setText(db.getAmeacas().get(position).getDescricao());
        return v;
    }
}
