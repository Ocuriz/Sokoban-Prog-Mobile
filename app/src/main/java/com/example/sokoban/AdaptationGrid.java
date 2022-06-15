package com.example.sokoban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AdaptationGrid extends BaseAdapter {

    Context context;
    Case[] elements;
    LayoutInflater inflater;
    Integer screenWidth;
    public AdaptationGrid(Context context, Case[] elements, Integer screenWidth) {
        this.context = context;
        this.elements = elements;
        this.screenWidth = screenWidth;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return elements.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_main, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.iconTest);
        icon.setAdjustViewBounds(true);
        icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        icon.setPadding(0,0,0,0);
        icon.setLayoutParams(new LinearLayout.LayoutParams(this.screenWidth/9, this.screenWidth/9));
        if (elements[position].getType() == CaseType.MUR){
            icon.setImageResource(R.drawable.mur);
        }
        if (elements[position].getType() == CaseType.DESTINATION){
            icon.setImageResource(R.drawable.croix);
        }
        if (elements[position].getType() == CaseType.VIDE){
            icon.setImageResource(R.drawable.vide);
        }
        if (elements[position].getType() == CaseType.BOITE){
            icon.setImageResource(R.drawable.boite);
        }
        if (elements[position].getType() == CaseType.JOUEUR){
            icon.setImageResource(R.drawable.joueur);
        }
        return convertView;
    }
}
