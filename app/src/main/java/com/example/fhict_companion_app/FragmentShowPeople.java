package com.example.fhict_companion_app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentShowPeople extends Fragment {


    TextView txt;
    public FragmentShowPeople() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_show_people, container, false);
        txt = view.findViewById(R.id.textViewShowPerson);
        People p = new People();
        return view;
    }



    public void Message(String input) {
        txt.setText(input);
    }
}