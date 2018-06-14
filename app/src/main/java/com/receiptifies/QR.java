package com.receiptifies;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class QR extends AppCompatActivity {
    public final static int REQUEST_CAMERA = 1;
    public final String PERMISSIONS_TAG = "Permission";
    public boolean permissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        //Checking Permissions for the App
        Log.i(PERMISSIONS_TAG  , "" + permissionGranted);
        if (!permissionGranted) {
            checkPermissions();
        }

    }

    //This code will work with the current API MIN 15 and greater.
    public boolean checkPermissions() {

        //Checking for Camera Permission.
        //Returning camera permission state.
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            return false;
        } else {
            return true;
        }
        //If more than one permission you can add it in the if statements.
    }
    //end checkPermissions.

    //Method that handles permission response.
    //This method will run right after permissions check.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            //Receive permission result camera permission.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Camera Permission has been granted, preview can be displayed.
                //TODO Show Camera preview.
                Toast.makeText(this, "Camera is now Open.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Camera permission was denied.", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //end onRequestPermissionsResult.


    //This code will only work with API MIN Level 23 and greater.
    /*
    private void isCameraPermissionGranted() {
        //checks if permission was granted.
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Camera Permission was approved.", Toast.LENGTH_LONG)
                    .show();
            //Permission was approved.

        } else {
            //Permission was denied.
             //Tell user what is the feature use for.
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera Permission is needed to scan QR Codes.", Toast.LENGTH_LONG)
                        .show();
            }

            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);

        }

    }
    //end isCameraPermissionGranted.
    */

}
