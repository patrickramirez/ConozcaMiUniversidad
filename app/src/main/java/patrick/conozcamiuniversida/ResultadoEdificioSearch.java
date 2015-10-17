package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_edificio_search);

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

                    options.title("Resultado: ");
                    options.snippet(cursor.getColumnName(cursor.getColumnIndex("direccion")).toString());

                    gm.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    options.position(new LatLng(Double.parseDouble(cursor.getColumnName(cursor.getColumnIndex("latitud")).toString()), Double
                            .parseDouble(cursor.getColumnName(cursor.getColumnIndex("longitud")).toString())));
                    gm.addMarker(options);

                    LatLng actual = new LatLng(Double.parseDouble(cursor.getColumnName(cursor.getColumnIndex("latitud")).toString()),
                            Double.parseDouble(cursor.getColumnName(cursor.getColumnIndex("longitud")).toString()));
                    CameraPosition posicion = new CameraPosition.Builder().target(actual).zoom(14).build();
                    CameraUpdate update = CameraUpdateFactory.newCameraPosition(posicion);
                    gm.animateCamera(update);


                }


            }
        }


    }
}
