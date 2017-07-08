package com.example.dcaceres.project;

import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TabHost;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;
import model.Contacto;
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

    private TabHost tabHost;
    private EditText nombres;
    private EditText telefono;
    private EditText correo;
    private EditText ocupacion;
    private EditText ciudad;
    private EditText descrip;
    private EditText direccion;
    private EditText lugar;
    private EditText fecha;
    private EditText hora;

    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        nombres   = (EditText)findViewById(R.id.contactoET_nombre);
        telefono  = (EditText) findViewById(R.id.contactoET_telefono);
        correo    = (EditText) findViewById(R.id.contactoET_correo);
        ocupacion = (EditText) findViewById(R.id.contactoET_ocupación);
        ciudad    = (EditText) findViewById(R.id.contactoET_ciudad);
        descrip   = (EditText) findViewById(R.id.contactoET_descrip);
        direccion = (EditText) findViewById(R.id.contactoET_direccion);
        lugar     = (EditText) findViewById(R.id.contactoET_lugar);
        fecha     = (EditText) findViewById(R.id.contactoET_fecha);
        hora      = (EditText) findViewById(R.id.contactoET_hora);

        setTabs();
//        setLocation();

        Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        Integer idContacto = getIntent().getIntExtra("idContacto", 0);
        Character action = getIntent().getCharExtra("action", ACTION_CREATE);

        if (action == ACTION_CREATE) {

            new AsyncTaskAPIGet(this, API_RESOURCE_USUARIOS).execute(idContacto);

        }else if (action == ACTION_READ) {

            new AsyncTaskAPIGet(this, API_RESOURCE_CONTACTOS).execute(idUsuario, idContacto);
        }

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

    public void setData(List<Contacto> contactos, Character action){

        //obtener el primer contacto... es solo 1
        if (contactos == null){
            return;
        }

        Contacto contacto = contactos.get(0);
        Log.i("Request", "ID: " + contacto.getIdUsuario());
        Log.i("Request", "Nombre: " + contacto.getPrimerNombre());
        Log.i("Request", "Apellido: " +  contacto.getPrimerApellido());

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
