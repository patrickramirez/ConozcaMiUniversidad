package patrick.conozcamiuniversida;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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
import DAO.FacultadDAO;
import DAO.MysqlDAO;

public class ResultadoFacultadSearch extends FragmentActivity {

    private GoogleMap gm;
    FacultadDAO _facultadDAO;
    EdificioDAO _edificioDAO;
    SharedPreferences prefs;
    String longitud, latitud;
    TextView txtDescripcionMapaFacultad, txtestadoFacultad, txtdireccionFacultad;
    MysqlDAO _mysqlDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_facultad_search);

        txtDescripcionMapaFacultad = (TextView) findViewById(R.id.txtDescripcionMapaFacultad);
        txtestadoFacultad = (TextView) findViewById(R.id.txtestadoFacultad);
        txtdireccionFacultad = (TextView) findViewById(R.id.txtdireccionFacultad);
        _edificioDAO = EdificioDAO.getInstance(this);
        _facultadDAO = FacultadDAO.getInstance(this);
        _mysqlDAO = MysqlDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        FragmentManager fmanager = getSupportFragmentManager();
        Fragment fragment = fmanager.findFragmentById(R.id.map);
        SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;

        MarkerOptions options = new MarkerOptions();

        gm = supportmapfragment.getMap();
        String NombreEdificioSeleccionado = prefs.getString("NombreFacultadSeleccionado", "");

        if (NombreEdificioSeleccionado != null) {
            if (null != gm) {
                gm.setMyLocationEnabled(true);


                Cursor cursor = _facultadDAO.CursorGetDatosFacultad(NombreEdificioSeleccionado);
                txtestadoFacultad.setText(_mysqlDAO.getEstadoFACULTADMysql(NombreEdificioSeleccionado));

                if (cursor.moveToFirst()) {

                    String idEdificio = cursor.getString(cursor.getColumnIndex("idedificio"));

                    Cursor c = _edificioDAO.CursorGetDatosEdificiobyID(idEdificio);

                    if (c.moveToFirst()) {
                        String direccion = c.getString(c.getColumnIndex("direccion"));
                        longitud = c.getString(c.getColumnIndex("longitud"));
                        latitud = c.getString(c.getColumnIndex("latitud"));
                        txtdireccionFacultad.setText(direccion);
                    }

                    txtDescripcionMapaFacultad.setText("Facultad " + cursor.getString(cursor.getColumnIndex("nombreFacultad")));



                    options.title("Resultado: ");
                    //options.snippet(direccion);


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

            startActivity(new Intent(this, FacultadSearch.class));
            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void btnSendGoogleMaps(View v) {


        String label = "Resultado Facultad";
        String uriBegin = "geo:" + latitud + "," + longitud;
        String query = latitud + "," + longitud + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);

//        Uri gmmIntentUri = Uri.parse("geo:" + latitud + "," + longitud);
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);

    }
}
