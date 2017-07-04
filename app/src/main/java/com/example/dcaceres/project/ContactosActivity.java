package com.example.dcaceres.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import model.Contactos;
import utils.AdapterContacto;
import utils.AsyncTaskContactos;

public class ContactosActivity extends AppCompatActivity {

    private ListView lista;
    private List<Contactos> contactos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        //obtener id de usuario logueado
        final Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        requestApiGetContacts(idUsuario);

        lista = (ListView)findViewById(R.id.contactosLV_lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contactos contacto = contactos.get(position);
                Intent toContactoActivity = new Intent( ContactosActivity.this, ContactoActivity.class );
                toContactoActivity.putExtra("idUsuario", idUsuario);
                toContactoActivity.putExtra("idContacto", contacto.getIdUsuario2());
                startActivity(toContactoActivity);
            }
        });
    }

    public void setContactos(List<Contactos> contactos){
        this.contactos = contactos;
    }

    public void setAdapterContactos(){
        AdapterContacto adapter = new AdapterContacto(ContactosActivity.this, contactos);
        Log.i("Request", "Set Adapter");
        lista.setAdapter(adapter);
    }

    private void requestApiGetContacts(Integer idUsuario){

        //limpiar listado de contactos
        if (contactos != null){
            contactos.clear();
        }

        new AsyncTaskContactos(this).execute(idUsuario);

        /*OkHttpClient cliente = new OkHttpClient();

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
                    ResponseContacto responseContactos = gson.fromJson(respuesta, ResponseContacto.class);

                    Log.i("Request", String.valueOf(responseContactos));
                    contactos = responseContactos.getContactos();

                    AdapterContacto adapter = new AdapterContacto(ContactosActivity.this, contactos);
                    Log.i("Request", "Set Adapter");
                    lista.setAdapter(adapter);
                }
            }
        });*/
    }
}
