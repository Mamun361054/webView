package com.example.sabbir.homerent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

    }
    public void homeownerreg(View v)
    {

        Intent i=new Intent(SignUpPage.this,SignUpOwner.class);
        startActivity(i);
    }
    public void profile(View v)
    {

        Intent i=new Intent(SignUpPage.this,OwnerProfile.class);
        startActivity(i);
    }
}
