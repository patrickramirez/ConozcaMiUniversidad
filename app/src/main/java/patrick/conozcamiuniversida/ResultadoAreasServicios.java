package patrick.conozcamiuniversida;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import DAO.EdificioDAO;
import DAO.SitioDAO;

public class ResultadoAreasServicios extends FragmentActivity {

    private GoogleMap gm;
    EdificioDAO _edificioDAO;
    SitioDAO _sitioDAO;
    SharedPreferences prefs;
    String longitud, latitud;
    TextView txtDescripcionMapaArea, txtestadoArea, txtdireccionArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_resultado_areas_servicios);

        txtDescripcionMapaArea = (TextView) findViewById(R.id.txtDescripcionMapaArea);
        txtestadoArea = (TextView) findViewById(R.id.txtestadoArea);
        txtdireccionArea = (TextView) findViewById(R.id.txtdireccionArea);

        _sitioDAO = SitioDAO.getInstance(this);
        _edificioDAO = EdificioDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        FragmentManager fmanager = getSupportFragmentManager();
        Fragment fragment = fmanager.findFragmentById(R.id.map);
        SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;

        MarkerOptions options = new MarkerOptions();

        gm = supportmapfragment.getMap();
        String EdificioArea = prefs.getString("NombreEdificioSeleccionadoEnArea", "");
        String tipoSeleccionado = prefs.getString("TipoSeleccionado", "");

        if (EdificioArea != null) {
            if (null != gm) {
                gm.setMyLocationEnabled(true);


                Cursor cursor = _edificioDAO.CursorGetDatosEdificio(EdificioArea);

                if (cursor.moveToFirst()) {

                    String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                    longitud = cursor.getString(cursor.getColumnIndex("longitud"));
                    latitud = cursor.getString(cursor.getColumnIndex("latitud"));
                    txtDescripcionMapaArea.setText("Edificio " + cursor.getString(cursor.getColumnIndex("nombreEdificio")));
                    txtestadoArea.setText(cursor.getString(cursor.getColumnIndex("estado")));
                    txtdireccionArea.setText("Dirigirse a: " + direccion);

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

    public void btnEstoyAqui(View v) {
        startActivity(new Intent(this, ResultadoMapaArea.class));
        finish();
    }
}
