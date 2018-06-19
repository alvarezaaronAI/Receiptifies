package com.receiptifies;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.integration.android.IntentIntegrator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QR extends AppCompatActivity {
    //Attributes to handle permissions.
    public final static int REQUEST_CAMERA = 1;
    //Log Cat String Tags.
    public static final String INFO_TAG = "STATE";
    private static final String ERROR_TAG = "ERROR";
    //Any other Attributes that will handle QR Scan.
    public boolean permissionGranted;
    public String mCurrentPhotoPath;
    public IntentIntegrator qrScan;

    //Create XML on Activity QR Start.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Toast.makeText(this, "You are now in the QR Scanning.", Toast.LENGTH_SHORT).show();
        Log.i(INFO_TAG, "isPermissionGranted : " + permissionGranted);
        //Checking Permissions for the App.
        boolean isPermission;
        if (!permissionGranted) {
            isPermission = checkPermissions();
            Log.i(INFO_TAG, "isPermission :" + isPermission);
        }
    }
    //end onCreate.

//This code will work with the current API MIN 15 and greater.

    //Checks and grants permission to use camera, if and only if is not yet accepted.
    public boolean checkPermissions() {
        //Checking for Write External Storage Permission.
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "This app will only work with usable external storage.", Toast.LENGTH_LONG).show();
            return false;
        }
        //Checking for all permissions manifest State.
        int permissionCheckCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionCheckWritable = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheckReadable = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        //TODO Write Code to check permissions for Geo Location.
        // Permissions Check Int val will result 0 if all permissions was granted, other wise < 0 if 1 or many permissions were denied.
        //TODO Edit a better way to check all permissions at once without needed to add.
        int permissionsCheck = permissionCheckCamera + permissionCheckReadable + permissionCheckWritable;
        //Allow the user to request permissions on the spot, if he wants.
        if (permissionsCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CAMERA);
            return false;
        } else {
            return true;
        }
    }
    //end checkPermissions.

    //Method that checks if External Device is Writable.
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
    //end isExternalStorageWritable

    //Method that handles permission response.
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //TODO Write code to check permissions result overall.
        if (requestCode == REQUEST_CAMERA) {
            //Receive permission result camera permission.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Camera Permissions has been granted, preview can be displayed.

                //TODO Show Camera preview.
                //Write Code here...

                Toast.makeText(this, "Camera is now Open.", Toast.LENGTH_SHORT).show();
                permissionGranted = true;
            } else {
                //Else all other permissions was denied. permission was denied.
                Toast.makeText(this, "Camera permissions was denied.", Toast.LENGTH_LONG).show();
            }
        } else {
            //show permission result.
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //end onRequestPermissionsResult.

    /*
    // Modifying Camera QR Button
    //Method that handles button click.
    public void qrScanner(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i(ERROR_TAG, "Debug Here");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.receiptifies",
                        photoFile);
                Log.i(INFO_TAG, "PhotoURI: " + photoURI.getPath());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CAMERA);
            }
        }
    }
    //end qrScanner.
    */
    //Method that handles button click.
    public void qrScanner(View view) {
        Intent intent = new Intent(this,QRScan.class);
        startActivity(intent);
    }
    //end qrScanner.

    //Method that writes a file and names it.
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    //end createImageFile.

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
