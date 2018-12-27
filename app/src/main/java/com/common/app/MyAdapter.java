package com.common.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {
Spinner spin1;
ArrayList<String> myArray;
Context ctx;
    public MyAdapter(Context context, int textViewResourceId,
                     ArrayList<String> objects,Spinner spin) {
        super(context, textViewResourceId, objects);
        spin1 = spin;
        myArray = objects;
        ctx = context;
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup prnt){
        View spinnerItem = LayoutInflater.from(ctx).inflate(android.R.layout.simple_spinner_item, null);

        TextView mytext= (TextView)spinnerItem.findViewById(android.R.id.text1);
        mytext.setText(myArray.get(position));

        //int selected = Spinner.
        int selected = spin1.getSelectedItemPosition();
        if(position == selected){
            spinnerItem.setBackgroundColor(Color.parseColor("#044A6C"));
        }
        return spinnerItem;

    }

}
