package com.receiptifies;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.receiptifies.QR.ERROR_TAG;
import static com.receiptifies.QR.INFO_TAG;

public class Receipts extends AppCompatActivity {
    /*
     Attributes that will handle Receipts Class
     */
    //Attribute that will handle Request Codes.
    public final static int REQUEST_RECEITPS = 2;
    //Attributes that will handle receipts.
    private String mCurrentTextFile;
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
    //Method that writes a file and names it as well as
    private File createTextFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String textFileName = "TEXT_" + timeStamp + "_";
        // TODO API Mismatch.
        // Documents Direct Path will only work in API 19 or greater, currently supporting api 15.
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File text = File.createTempFile(
                textFileName,  /* prefix */
                ".txt",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentTextFile = text.getAbsolutePath();
        Log.i(INFO_TAG, "Text File Path");
        return text;
    }
    //end createImageFile.

    //Method that handles QrScanner Button.
    public void qrScanner(View view) {
            File textFile = null;
            try {
                textFile = createTextFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i(ERROR_TAG, "Debug Here");
            }
            // Continue only if the File was successfully created
            if (textFile != null) {
                Uri textURI = FileProvider.getUriForFile(this,
                        "com.receiptifies",
                        textFile);
                Log.i(INFO_TAG, "TextURI: " + textURI.getPath());

        }
    }
    //end qrScanner.


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
