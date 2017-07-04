package com.example.dcaceres.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        Integer idContacto = getIntent().getIntExtra("idContacto", 0);

    }

    private void requestApiGetContacto(Integer idUsuario, Integer idContacto){

        OkHttpClient cliente = new OkHttpClient();

        //armar solicitud de API
        Request request = new Request.Builder().url("http://192.168.1.66/api.phpbackend.com/v1/contactos/" + idUsuario+"," + idContacto)
                .get()
                .build();

        Call call = cliente.newCall(request);

        //creación de callback para manejo de solicitudes
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Request", "Error al buscar datos de contacto");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final Gson gson = new Gson();

                if (response.isSuccessful()){

//                    String respuesta = response.body().string();
//                    ResponseContacto responseContactos = gson.fromJson(respuesta, ResponseContacto.class);
//
//                    Log.i("Request", String.valueOf(responseContactos));
//                    contactos = responseContactos.getContactos();
//
//                    AdapterContacto adapter = new AdapterContacto(ContactosActivity.this, contactos);
//                    lista.setAdapter(adapter);
                }
            }
        });

    }
}
