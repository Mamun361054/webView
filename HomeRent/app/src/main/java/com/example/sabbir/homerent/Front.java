package com.example.sabbir.homerent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Front extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

    }
    public void signup(View v)
    {
        //TextView title= (TextView) findViewById(R.id.textView2);

        Intent i=new Intent(Front.this,SignUpPage.class);
        startActivity(i);
    }
    public void signin(View v)
    {
        //TextView title= (TextView) findViewById(R.id.textView2);

        Intent i=new Intent(Front.this,SignInPage.class);
        startActivity(i);
    }
}
