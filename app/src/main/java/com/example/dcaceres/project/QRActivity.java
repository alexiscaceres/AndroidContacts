package com.example.dcaceres.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class QRActivity extends AppCompatActivity {

    private ImageView ivQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        ivQRCode = (ImageView)findViewById(R.id.qrIV_qrcode);
        Bitmap bitmap = getIntent().getParcelableExtra("QRCode");
        ivQRCode.setImageBitmap(bitmap);
    }
}
