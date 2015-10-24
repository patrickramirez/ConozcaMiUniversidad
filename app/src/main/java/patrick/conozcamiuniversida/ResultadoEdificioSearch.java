package patrick.conozcamiuniversida;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import DAO.EdificioDAO;

public class ResultadoEdificioSearch extends FragmentActivity {
    private GoogleMap gm;
    EdificioDAO _edificioDAO;
    SharedPreferences prefs;
    String longitud, latitud;
    TextView txtDescripcionMapa, txtestadoEdificio, txtdireccionEdificio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_edificio_search);

        txtDescripcionMapa = (TextView) findViewById(R.id.txtDescripcionMapa);
        txtestadoEdificio = (TextView) findViewById(R.id.txtestadoFacultad);
        txtdireccionEdificio = (TextView) findViewById(R.id.txtdireccionEdificio);

        _edificioDAO = EdificioDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        FragmentManager fmanager = getSupportFragmentManager();
        Fragment fragment = fmanager.findFragmentById(R.id.map);
        SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;

        MarkerOptions options = new MarkerOptions();

        gm = supportmapfragment.getMap();
        String NombreEdificioSeleccionado = prefs.getString("NombreEdificioSeleccionado", "");

        if (NombreEdificioSeleccionado != null) {
            if (null != gm) {
                gm.setMyLocationEnabled(true);


                Cursor cursor = _edificioDAO.CursorGetDatosEdificio(NombreEdificioSeleccionado);

                if (cursor.moveToFirst()) {

                    String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                    longitud = cursor.getString(cursor.getColumnIndex("longitud"));
                    latitud = cursor.getString(cursor.getColumnIndex("latitud"));
                    txtDescripcionMapa.setText("Edificio " + cursor.getString(cursor.getColumnIndex("nombreEdificio")));
                    txtestadoEdificio.setText(cursor.getString(cursor.getColumnIndex("estado")));
                    txtdireccionEdificio.setText(direccion);

                    options.title("Resultado: ");
                    options.snippet(direccion);


                    gm.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    options.position(new LatLng(Double.parseDouble(latitud), Double
                            .parseDouble(longitud)));
                    gm.addMarker(options);

                    LatLng actual = new LatLng(Double.parseDouble(latitud),
                            Double.parseDouble(longitud));
                    CameraPosition posicion = new CameraPosition.Builder().target(actual).zoom(15).build();
                    CameraUpdate update = CameraUpdateFactory.newCameraPosition(posicion);
                    gm.animateCamera(update);


                }


            }
        }


    }

    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botï¿½n al pulsar ir a atrï¿½s

            startActivity(new Intent(this, EdificioSearch.class));
            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void btnSendGoogleMaps(View v) {


        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitud + "," + longitud);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
}
