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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import DAO.CampusDAO;
import DAO.EdificioDAO;
import DAO.MysqlDAO;
import Utils.Utils;

public class ResultadoCampusSearch extends FragmentActivity {

    private GoogleMap gm;
    CampusDAO _edificioDAO;
    SharedPreferences prefs;
    String longitud, latitud;
    TextView txtDescripcionMapa, txtestadoEdificio, txtdireccionEdificio;
    MysqlDAO _mysqlDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_campus_search);


        txtDescripcionMapa = (TextView) findViewById(R.id.txtDescripcionMapa);
        txtestadoEdificio = (TextView) findViewById(R.id.txtestadoFacultad);
        txtdireccionEdificio = (TextView) findViewById(R.id.txtdireccionEdificio);

        _edificioDAO = CampusDAO.getInstance(this);
        _mysqlDAO = MysqlDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        FragmentManager fmanager = getSupportFragmentManager();
        Fragment fragment = fmanager.findFragmentById(R.id.map);
        SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;

        if(Utils.isOnline(this)){
            try{
                MarkerOptions options = new MarkerOptions();

                gm = supportmapfragment.getMap();
                String NombreEdificioSeleccionado = prefs.getString("NombreSedeSearchSeleccionado", "");
                if (NombreEdificioSeleccionado != null) {
                    if (null != gm) {
                        gm.setMyLocationEnabled(true);

                        txtestadoEdificio.setText(_mysqlDAO.GetEstadoCampusMysql(NombreEdificioSeleccionado));
                        Cursor cursor = _edificioDAO.CursorGetDatosCampus(NombreEdificioSeleccionado);

                        if (cursor.moveToFirst()) {

                            String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                            longitud = cursor.getString(cursor.getColumnIndex("longitud"));
                            latitud = cursor.getString(cursor.getColumnIndex("latitud"));
                            txtDescripcionMapa.setText("Campus " + cursor.getString(cursor.getColumnIndex("nombrecampus")));
//                    txtestadoEdificio.setText(cursor.getString(cursor.getColumnIndex("estado")));
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

            }catch (Exception e){
                Toast.makeText(this,"Mapa no Disponible, intente nuevamente",Toast.LENGTH_LONG).show();
            }


        }else{
            Toast.makeText(this,"Problemas de conexion a internet, verifique e intente nuevamente",Toast.LENGTH_LONG).show();
        }



    }

    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botï¿½n al pulsar ir a atrï¿½s

            startActivity(new Intent(this, Campus.class));
            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void btnSendGoogleMaps(View v) {

//
//        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitud + "," + longitud);
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);


        String label = "Resultado Campus";
        String uriBegin = "geo:" + latitud + "," + longitud;
        String query = latitud + "," + longitud + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}

