package com.example.dcaceres.project;

import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;
import model.Contacto;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.AsyncTaskAPIGet;

public class ContactoActivity extends AppCompatActivity //implements OnMapReadyCallback,
//                                                                   GoogleApiClient.ConnectionCallbacks,
//                                                                   GoogleApiClient.OnConnectionFailedListener
{
    private static Character ACTION_READ = 'R';
    private static Character ACTION_CREATE = 'C';
    private final String API_RESOURCE_USUARIOS = "usuarios";
    private final String API_RESOURCE_CONTACTOS = "contactos";
//    private static final int REQUEST_LOCATION = 1;

    private TabHost etTabHost;
    private EditText etNombres;
    private EditText etTelefono;
    private EditText etCorreo;
    private EditText etOcupacion;
    private EditText etCiudad;
    private EditText etDescrip;
    private EditText etDireccion;
    private EditText etLugar;
    private EditText etFecha;
    private EditText etHora;
    private Button   btGuardar;

    private Character action;
    private Integer   idUsuario;
    private Integer   idContacto;

//    private GoogleMap map;
//    private GoogleApiClient googleApiClient;
//    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etNombres = (EditText)findViewById(R.id.contactoET_nombre);
        etTelefono = (EditText) findViewById(R.id.contactoET_telefono);
        etCorreo = (EditText) findViewById(R.id.contactoET_correo);
        etOcupacion = (EditText) findViewById(R.id.contactoET_ocupación);
        etCiudad = (EditText) findViewById(R.id.contactoET_ciudad);
        etDescrip = (EditText) findViewById(R.id.contactoET_descrip);
        etDireccion = (EditText) findViewById(R.id.contactoET_direccion);
        etLugar = (EditText) findViewById(R.id.contactoET_lugar);
        etFecha = (EditText) findViewById(R.id.contactoET_fecha);
        etHora = (EditText) findViewById(R.id.contactoET_hora);
        btGuardar = (Button) findViewById(R.id.contactoBT_guardar);

        setTabs();
        getData();
//        setLocation();


        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardarDatos();

            }
        });

    }

    private void setTabs(){

        etTabHost = (TabHost)findViewById(android.R.id.tabhost);
        etTabHost.setup();

        TabHost.TabSpec spec = etTabHost.newTabSpec("datosCont");
        spec.setContent(R.id.contactoTAB_datosCont);
        spec.setIndicator("", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        etTabHost.addTab(spec);

        spec = etTabHost.newTabSpec("datosRel");
        spec.setContent(R.id.contactoTAB_datosRel);
        spec.setIndicator("", ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_map));
        etTabHost.addTab(spec);

        etTabHost.setCurrentTab(0);
    }

    private void getData(){

        idUsuario = getIntent().getIntExtra("idUsuario", 0);
        idContacto = getIntent().getIntExtra("idContacto", 0);
        action = getIntent().getCharExtra("action", ACTION_CREATE);

        if (action == ACTION_CREATE) {

            new AsyncTaskAPIGet(this, API_RESOURCE_USUARIOS).execute(idContacto);

        }else if (action == ACTION_READ) {

            new AsyncTaskAPIGet(this, API_RESOURCE_CONTACTOS).execute(idUsuario, idContacto);
        }
    }

//    private void setLocation(){
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.contactoMV_lugar);
//
//        mapFragment.getMapAsync(this);
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .enableAutoManage(this, this)
//                .build();
//    }

    public void setData(List<Contacto> contactos){

        //obtener el primer contacto... es solo 1
        if (contactos == null){
            return;
        }

        Contacto contacto = contactos.get(0);

        etNombres.setText(contacto.getPrimerNombre() + " " + contacto.getPrimerApellido());
        etTelefono.setText(contacto.getTelefono());
        etCorreo.setText(contacto.getCorreo());
        etOcupacion.setText(contacto.getOcupacion());
        etCiudad.setText(contacto.getCiudad());
        //etDescrip.setText(contacto.getD);
        etDireccion.setText(contacto.getDireccion());
        etLugar.setText(contacto.getLugar());
        etFecha.setText(contacto.getFecha());
        etHora.setText(contacto.getHora());
    }

    private void guardarDatos(){

        if (action == ACTION_CREATE ){

            OkHttpClient cliente = new OkHttpClient();
            RequestBody jsonBody = new FormBody.Builder().add("fecha", etFecha.getText().toString())
                    .add("hora", etHora.getText().toString())
                    .add("lugar", etLugar.getText().toString())
                    .add("activa", Integer.toString(1))
                    .build();

            //armar solicitud de API
            Request request = new Request.Builder().url("http://192.168.1.69/api.phpbackend.com/v1/contactos/" + idUsuario + "," + idContacto)
                    .put(jsonBody)
                    .build();

            try {

                Response response = cliente.newCall(request).execute();

                if (response.isSuccessful()){
                    Toast.makeText(this, "Contacto creado exitosamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Error al crear el contacto", Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                Toast.makeText(this, "Error al crear el contacto", Toast.LENGTH_LONG).show();
            }
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        googleApiClient.connect();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        googleApiClient.disconnect();
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//        if ( ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED ) {
//
//        } else {
//            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//            if (lastLocation != null ){
//                double latitud = lastLocation.getLatitude();
//                double longitud = lastLocation.getLongitude();
//
//            }else{
//                Toast.makeText(this, "Ubicación no encontrada", Toast.LENGTH_LONG).show();
//            }
//        }
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        map = googleMap;
//
//        LatLng location = new LatLng(-34, 151);
//        map.addMarker(new MarkerOptions().position(location).title("Ubicación"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(location));
//
//    }


}
