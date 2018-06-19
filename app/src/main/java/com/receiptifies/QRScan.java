package com.receiptifies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QRScan extends AppCompatActivity {
    //Request Codes
    public final int REQUEST_QRSCAN = 2;
    //Attributes for QrScan
    public IntentIntegrator qrScan;
    public TextView qr_textViewer;
    //OnsCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        qr_textViewer = (TextView) (findViewById(R.id.qr_textViewer));
        qrScan.initiateScan();
        finishActivity(REQUEST_QRSCAN);
    }
    //end of OnCreate.


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null) {
                Toast.makeText(this,"Decoded Failure",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Message Decoded", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = new JSONObject(intentResult.getContents());
                    qr_textViewer.setText(obj.getString("coddedMessage"));
                } catch (JSONException e) {
                    Toast.makeText(this, "Error Check LogCat", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
