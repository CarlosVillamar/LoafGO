package com.example.carlos.loafgotruckmodule;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReaderActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    /** this activity uses the ZXing API to create the barcode scanner view*/

    final String TAG = "ReaderActivity";
     private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        scannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
            Alert(result);
    }

    public void Alert(Result rawResult){
        /** in future verisons of the project we would pass in the result here, and pass it into an
         * object oriented class or pass the information into a database and do something with it.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(ReaderActivity.this);
        builder.setTitle("Scan result");
        builder.setMessage("Result :"+rawResult.getText()+"\nType :"+rawResult.getBarcodeFormat().toString())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // back to previous activity
                        finish();

                    }
                })
                .setNegativeButton("Scan Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
// If you would like to resume scanning, call this method below:
                        scannerView.resumeCameraPreview(ReaderActivity.this);
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
