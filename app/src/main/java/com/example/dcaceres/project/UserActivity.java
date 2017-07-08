package com.example.dcaceres.project;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private EditText etPrimerNombre;
    private EditText etPrimerApellido;
    private EditText etCorreo;
    private EditText etCiudad;
    private EditText etDireccion;
    private EditText etTelefono;
    private EditText etContrasena;

    private Button btAgregarUsuario;
    private Button btGenerarQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        etCorreo = (EditText)findViewById(R.id.userET_correo);
        etCiudad = (EditText)findViewById(R.id.userET_ciudad);
        etDireccion = (EditText)findViewById(R.id.userET_direccion);
        etTelefono = (EditText)findViewById(R.id.userET_telefono);
        etPrimerApellido = (EditText)findViewById(R.id.userET_primerApellido);
        etPrimerNombre = (EditText)findViewById(R.id.userET_primerNombre);
        etContrasena = (EditText)findViewById(R.id.userET_contrasena);


        btAgregarUsuario = (Button)findViewById(R.id.userBT_agregar);
        btGenerarQR = (Button)findViewById(R.id.userBT_generarQR);

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

            crearUsuario();

            }
        });

    }

    private void crearUsuario() {

        //armar JSON para login
        OkHttpClient cliente = new OkHttpClient();
        RequestBody jsonBody = new FormBody.Builder().add("correo", etCorreo.getText().toString())
                .add("primerNombre", etPrimerNombre.getText().toString())
                .add("primerApellido", etPrimerApellido.getText().toString())
                .add("contrasena", etContrasena.getText().toString())
//                .add("ciudad", etCiudad.getText().toString())
//                .add("descripcion", etDescripcion.getText().toString())
//                .add("direccion", etDireccion.getText().toString())
//                .add("nombre", etNombre.getText().toString())
//                .add("ocupacion", etOcupacion.getText().toString())
//                .add("telefono", etTelefono.getText().toString())
                .build();

        Log.i("Request", jsonBody.toString());
        //armar solicitud de API
        Request request = new Request.Builder().url("http://192.168.1.69/api.phpbackend.com/v1/usuarios/create")
                .post(jsonBody)
                .build();

        Call call = cliente.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_LONG).show();
                         }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final Boolean isSuccessful;

                isSuccessful = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccessful){
                            Toast.makeText(getApplicationContext(), "Usuario creado exitosamente", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });



    }

}
