package com.example.carlos.loafgotruckmodule;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.camerakit.CameraKit;
import com.camerakit.CameraKitView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.List;
//import com.google.zxing.Result;

//import me.dm7.barcodescanner.zxing.ZXingScannerView;
//TODO:Rebuild this class with ML kit

public class ReaderActivity extends AppCompatActivity  {
    /** this activity uses the ZXing API to create the barcode scanner view*/

    final String TAG = "ReaderActivity";
    //     private ZXingScannerView scannerView;
    private CameraKitView cameraKitView;
    private Button captureBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        scannerView = new ZXingScannerView(this);
//        setContentView(scannerView);
        cameraKitView =findViewById(R.id.camera);
//        captureBtn = findViewById(R.id.btnCapture);
//        captureBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
//                    @Override
//                    public void onImage(CameraKitView cameraKitView, byte[] bytes) {
//                        Bitmap bitmap = cameraKitView.getDrawingCache();
//                        getQRinfo(bitmap);
//                    }
//                });
//            }
//        });
//        setContentView(cameraKitView);
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_DATA_MATRIX|FirebaseVisionBarcode.FORMAT_QR_CODE)
                        .build();

        FirebaseVisionBarcodeDetector firebaseVisionBarcodeDetector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
        Toast.makeText(getApplicationContext(),TAG,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
//        cameraKitView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
//        cameraKitView.onResume();
//        scannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//        scannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
//        cameraKitView.onPause();
//        scannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    protected void onStop() {
//        cameraKitView.onStop();
        super.onStop();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getQRinfo(Bitmap bitmap){
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_DATA_MATRIX|FirebaseVisionBarcode.FORMAT_QR_CODE)
                        .build();
        FirebaseVisionBarcodeDetector firebaseVisionBarcodeDetector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
        Task<List<FirebaseVisionBarcode>> task = firebaseVisionBarcodeDetector.detectInImage(firebaseVisionImage);
//        task.addOnSuccessListener()
    }

//    @Override
//    public void handleResult(Result result) {
//            Alert(result);
//    }

//    public void Alert(Result rawResult){
//        /** in future verisons of the project we would pass in the result here, and pass it into an
//         * object oriented class or pass the information into a database and do something with it.
//         */
//        AlertDialog.Builder builder = new AlertDialog.Builder(ReaderActivity.this);
//        builder.setTitle("Scan result");
//        builder.setMessage("Result :"+rawResult.getText()+"\nType :"+rawResult.getBarcodeFormat().toString())
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // back to previous activity
//                        finish();
//
//                    }
//                })
//                .setNegativeButton("Scan Again", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//// If you would like to resume scanning, call this method below:
//                        scannerView.resumeCameraPreview(ReaderActivity.this);
//                    }
//                });
//        // Create the AlertDialog object and return it
//        builder.create().show();
//    }
}
