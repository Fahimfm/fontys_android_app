package com.example.fhict_companion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Intent intent;
    Button buttonSchedule;
    Button btnlogin;
    Button btnpeople;
    FrameLayout frame;
    String acquiredToken;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarNav);
        setSupportActionBar(toolbar);


        // Navigation drawer
        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Navigation handling
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tv = findViewById(R.id.textViewmessage);
        buttonSchedule = findViewById(R.id.buttonSchedule);
        buttonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScheduleActivity();
            }
        });
        btnpeople = findViewById(R.id.buttonPeople);

        btnpeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
                startActivity(intent);
            }
        });
        btnlogin = findViewById(R.id.buttonLogin);
        frame = findViewById(R.id.frameLayout);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmanager = getSupportFragmentManager();
                FragmentTransaction fragtrans = fragmanager.beginTransaction();
                TokenFragment t = new TokenFragment();
                fragtrans.replace(R.id.frameLayout,t, "ok");
                fragtrans.commit();
            }
        });

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_people:
                intent = new Intent(this,PeopleActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_schedule:
                intent = new Intent(this,ScheduleActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }

    public void openScheduleActivity()
    {
        Intent intent = new Intent(this,ScheduleActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(String token) {
        tv.setText("Success!");

    }
}