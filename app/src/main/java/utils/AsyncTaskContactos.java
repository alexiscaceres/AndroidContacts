package utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dcaceres.project.ContactosActivity;
import com.google.gson.Gson;

import java.util.List;

import model.Contactos;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by doris on 3/07/2017.
 */

public class AsyncTaskContactos extends AsyncTask<Integer, Void, List<Contactos>> {

    private List<Contactos> contactos;
    private ContactosActivity activity;

    public AsyncTaskContactos(ContactosActivity activity){
        this.activity = activity;
    }


    @Override
    protected List<Contactos> doInBackground(Integer... params) {

        OkHttpClient cliente = new OkHttpClient();
        Gson gson = new Gson();

        Log.i("Request", String.valueOf(params[0]));
        //armar solicitud de API
        Request request = new Request.Builder().url("http://192.168.1.66/api.phpbackend.com/v1/contactos/"+ params[0])
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
    protected void onPostExecute(List<Contactos> contactos) {

        super.onPostExecute(contactos);
        Log.i("Request", "aqui llega");
        Log.i("Request", String.valueOf(contactos));
        activity.setContactos(contactos);

        if (contactos != null){
            activity.setAdapterContactos();
        }

    }
}
