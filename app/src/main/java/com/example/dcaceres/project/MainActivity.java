package com.example.dcaceres.project;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.jar.Manifest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.JsonMessage;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btLogin;
    Button btSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.mainEt_email);
        etPassword = (EditText)findViewById(R.id.mainEt_password);
        btLogin = (Button)findViewById(R.id.mainBt_login);
        btSignin = (Button)findViewById(R.id.mainBt_sigin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Gson gson = new Gson();

                //armar JSON para login
                OkHttpClient cliente = new OkHttpClient();
                RequestBody jsonBody = new FormBody.Builder().add("correo", etEmail.getText().toString())
                        .add("contrasena", etPassword.getText().toString())
                        .build();

                //armar solicitud de API
                Request request = new Request.Builder().url("http://192.168.1.71/api.phpbackend.com/v1/usuarios/login")
                        .post(jsonBody)
                        .build();

                Log.i("Request", "Request armada");

                Call call = cliente.newCall(request);

                //creación de callback para manejo de solicitudes
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("Request", "Request falla" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String toastMsg;
                        final Boolean ok;
                        String requestMsg = "";

                        if (response.isSuccessful()){

                            Log.i("Response", "Response is successfull");
                            String respuesta = response.body().string();
                            Log.i("Response", respuesta);

                            JsonMessage json = gson.fromJson(respuesta, JsonMessage.class);

                            if ( json.getEstado() == 1 ){
                                Log.i("Intent", "Intent call");
                                ok = true;
                            }else{
                                requestMsg = "Error al tratar de validar el usuario" + json.getMensaje();
                                ok = false;
                            }

                        } else {
                            Log.i("Response", "Response isn't successfull " + response.body().string());
                            requestMsg = "Error al tratar de validar el usuario "+ response.body().string() ;
                            ok = false;
                        }

                        toastMsg = requestMsg;


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (ok) {

                                    Intent toUserActivity = new Intent( MainActivity.this, UserActivity.class );
                                    //toUserActivity.putExtra();
                                    startActivity(toUserActivity);

                                }
                                else{

                                    Toast toast = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG);
                                    toast.show();

                                }
                            }
                        });
                    }
                });




                //ir a otra activity
                //Intent toUserActivity = new Intent(MainActivity.this, UserActivity.class);
                //toUserActivity.putExtra("id", )
                //startActivity(toUserActivity);
            }
        });

        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
