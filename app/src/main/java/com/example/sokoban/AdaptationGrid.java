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
    Integer screenHeight;
    public AdaptationGrid(Context context, Case[] elements, Integer screenWidth, Integer screenHeigth) {
        this.context = context;
        this.elements = elements;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeigth;
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
        convertView = inflater.inflate(R.layout.grid_layout, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        /*icon.getLayoutParams().height = 100;
        icon.getLayoutParams().width = 50;
        icon.setAdjustViewBounds(true);
        icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        icon.setPadding(0,0,0,0);
        icon.requestLayout();*/
        if (elements[position].getType() == CaseType.MUR){
            icon.setImageResource(R.drawable.mur);
        }
        if (elements[position].getType() == CaseType.DESTINATION){
            icon.setImageResource(R.drawable.vert);
        }
        if (elements[position].getType() == CaseType.VIDE){
            icon.setImageResource(R.drawable.vide);
        }
        if (elements[position].getType() == CaseType.BOITE){
            icon.setImageResource(R.drawable.marron);
        }
        if (elements[position].getType() == CaseType.JOUEUR){
            icon.setImageResource(R.drawable.bleu);
        }
        return convertView;
    }
}
