package patrick.conozcamiuniversida;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import DAO.EdificioDAO;
import Utils.GPSTracker;
import Utils.MyLocationListener;

public class Explorar extends FragmentActivity {

    private GoogleMap map;
    private LatLng ltlng;
    double ltlng1;
    double ltlng2;
    SharedPreferences prefs;
    EdificioDAO _ediDAO;
    //    protected LocationManager locationManager;
//    protected LocationListener locationListener;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorar);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        _ediDAO = EdificioDAO.getInstance(this);

        gps = new GPSTracker(this);
        // check if GPS enabled
        if (gps.canGetLocation()) {

            ltlng1 = gps.getLatitude();
            ltlng2 = gps.getLongitude();


        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        InitialiseMap();


    }

    private void InitialiseMap() {
        // TODO Auto-generated method stub
        if (map == null) {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            if (map != null) {


            }

            map.setMyLocationEnabled(true);

            String NombreUniversidad = prefs.getString("Universidad", "");

            Cursor c = _ediDAO.getEdificioaExplorarbyUniversidad(NombreUniversidad);
            if (c.moveToFirst()) {
                do {

                    ltlng = new LatLng(c.getDouble(0), c.getDouble(1));
                    map.addMarker(new MarkerOptions()
                            .position(ltlng)
                            .title(c.getString(2))
                    );

                } while (c.moveToNext());
            }


            LatLng actual = new LatLng(-33.44515, -70.655479);

            CameraPosition posicion = new CameraPosition.Builder().target(actual).zoom(7).build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(posicion);
            map.animateCamera(update);

        }
    }


}
