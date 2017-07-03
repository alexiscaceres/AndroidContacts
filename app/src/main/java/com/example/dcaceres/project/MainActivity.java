package com.example.dcaceres.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.ResponseLogin;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences.Editor editShared;
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btSignin;
    private Integer idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancias elementos de interfaz grafica
        etEmail = (EditText)findViewById(R.id.mainEt_email);
        etPassword = (EditText)findViewById(R.id.mainEt_password);
        btLogin = (Button)findViewById(R.id.mainBt_login);
        btSignin = (Button)findViewById(R.id.mainBt_sigin);

        etEmail.setText("prueba@prueba.com");
        etPassword.setText("12345");

        //obtener id de usuario
        SharedPreferences sharedPref = getSharedPreferences("AppData", Context.MODE_PRIVATE);
        editShared = sharedPref.edit();
        idUsuario = sharedPref.getInt("idUsuario", 0);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (idUsuario != 0 ){
                    callContactosActivity();
                } else {
                    requestApiLogin();
                }

            }
        });

        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ir a Activity de usuario
                Intent toUserActivity = new Intent(MainActivity.this, UserActivity.class);
                toUserActivity.putExtra("option", 'C');
                startActivity(toUserActivity);

            }
        });
    }

    private void requestApiLogin(){

        final Gson gson = new Gson();

        //armar JSON para login
        OkHttpClient cliente = new OkHttpClient();
        RequestBody jsonBody = new FormBody.Builder().add("correo", etEmail.getText().toString())
                .add("contrasena", etPassword.getText().toString())
                .build();

        //armar solicitud de API
        Request request = new Request.Builder().url("http://192.168.1.66/api.phpbackend.com/v1/usuarios/login")
                .post(jsonBody)
                .build();

        Call call = cliente.newCall(request);

        //creación de callback para manejo de solicitudes
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                /// TOCA CREAR UN MENSAJE PARA CUANDO FALLE
                Log.i("Request", "Error al llamar API");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String toastMsg;
                final Boolean ok;
                String requestMsg = "";

                if (response.isSuccessful()){

                    Log.i("Request", "Successful");

                    String respuesta = response.body().string();
                    ResponseLogin json = gson.fromJson(respuesta, ResponseLogin.class);

                    if ( json.getEstado() == 1 ){
                        ok = true;

                        idUsuario = 1;
                        editShared.putInt("idUsuario",idUsuario).commit();

                    }else{
                        requestMsg = "Error al tratar de validar el usuario" + json.getMensaje();
                        ok = false;
                    }

                } else {
                    requestMsg = "Error al tratar de validar el usuario "+ response.body().string() ;
                    ok = false;
                }

                toastMsg = requestMsg;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (ok) {

                            //llamar activity utilizando el IdUsuario
                            callContactosActivity();
                        }
                        else{

                            Toast toast = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG);
                            toast.show();

                        }
                    }
                });
            }
        });
    }

    private void callContactosActivity() {

        Intent toContactosActivity = new Intent( MainActivity.this, ContactosActivity.class );
        toContactosActivity.putExtra("idUsuario", idUsuario);
        startActivity(toContactosActivity);
    }
}
