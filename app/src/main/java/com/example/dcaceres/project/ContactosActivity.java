package com.example.dcaceres.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import model.Contacto;
import utils.AdapterContacto;
import utils.AsyncTaskAPIGet;

public class ContactosActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private final int       REQUEST_CODE_ASK_PERMISSIONS = 10;
    private final Character ACTION_READ = 'R';
    private final Character ACTION_CREATE = 'C';
    private final String API_RESOURCE_CONTACTOS = "contactos";

    private ListView listaContactos;
    private Button agregarContacto;
    private Integer idUsuario;
    private List<Contacto> contactos = null;
    private ZXingScannerView scannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        //obtener id de usuario logueado
        idUsuario = getIntent().getIntExtra("idUsuario", 0);
        requestApiGetContacts(idUsuario);

        listaContactos = (ListView)findViewById(R.id.contactosLV_lista);
        agregarContacto = (Button) findViewById(R.id.contactosBT_addCont);

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto contacto = contactos.get(position);
                Integer idContacto = Integer.parseInt(contacto.getIdUsuario2());
                viewContacto(idContacto, ACTION_READ);
            }
        });

        agregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermission();
                }else {
                    escanearQR();
                }
            }
        });
    }

    public void setContactos(List<Contacto> contactos){
        this.contactos = contactos;
    }

    public void setAdapterContactos(){

        if (contactos != null ){

            AdapterContacto adapter = new AdapterContacto(ContactosActivity.this, contactos);
            listaContactos.setAdapter(adapter);

        }
    }

    private void requestApiGetContacts(Integer idUsuario){

        //limpiar listado de contactos
        if (contactos != null){
            contactos.clear();
        }

        new AsyncTaskAPIGet(this, API_RESOURCE_CONTACTOS).execute(idUsuario);
    }

    private void escanearQR(){

        Log.i("Scanner", "Ejecutando");
        scannerView = new ZXingScannerView(ContactosActivity.this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    private void viewContacto(Integer idContacto, Character action){

        Intent toContactoActivity = new Intent( ContactosActivity.this, ContactoActivity.class );
        toContactoActivity.putExtra("idUsuario", idUsuario);
        toContactoActivity.putExtra("idContacto", idContacto);
        toContactoActivity.putExtra("action", action);
        startActivity(toContactoActivity);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Permiso negado anteriormente", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA }, REQUEST_CODE_ASK_PERMISSIONS);
                Toast.makeText(this, "Solicitando Permisos", Toast.LENGTH_LONG).show();
            }
        }else{
            escanearQR();
        }

    }

    @Override
    public void handleResult(Result result) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Codigo QR");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        scannerView.resumeCameraPreview(this);

        Integer idContacto = Integer.parseInt(result.getText());
        viewContacto(idContacto, ACTION_CREATE);
    }

    @Override
    protected void onPause() {

        super.onPause();

        if ( scannerView != null ){
            scannerView.stopCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    escanearQR();
                } else {
                    Toast.makeText(this, "Permiso no concedido", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}
