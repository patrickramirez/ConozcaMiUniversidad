package patrick.conozcamiuniversida;

import android.app.Activity;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import DAO.EdificioDAO;
import DAO.MysqlDAO;
import DAO.SitioDAO;
import Utils.Utils;

public class ResultadoSala extends FragmentActivity {
    private GoogleMap gm;
    EdificioDAO _edificioDAO;
    SitioDAO _sitioDAO;
    SharedPreferences prefs;
    String longitud, latitud;
    TextView txtDescripcionMapaArea, txtestadoArea, txtdireccionArea;
    MysqlDAO _mysqlDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_resultado_sala);
        txtDescripcionMapaArea = (TextView) findViewById(R.id.txtDescripcionMapaArea);
        txtestadoArea = (TextView) findViewById(R.id.txtestadoArea);
        txtdireccionArea = (TextView) findViewById(R.id.txtdireccionArea);

        _sitioDAO = SitioDAO.getInstance(this);
        _edificioDAO = EdificioDAO.getInstance(this);
        _mysqlDAO = MysqlDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        FragmentManager fmanager = getSupportFragmentManager();
        Fragment fragment = fmanager.findFragmentById(R.id.map);
        SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;

        if (Utils.isOnline(this)) {
            try {
                MarkerOptions options = new MarkerOptions();

                gm = supportmapfragment.getMap();
                String EdificioArea = prefs.getString("NombreSalaLaboratorioSelect", "");
                String tipoSeleccionado = prefs.getString("NombreOficinaSalaSelect", "");

                if (EdificioArea != null) {
                    if (null != gm) {
                        gm.setMyLocationEnabled(true);


                        Cursor cursor = _sitioDAO.getDatos3(tipoSeleccionado, EdificioArea);

                        txtestadoArea.setText(_mysqlDAO.getDatos3(tipoSeleccionado, EdificioArea));

                        if (cursor.moveToFirst()) {

                            String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                            longitud = cursor.getString(cursor.getColumnIndex("longitud"));
                            latitud = cursor.getString(cursor.getColumnIndex("latitud"));
                            txtDescripcionMapaArea.setText("Edificio " + cursor.getString(cursor.getColumnIndex("nombreEdificio")));

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
            } catch (Exception e) {
                Toast.makeText(this, "Mapa no disponible, intente nuevamente", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Problemas de conexion a internet, verifique e intente nuevamente", Toast.LENGTH_LONG).show();
        }

    }

    public void btnSendGoogleMaps(View v) {
        String label = "Resultado Edificios";
        String uriBegin = "geo:" + latitud + "," + longitud;
        String query = latitud + "," + longitud + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void btnEstoyAqui(View v) {
        startActivity(new Intent(this, ResultadoMapaSala.class));
        finish();
    }

    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botï¿½n al pulsar ir a atrï¿½s

            startActivity(new Intent(this, Sala.class));
            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
