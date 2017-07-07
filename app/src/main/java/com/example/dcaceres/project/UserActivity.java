package com.example.dcaceres.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserActivity extends AppCompatActivity //implements ZXingScannerView.ResultHandler
{

    private EditText etCorreo;
    private EditText etCiudad;
    private EditText etDescripcion;
    private EditText etDireccion;
    private EditText etNombre;
    private EditText etOcupacion;
    private EditText etTelefono;
    private Button btAgregarUsuario;
    private Button btGenerarQR;
    private ZXingScannerView scannerView;
    private Character option;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 10;

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

        option = getIntent().getCharExtra("option", 'R');

        switch (option){
            case 'R':
                break;
            case 'C':
                btAgregarUsuario.setText("Crear Usuario");
                btGenerarQR.setVisibility(View.INVISIBLE);
                break;
            case 'U':
                break;

        }

        btGenerarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                try {

                    BitMatrix bitMatrix = multiFormatWriter.encode("1234567890", BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    Intent intent = new Intent(UserActivity.this, QRActivity.class);
                    intent.putExtra("QRCode", bitmap);
                    startActivity(intent);


                }catch (WriterException exception){
                    exception.printStackTrace();
                }


            }
        });

        btAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (option){
                    case 'C':
                        crearUsuario();
                        break;

//                    default:
//                        escanearQR();
//                        break;

                }

            }
        });

    }

//    @Override
//    public void handleResult(Result result) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Codigo QR");
//        builder.setMessage(result.getText());
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        scannerView.resumeCameraPreview(this);
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        scannerView.stopCamera();
//    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void checkPermission(){
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//                Toast.makeText(this, "Permiso negado anteriormente", Toast.LENGTH_LONG).show();
//            } else {
//
//                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA }, REQUEST_CODE_ASK_PERMISSIONS);
//                Toast.makeText(this, "Solicitando Permisos", Toast.LENGTH_LONG).show();
//
//            }
//        }else{
//            Toast.makeText(this, "Permiso ya otorgado", Toast.LENGTH_LONG).show();
//        }
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        Toast.makeText(this, "OnRequestPermission", Toast.LENGTH_LONG).show();
//        switch (requestCode) {
//
//            case REQUEST_CODE_ASK_PERMISSIONS:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, "Permiso no concedido", Toast.LENGTH_SHORT).show();
//                }
//                return;
//        }
//    }

    private void crearUsuario(){

        //armar JSON para login
        OkHttpClient cliente = new OkHttpClient();
        RequestBody jsonBody = new FormBody.Builder().add("correo", etCorreo.getText().toString())
                .add("ciudad", etCiudad.getText().toString())
                .add("descripcion", etDescripcion.getText().toString())
                .add("direccion", etDireccion.getText().toString())
                .add("nombre", etNombre.getText().toString())
                .add("ocupacion", etOcupacion.getText().toString())
                .add("telefono", etTelefono.getText().toString())
                .build();

        //armar solicitud de API
        Request request = new Request.Builder().url("http://192.168.1.71/api.phpbackend.com/v1/usuarios/create")
                .post(jsonBody)
                .build();

        Call call = cliente.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

//    private void escanearQR(){
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkPermission();
//        }
//
//        Log.i("Scanner", "Ejecutando");
//        scannerView = new ZXingScannerView(UserActivity.this);
//        setContentView(scannerView);
//        scannerView.setResultHandler(UserActivity.this);
//        scannerView.startCamera();
//    }


}
