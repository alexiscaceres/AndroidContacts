package com.example.dcaceres.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import model.Contacto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utils.AdapterContacto;
import utils.ResponseContactos;

public class ContactosActivity extends AppCompatActivity {

    private ListView lista;
    private List<Contacto> contactos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        //obtener id de usuario logueado
        Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        obtenerContactos(idUsuario);

        lista = (ListView)findViewById(R.id.contactosLV_lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent toContactoActivity = new Intent( ContactosActivity.this, ContactoActivity.class );
                toContactoActivity.putExtra("idUsuario", 1);
                toContactoActivity.putExtra("idContacto", 2);
                startActivity(toContactoActivity);
            }
        });

    }

    private void obtenerContactos( Integer idUsuario){

        //limpiar listado de contactos
        if (contactos != null){
            contactos.clear();
        }

        OkHttpClient cliente = new OkHttpClient();

        //armar solicitud de API
        Request request = new Request.Builder().url("http://192.168.1.66/api.phpbackend.com/v1/contactos/"+ idUsuario)
                .get()
                .build();

        Call call = cliente.newCall(request);

        //creación de callback para manejo de solicitudes
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Request", "Error al buscar contactos de usuario");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final Gson gson = new Gson();

                if (response.isSuccessful()){

                    String respuesta = response.body().string();
                    ResponseContactos responseContactos = gson.fromJson(respuesta, ResponseContactos.class);

                    Log.i("Request", String.valueOf(responseContactos));
                    contactos = responseContactos.getContactos();

                    AdapterContacto adapter = new AdapterContacto(ContactosActivity.this, contactos);
                    lista.setAdapter(adapter);
                }
            }
        });
    }
}
