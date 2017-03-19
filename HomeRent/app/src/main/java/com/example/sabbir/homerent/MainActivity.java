package com.example.sabbir.homerent;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    TextView o1;

    public class backgroundTask extends AsyncTask<Void,Integer,Void> {

        int progress=0;

        @Override
        protected void onPreExecute() {
            progress = 0;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            goToDashBoard();
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(progress<100){
                progress++;
                publishProgress(progress);
                SystemClock.sleep(20);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        o1 = (TextView) findViewById(R.id.txt);
       Typeface font1= Typeface.createFromAsset(getAssets(), "ALBAS.ttf");
       o1.setTypeface(font1);


        progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
        progressBar.setProgress(0);
        new backgroundTask().execute();
    }

    public void goToDashBoard(){
        Intent intent = new Intent(MainActivity.this,Front.class);
        startActivity(intent);
        finish();
    }

}

