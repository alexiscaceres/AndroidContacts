package com.example.dcaceres.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        Integer idContacto = getIntent().getIntExtra("idContacto", 0);



    }

    private void requestApiGetContacto(){

    }
}
