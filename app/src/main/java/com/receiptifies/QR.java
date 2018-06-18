package com.receiptifies;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class QR extends AppCompatActivity {
    //Attributes to handle QR Scan.
    public final static int REQUEST_CAMERA = 1;
    //Log Cat String Tags
    public final String INFO_TAG = "STATE";
    //
    public boolean permissionGranted;

    //Create XML on Activity QR Start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Toast.makeText(this, "You are now in the QR Scanning.", Toast.LENGTH_SHORT).show();
        Log.i(INFO_TAG, "isPermissionGranted : " + permissionGranted);

        //Checking Permissions for the App
        boolean isPermission;
        if (!permissionGranted) {
            isPermission = checkPermissions();
            Log.i(INFO_TAG, "isPermission :" + isPermission);
        }
    }
    //end onCreate

//This code will work with the current API MIN 15 and greater.

    //Checks and grants permission to use camera, if and only if is not yet accepted.
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
        //Checking for Write External Storage.
            //TODO Write code to check permissions for external storage.

    }
    //end checkPermissions.

    //Method that handles permission response.
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            //Receive permission result camera permission.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Camera Permission has been granted, preview can be displayed.

                //TODO Show Camera preview.
                //Write Code here...

                Toast.makeText(this, "Camera is now Open.", Toast.LENGTH_SHORT).show();
                permissionGranted = true;
            } else {
                //Else camera permission was denied.
                Toast.makeText(this, "Camera permission was denied.", Toast.LENGTH_LONG).show();
            }
        } else {
            //show permission result.
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //end onRequestPermissionsResult.

    //Method that handles button click.
    public void qrScanner(View view) {

//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_CAMERA);
//        }
    }
    //end qrScanner.

/*
//This code will only work with API MIN Level 23 and greater.

    //Grants permission to use camera, if and only if permission is not yet accepted.
    private void isCameraPermissionGranted() {
        //checks if permission was granted.
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Camera Permission was approved.", Toast.LENGTH_LONG)
                    .show();
            //Permission was approved.
            // TO DO Write code to show camera preview.
            //Write Code here.

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
