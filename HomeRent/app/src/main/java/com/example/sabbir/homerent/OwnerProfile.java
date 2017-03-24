package com.example.sabbir.homerent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OwnerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);
        getSupportActionBar().hide();
    }
}
