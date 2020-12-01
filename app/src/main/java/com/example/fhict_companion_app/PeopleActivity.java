package com.example.fhict_companion_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PeopleActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {

    public   String people_list[];
    private String onePerson= "";
    Button sch;
    Button btngetpeople;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        lv = findViewById(R.id.listviewrow);
        people_list = new String[107];
        sch = findViewById(R.id.buttonSch);
        btngetpeople = findViewById(R.id.buttonPeopleList);
        btngetpeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmanager = getSupportFragmentManager();
                FragmentTransaction fragtrans = fragmanager.beginTransaction();
                TokenFragment t = new TokenFragment();
                fragtrans.replace(R.id.frameLayout2,t, "token");
                fragtrans.commit();
            }
        });

        sch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PeopleActivity.this, ScheduleActivity.class );
                finish();
                startActivity(in);
            }
        });
    }


    @Override
    public void onFragmentInteraction(String token) {
        new JSONTask().execute(token);
    }

    private class JSONTask extends AsyncTask <String, Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            URL url =null;
            String s=null;
            try {
                url = new URL("https://api.fhict.nl/people");
                HttpURLConnection connection = null;
                connection =(HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept","application/json");
                connection.setRequestProperty("Authorization","Bearer "+strings[0]);
                connection.connect();
                InputStream is = connection.getInputStream();
                Scanner scn =new Scanner(is);
                s =scn.useDelimiter("\\Z").next();


                    // creating json object
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i =0; i <jsonArray.length(); i++){
                        JSONObject jo = (JSONObject) jsonArray.get(i);

                        onePerson =  "GivenName:   " + jo.get("givenName")+ "\n"  +"SurName:      " +jo.get("surName")+ "\n"
                                +"Office:           " +jo.get("office")+ "\n" + "Department:   " +jo .get("department")+"\n"+"Email:     " +jo.get("mail")+"\n"
                                +"Contact:     "  +jo.get("telephoneNumber");

                        people_list[i]=onePerson;
                    }



            }catch (IOException | JSONException e){
                e.printStackTrace();
            }
            return s;
        }

        @Override
        public void onPostExecute(String s) {

            super.onPostExecute(s);
            lv.setAdapter(new PeopleAdapter(getApplicationContext(),people_list));
        }
    }

}

