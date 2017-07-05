package com.example.dcaceres.project;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import model.Contacto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utils.AsyncTaskContactos;

public class ContactoActivity extends AppCompatActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        setTabs();

        Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        Integer idContacto = getIntent().getIntExtra("idContacto", 0);

        new AsyncTaskContactos(this).execute(idUsuario, idContacto);
    }

    private void setTabs(){

        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("datosCont");
        spec.setContent(R.id.contactoTAB_datosCont);
        spec.setIndicator("", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("datosRel");
        spec.setContent(R.id.contactoTAB_datosRel);
        spec.setIndicator("", ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_map));
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }

    public void setData(List<Contacto> contactos){

        //obtener el primer contacto... es solo 1
        Contacto contacto = contactos.get(0);

        EditText nombres = (EditText)findViewById(R.id.contactoTV_nombres);
        EditText telefono = (EditText) findViewById(R.id.contactoET_telefono);
        EditText correo = (EditText) findViewById(R.id.contactoET_correo);
        EditText ocupacion = (EditText) findViewById(R.id.contactoTV_ocupacion);
        EditText ciudad = (EditText) findViewById(R.id.contactoET_ciudad);
        EditText descrip = (EditText) findViewById(R.id.contactoET_descrip);
        EditText direccion = (EditText) findViewById(R.id.contactoET_direccion);
        EditText lugar = (EditText) findViewById(R.id.contactoET_lugar);
        EditText fecha = (EditText) findViewById(R.id.contactoET_fecha);
        EditText hora = (EditText) findViewById(R.id.contactoET_hora);

        nombres.setText(contacto.getPrimerNombre() + " " + contacto.getPrimerApellido());
        telefono.setText(contacto.getTelefono());
        correo.setText(contacto.getCorreo());
        ocupacion.setText(contacto.getOcupacion());
        ciudad.setText(contacto.getCiudad());
        //descrip.setText(contacto.getD);
        direccion.setText(contacto.getDireccion());
        lugar.setText(contacto.getLugar());
        fecha.setText(contacto.getFecha());
        hora.setText(contacto.getHora());
    }

/*    private void requestApiGetContacto(Integer idUsuario, Integer idContacto){

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

    }*/
}
