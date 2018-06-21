package com.receiptifies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Home extends AppCompatActivity {

    //Create XML On Activity Home Start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    //end onCreate

    //Method that handles camera in home activity.
    public void cameraPreview(View view) {
        Snackbar.make(view, "You are now in QR Scanning ", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        //creating action to go to QR Scanning
        Intent intent = new Intent(Home.this, QR.class);
        startActivity(intent);
    }
    //end of cameraPreview.

    //Method that will handle receipts in home activity.
    public void receiptsPreview(View view) {
        Snackbar.make(view, "You are now in Receipts Page", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        //creating action to go to QR Scanning
        Intent intent = new Intent(Home.this, Receipts.class);
        startActivity(intent);
        overridePendingTransition(R.anim);
    }
    //end receiptsPreview.
}
