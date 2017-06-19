package com.example.dcaceres.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    EditText etCorreo;
    EditText etCiudad;
    EditText etDescripcion;
    EditText etDireccion;
    EditText etNombre;
    EditText etOcupacion;
    EditText etTelefono;
    Button btAgregarUsuario;
    Button btGenerarQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        etCorreo = (EditText)findViewById(R.id.userET_correo);
        etCiudad = (EditText)findViewById(R.id.userET_ciudad);
        etDescripcion = (EditText)findViewById(R.id.userET_descripcion);
        etDireccion = (EditText)findViewById(R.id.userET_direccion);
        etNombre = (EditText)findViewById(R.id.userET_nombre);
        etOcupacion = (EditText)findViewById(R.id.userET_ocupacion);
        etTelefono = (EditText)findViewById(R.id.userET_telefono);
        btAgregarUsuario = (Button)findViewById(R.id.userBT_agregar);
        btGenerarQR = (Button)findViewById(R.id.userBT_generarQR);

        btGenerarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermission();
                }

                Intent toScannerActivity = new Intent( UserActivity.this, ScannerActivity.class );
                //toUserActivity.putExtra();
                startActivity(toScannerActivity);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission(){

        final int REQUEST_CODE_ASK_PERMISSIONS = 1;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                Toast.makeText(this, "Solicitando Permisos", Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(this, "Permiso ya otorgado", Toast.LENGTH_LONG).show();
        }

    }

}
