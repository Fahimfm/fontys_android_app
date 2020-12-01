package com.example.fhict_companion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ScheduleActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Intent intent;
    public  String schedule_list[];
    private String schedule= "";
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = findViewById(R.id.toolbarNav);
        setSupportActionBar(toolbar);
        lv = findViewById(R.id.listviewSchedule);
        schedule_list = new String[6060];
        // Navigation drawer
        drawer = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Navigation handling
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_people:
                intent = new Intent(ScheduleActivity.this,PeopleActivity.class);
                finish();
                startActivity(intent);

                break;
            case R.id.nav_schedule:
                intent = new Intent(this,ScheduleActivity.class);
                finish();
                startActivity(intent);
                break;

        }

        return true;
    }
    public void onStop () {
        super.onStop();
        intent = new Intent(this,MainActivity.class);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(String token) {
        new  MyAsyncTasks().execute(token);
    }


    private class MyAsyncTasks extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            URL url =null;
            String s=null;
            try {
                url = new URL("https://api.fhict.nl/schedule/autocomplete/Subject");
                HttpURLConnection connection = null;
                connection =(HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept","application/json");
                connection.setRequestProperty("Authorization","Bearer "+params[0]);
                connection.connect();
                InputStream is = connection.getInputStream();
                Scanner scn =new Scanner(is);
                s =scn.useDelimiter("\\Z").next();


                // creating json object
                JSONArray jsonArray = new JSONArray(s);
                for (int i =0; i <jsonArray.length(); i++){
                    JSONObject jo = (JSONObject) jsonArray.get(i);

                    schedule =  "Name:   " + jo.get("name")+ "\n"  +"Kind:      " +jo.get("kind");

                    schedule_list[i]=schedule;
                }



            }catch (IOException | JSONException e){
                e.printStackTrace();
            }
            return s;
        }

        @Override
        public void onPostExecute(String s) {

            super.onPostExecute(s);
           lv.setAdapter(new ScheduleAdapter(getApplicationContext(),schedule_list));
        }
    }
}