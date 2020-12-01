package com.example.fhict_companion_app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class People extends Fragment implements TokenFragment.OnFragmentInteractionListener {

    private FragmentPeopleListener listener;
    private EditText editxt;
    public interface FragmentPeopleListener {
        public void OnInputSent (String input);
    }


    public People() {
        // Required empty public constructor
    }

    Button btnshow;
    String generatedToken;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_people, container, false);

        editxt = view.findViewById(R.id.editTextTextPersonName);
        btnshow = view.findViewById(R.id.buttonGetPerson);
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmanager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragtrans = fragmanager.beginTransaction();
                final FragmentShowPeople p = new FragmentShowPeople();
                fragtrans.replace(R.id.framePeople,p, "people");
                fragtrans.commit();
                String input = editxt.getText().toString();
                //p.Message(input);
            }

        });

        return view;
    }

    @Override
    public void onFragmentInteraction(String token) {
         generatedToken = token;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(context instanceof FragmentPeopleListener){
//            listener = (FragmentPeopleListener) context;
//        }
//        else{
//            throw new RuntimeException(context.toString() + "must implement the FragmentPeople listener");
//        }
//    }

//    @Override
//    public void onAttachFragment(Fragment childFragment) {
//        super.onAttachFragment(childFragment);
//        listener = (FragmentPeopleListener) childFragment;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }
}