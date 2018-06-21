package com.receiptifies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Receipts extends AppCompatActivity {
    /*
    On Create Method
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);
    }
    /*
    Methods that will handle Receipts Interface
    */
    //TODO Methods go Here.

    /*
    States Activity
    */
    //Override Finish.
    @Override
    public void finish() {
        super.finish();
        //TODO Animation Receipts: Enable this to allow the slide in left/right animation.
        //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    //end finish.

}
