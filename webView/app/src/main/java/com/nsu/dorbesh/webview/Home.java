package com.nsu.dorbesh.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Profile p = new Profile();
        ft.add(R.id.fragment_container,p);
        ft.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_calender) {
            startActivity(new Intent(Home.this,MainActivity.class));
            return true;
        }else if (id == R.id.directories){

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Directories d = new Directories();
            ft.replace(R.id.fragment_container,d);
            ft.commit();

        }else if (id == R.id.event){

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Events e = new Events();
            ft.replace(R.id.fragment_container,e);
            ft.commit();

        }else if (id == R.id.admission){

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Admission ad = new Admission();
            ft.replace(R.id.fragment_container,ad);
            ft.commit();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Profile p = new Profile();
            ft.replace(R.id.fragment_container,p);
            ft.commit();
           // Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
        }else if (id == R.id.cgpa) {
            startActivity(new Intent(Home.this,splashscreen.class));
        }else if (id == R.id.account_finance) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            AccountingFinance af = new AccountingFinance();
            ft.replace(R.id.fragment_container,af);
            ft.commit();

        }else if (id == R.id.account_economics) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Economics eco = new Economics();
            ft.replace(R.id.fragment_container,eco);
            ft.commit();

        }else if(id == R.id.account_management) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Management mgt = new Management();
            ft.replace(R.id.fragment_container, mgt);
            ft.commit();
        }else if(id == R.id.account_marketing) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Marketing mar = new Marketing();
            ft.replace(R.id.fragment_container, mar);
            ft.commit();
        }else if(id == R.id.archi) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Architecture arc = new Architecture();
            ft.replace(R.id.fragment_container, arc);
            ft.commit();
        }else if(id == R.id.civil) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Civil civ = new Civil();
            ft.replace(R.id.fragment_container, civ);
            ft.commit();
        }else if(id == R.id.ece) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Ece ece = new Ece();
            ft.replace(R.id.fragment_container, ece);
            ft.commit();
        }else if(id == R.id.math) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Math mat = new Math();
            ft.replace(R.id.fragment_container, mat);
            ft.commit();
        }else if(id == R.id.english) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            English eng = new English();
            ft.replace(R.id.fragment_container, eng);
            ft.commit();
        }else if(id == R.id.philosophy) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Philosophy phi = new Philosophy();
            ft.replace(R.id.fragment_container, phi);
            ft.commit();
        }else if(id == R.id.political) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Political pol = new Political();
            ft.replace(R.id.fragment_container, pol);
            ft.commit();
        }else if(id == R.id.law) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Law law = new Law();
            ft.replace(R.id.fragment_container, law);
            ft.commit();
        }else if(id == R.id.biochemistry) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            BioChemistry bio = new BioChemistry();
            ft.replace(R.id.fragment_container, bio);
            ft.commit();
        }else if(id == R.id.environment) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Environment en = new Environment();
            ft.replace(R.id.fragment_container, en);
            ft.commit();
        }else if(id == R.id.pharma) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Pharmacy phar = new Pharmacy();
            ft.replace(R.id.fragment_container, phar);
            ft.commit();
        }else if(id == R.id.health) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Health hel = new Health();
            ft.replace(R.id.fragment_container, hel);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
