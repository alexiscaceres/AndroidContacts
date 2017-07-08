package utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dcaceres.project.ContactoActivity;
import com.example.dcaceres.project.ContactosActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.Contacto;
import model.Contactos;
import model.Usuario;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by doris on 3/07/2017.
 */

public class AsyncTaskAPIGet extends AsyncTask<Integer, Void, List<Contacto>> {

    private static Character ACTION_READ = 'R';
    private static Character ACTION_CREATE = 'C';
    private final String API_RESOURCE_USUARIOS = "usuarios";
    private final String API_RESOURCE_CONTACTOS = "contactos";

    private AppCompatActivity activity;
    private String resource;

    public AsyncTaskAPIGet(AppCompatActivity activity, String resource){
        this.activity = activity;
        this.resource = resource;
    }

    @Override
    protected List<Contacto> doInBackground(Integer... params) {

        OkHttpClient cliente = new OkHttpClient();
        List<Contacto> contactos = new ArrayList<Contacto>();
        Gson gson = new Gson();

        //String url = "http://192.168.1.69/api.phpbackend.com/v1/contactos/" + params[0];
        String url = "http://192.168.1.69/api.phpbackend.com/v1/"+ resource + "/" + params[0];

        if (activity instanceof ContactoActivity){

            try{

                url = url + "," + params[1];

            } catch (Exception exception){
            }

        }

        //armar solicitud de API
        Request request = new Request.Builder().url(url)
                .get()
                .build();

        try {

            Response response = cliente.newCall(request).execute();

            switch (resource){
                case API_RESOURCE_CONTACTOS:
                    ResponseContactos responseContactos = gson.fromJson( response.body().string(), ResponseContactos.class);
                    contactos = responseContactos.getContactos();
                    break;

                case  API_RESOURCE_USUARIOS:
                    ResponseUsuario responseUsuario = gson.fromJson( response.body().string(), ResponseUsuario.class);
                    Contacto contacto = new Contacto( responseUsuario.getUsuario() );
                    contactos.add(contacto);
                    break;
            }

        } catch (Exception e){
            Log.i("Request", "Excepción " + e.getMessage());
            e.printStackTrace();
        }

        return contactos;
    }

    @Override
    protected void onPostExecute(List<Contacto> contactos) {
        super.onPostExecute(contactos);

        if (activity instanceof ContactosActivity){
            ((ContactosActivity) activity).setContactos(contactos);
            ((ContactosActivity) activity).setAdapterContactos();
        }
        if (activity instanceof ContactoActivity){
            ((ContactoActivity) activity).setData(contactos);
        }
    }
}
