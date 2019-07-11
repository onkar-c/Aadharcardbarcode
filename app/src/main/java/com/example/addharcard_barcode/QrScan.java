
package com.example.addharcard_barcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * @author onkar.chopade
 */

public class QrScan extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewName, textViewAddress, textViewUuid, textViewGender, textViewDob;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);

        textViewName =  findViewById(R.id.textViewName);
        textViewAddress =  findViewById(R.id.textViewAddress);
        textViewUuid = findViewById(R.id.textViewUuid);
        textViewGender = findViewById(R.id.textViewGender);
        textViewDob = findViewById(R.id.textViewDob);

        //intializing scan object
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(true);

        //attaching onclick listener
        findViewById(R.id.buttonScan).setOnClickListener(this);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    Log.i("Card data", result.getContents());
                    AadhaarCard newCard = new AadhaarXMLParser().parse(result.getContents());
                    textViewUuid.setText(newCard.getFormattedUID());
                    textViewName.setText(newCard.name);
                    textViewAddress.setText(newCard.getAddress());
                    textViewGender.setText(newCard.gender);
                    textViewDob.setText(newCard.dob);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }

}
