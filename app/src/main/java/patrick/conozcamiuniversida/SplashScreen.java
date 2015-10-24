package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import DAO.CampusDAO;
import DAO.EdificioDAO;
import DAO.FacultadDAO;
import DAO.SedeDAO;
import DAO.UniversidadDAO;
import Utils.LoadingTask;
import patrick.conozcamiuniversida.R;

public class SplashScreen extends Activity implements LoadingTask.LoadingTaskFinishedListener {

    SharedPreferences prefs;
    UniversidadDAO _uniDAO;
    SedeDAO _sedeDAO;
    CampusDAO _campusDAO;
    EdificioDAO _edificioDAO;
    FacultadDAO _facultadDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ProgressBar barra = (ProgressBar) findViewById(R.id.progressBar1);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        _uniDAO = UniversidadDAO.getInstance(this);
        _sedeDAO = SedeDAO.getInstance(this);
        _campusDAO = CampusDAO.getInstance(this);
        _edificioDAO = EdificioDAO.getInstance(this);
        _facultadDAO = FacultadDAO.getInstance(this);
/*

        _uniDAO.Insertar(0, "Universidad Andres Bello", "republica 237", "", "", "disponible");---
        _uniDAO.Insertar(1, "Universidad Diego Portales", "ejercito 100", "", "", "disponible");

        _sedeDAO.Insertar(0, "Santiago", "republica 237", "", "", "disponible", 0);--
        _sedeDAO.Insertar(1, "Viña", "quillota 980", "", "", "disponible", 0);
        _sedeDAO.Insertar(2, "Santiago", "ejercito 100", "", "", "disponible", 1);

        _campusDAO.Insertar(0, "Campus Antonio Varas", "antonio varas 807", "", "", "disponible", 0);--
        _campusDAO.Insertar(1, "Campus republica", "republica 100", "", "", "disponible", 0);


        _edificioDAO.Insertar(0, "A1", "antonio varas 807", "-33.4346167", "-70.6151994", "disponible", 0);
        _edificioDAO.Insertar(1, "A2", "antonio varas 880", "-33.4353601", "-70.61473699999999", "disponible", 0);
        _edificioDAO.Insertar(2, "R1", "republica 330", "-33.6628139", "-70.93269450000003", "disponible", 1);


        _facultadDAO.Insertar(0, "Ingenieria", "disponible", 0, 0);
        _facultadDAO.Insertar(1, "Construccion", "disponible", 0, 1);

        _facultadDAO.Insertar(2, "Medicina", "disponible", 0, 2);

*/
        new LoadingTask(barra, SplashScreen.this).execute();
    }


    @Override
    public void onTaskFinished() {
        // TODO Auto-generated method stub
        completeSplash();
    }

    private void completeSplash() {
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {


        String Bienvenida = prefs.getString("Bienvenida", "");
        if (Bienvenida.equalsIgnoreCase("SI")) {
            startActivity(new Intent(SplashScreen.this, MenuPrincipal.class));
            finish();
        } else {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }

    }
}
