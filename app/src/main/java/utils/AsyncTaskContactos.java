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
import java.util.List;

import model.Contacto;
import model.Contactos;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by doris on 3/07/2017.
 */

public class AsyncTaskContactos extends AsyncTask<Integer, Void, List<Contacto>> {

    private List<Contacto> contactos;
    private AppCompatActivity activity;

    public AsyncTaskContactos(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    protected List<Contacto> doInBackground(Integer... params) {

        OkHttpClient cliente = new OkHttpClient();
        Gson gson = new Gson();

        String url = "http://192.168.1.69/api.phpbackend.com/v1/contactos/" + params[0];

        if (activity instanceof ContactoActivity){
            url = url + "," + params[1];
        }

        //armar solicitud de API
        Request request = new Request.Builder().url(url)
                .get()
                .build();

        try {

            Response response = cliente.newCall(request).execute();
            ResponseContactos responseContactos = gson.fromJson( response.body().string(), ResponseContactos.class);
            return responseContactos.getContactos();

        } catch (Exception e){
            Log.i("Request", "Excepción " + e.getMessage());
            e.printStackTrace();
        }
        return  null;
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
