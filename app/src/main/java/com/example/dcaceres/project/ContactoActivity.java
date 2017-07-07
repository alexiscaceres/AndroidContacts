package com.example.dcaceres.project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import model.Contacto;
import utils.AsyncTaskContactos;

public class ContactoActivity extends AppCompatActivity //implements OnMapReadyCallback,
//                                                                   GoogleApiClient.ConnectionCallbacks,
//                                                                   GoogleApiClient.OnConnectionFailedListener
{
    private final Character ACTION_READ = 'R';
    private final Character ACTION_CREATE = 'C';
    private static final int REQUEST_LOCATION = 1;

    private TabHost tabHost;

    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        setTabs();
//        setLocation();

        Integer idUsuario = getIntent().getIntExtra("idUsuario", 0);
        Integer idContacto = getIntent().getIntExtra("idContacto", 0);
        Character action = getIntent().getCharExtra("action", ACTION_CREATE);

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
        Contacto contacto = contactos.get(0);

        EditText nombres = (EditText)findViewById(R.id.contactoET_nombre);
        EditText telefono = (EditText) findViewById(R.id.contactoET_telefono);
        EditText correo = (EditText) findViewById(R.id.contactoET_correo);
        EditText ocupacion = (EditText) findViewById(R.id.contactoET_ocupación);
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
