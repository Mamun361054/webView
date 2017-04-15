package com.example.sabbir.homerent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SignInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

    }
    public void ownersignin(View v)
    {
        //TextView title= (TextView) findViewById(R.id.textView2);

        Intent i=new Intent(SignInPage.this,Login.class);
        startActivity(i);
    }
    public void rentersignin(View v)
    {
        //TextView title= (TextView) findViewById(R.id.textView2);

        Intent i=new Intent(SignInPage.this,login2.class);
        startActivity(i);
    }
}
