package com.example.fhict_companion_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PeopleAdapter extends BaseAdapter {

    private String[] data;
    private LayoutInflater inflater;

    public PeopleAdapter(Context mContext, String[] names){
        this.data = names;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = inflater.inflate(R.layout.new_row,viewGroup,false);
        }
        TextView txt = (TextView) view.findViewById(R.id.textViewRow);
        ImageView ppl = (ImageView) view.findViewById(R.id.peopleImage);
        txt.setText(getItem(i));
        ppl.setImageResource(R.drawable.fontys);

        return view;
    }
}
