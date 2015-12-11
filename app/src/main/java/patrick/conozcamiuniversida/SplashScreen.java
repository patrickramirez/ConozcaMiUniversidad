package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import DAO.CampusDAO;
import DAO.EdificioDAO;
import DAO.FacultadDAO;
import DAO.SedeDAO;
import DAO.SitioDAO;
import DAO.UniversidadDAO;
import Utils.LoadingTask;
import Utils.Utils;

public class SplashScreen extends Activity implements LoadingTask.LoadingTaskFinishedListener {

    SharedPreferences prefs;
    UniversidadDAO _uniDAO;
    SedeDAO _sedeDAO;
    CampusDAO _campusDAO;
    EdificioDAO _edificioDAO;
    FacultadDAO _facultadDAO;
    SitioDAO _sitioDAO;

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
        _sitioDAO = SitioDAO.getInstance(this);

//

        if(Utils.isOnline(this)){
            try{
                //Insertar datos en universidad
//        _uniDAO.Insertar(0, "Universidad Andrés Bello", "República 237, Santiago Centro", "", "", "disponible");
//
////Insertar datos en Sede
//        _sedeDAO.Insertar(0, "Santiago", "República 237, Santiago Centro", "-33.451512", "-70.66785219999990", " ", 0);
//        _sedeDAO.Insertar(1, "Viña del mar", "Quillota 980, Viña del Mar", "-33.01422489999990", "-71.54341039999990", " ", 0);
//        _sedeDAO.Insertar(2, "Concepción", "Autopista Concepción - Talcahuano 7100, Concepción", "-36.7797197", "-73.07603399999990", " ", 0);
//
////Insertar datos en Campus
//        _campusDAO.Insertar(0, "Los Leones", "Los Leones 745, Providencia", "-33.4265323", "-70.60381280000000", "disponible", 0);
//        _campusDAO.Insertar(1, "Casona", "Fernándes Concha 700 Las Condes", "-33.3748966", "-70.5053787", "disponible", 0);
//        _campusDAO.Insertar(2, "Bellavista", "Bellavista 0121, Santiago", "-33.4345369", "-70.63376979999990", "disponible", 0);
//        _campusDAO.Insertar(3, "Antonio Varas", "Antonio Varas 880, Providencia", "-33.4353601", "-70.61473699999990", "disponible", 0);
//        _campusDAO.Insertar(4, "República", "República 237, Santiago Centro", "-33.451512", "-70.66785219999990", "disponible", 0);
//        _campusDAO.Insertar(5, "Reñaca", "Halimeda esquina Talasia, Viña del Mar", "-32.9771977", "-71.5420186", "disponible", 1);
//        _campusDAO.Insertar(6, "Miraflores", "Valparaiso 1560, Viña del Mar", "-33.0371495", "-71.58134369999990", "disponible", 1);
//        _campusDAO.Insertar(7, "Nuevo Viña del Mar", "7 Norte 1348, Viña del Mar", "-33.0173805", "-71.54126969999990", "disponible", 1);
//        _campusDAO.Insertar(8, "Concepción", "Autopista Concepción-Talcahuano 7100, Concepción", "-36.7797197", "-73.07603399999990", "disponible", 2);
//
//
////Insertar datos en Edificio
//        _edificioDAO.Insertar(0, "A1", "Antonio varas 807, Providencia", "-33.43461680000000", "-70.61519980000000", "disponible", 3);
//        _edificioDAO.Insertar(1, "A2", "Antonio varas 880, Providencia", "-33.4353601", "-70.61473699999990", "disponible", 3);
//        _edificioDAO.Insertar(3, "R1", "República 237, Santiago", "-33.451512", "-71.58134369999990", "disponible", 4);
//
////Insertar datos en Facultad
//        _facultadDAO.Insertar(0, "Ingenieria", "disponible", 3, 0);
//        _facultadDAO.Insertar(1, "Medicina", "disponible", 4, 3);
//
////Insertar datos en Sitio
//        _sitioDAO.Insertar(0, "Baño", "Baño A1", "Baños a lado derecho de escalera principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(2, "Enfermeria", "Enfermeria A2", "Enfermeria principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(3, "Oficina", "Cobranza", "A un costado de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(4, "Decanato", "Decano de Ingenieria", "Cuarto piso a un costado derecho", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(5, "Federación", "Federación Ingenieria", "Piso -1", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(6, "Oficina", "Secretario Académico", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(7, "Sala", "Sala 001", "Al costado derecho de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(8, "Sala", "Sala 002", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(9, "Sala", "Sala 403", "Cuarto piso", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(10, "Sala", "Sala 007", "Piso -1 bajando escaleras", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(11, "Sala", "Sala 202", "A un costado derecho de las escaleras principales", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(12, "Laboratorio", "Lab 001", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(13, "Laboratorio", "Lab 006", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
//        _sitioDAO.Insertar(14, "Laboratorio", "Lab 001", "Bajando a piso -1", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(15, "Casino", "Casino", "Cruzando edificio", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
//        _sitioDAO.Insertar(16, "Laboratorio", "Lab 08", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);


                //Insertar datos en universidad
                _uniDAO.Insertar(0, "Universidad Andres Bello", "Republica 237, Santiago Centro", "", "", "disponible");
                _uniDAO.Insertar(1,"Universidad de prueba 1", "argentina","","","");

//Insertar datos en Sede
                _sedeDAO.Insertar(0, "Santiago", "Republica 237, Santiago Centro", "-33.451512", "-70.66785219999990", " ", 0);
                _sedeDAO.Insertar(1, "Viña del mar", "Quillota 980, Viña del Mar", "-33.01422489999990", "-71.54341039999990", " ", 0);
                _sedeDAO.Insertar(2, "Concepción", "Autopista Concepción - Talcahuano 7100, Concepción", "-36.7797197", "-73.07603399999990", " ", 0);
                _sedeDAO.Insertar(3, "Sede Prueba", "prueba","-33.549228","-70.3848585","",1);

//Insertar datos en Campus
                _campusDAO.Insertar(0, "Los Leones", "Los Leones 745, Providencia", "-33.4265323", "-70.60381280000000", "disponible", 0);
                _campusDAO.Insertar(1, "Casona", "Fernándes Concha 700 Las Condes", "-33.3748966", "-70.5053787", "disponible", 0);
                _campusDAO.Insertar(2, "Bellavista", "Bellavista 0121, Santiago", "-33.4345369", "-70.63376979999990", "disponible", 0);
                _campusDAO.Insertar(3, "Antonio Varas", "Antonio Varas 880, Providencia", "-33.4353601", "-70.61473699999990", "disponible", 0);
                _campusDAO.Insertar(4, "República", "República 237, Santiago Centro", "-33.451512", "-70.66785219999990", "disponible", 0);
                _campusDAO.Insertar(5, "Reñaca", "Halimeda esquina Talasia, Viña del Mar", "-32.9771977", "-71.5420186", "disponible", 1);
                _campusDAO.Insertar(6, "Miraflores", "Valparaiso 1560, Viña del Mar", "-33.0371495", "-71.58134369999990", "disponible", 1);
                _campusDAO.Insertar(7, "Nuevo Viña del Mar", "7 Norte 1348, Viña del Mar", "-33.0173805", "-71.54126969999990", "disponible", 1);
                _campusDAO.Insertar(8, "Concepción", "Autopista Concepción-Talcahuano 7100, Concepción", "-36.7797197", "-73.07603399999990", "disponible", 2);

                _campusDAO.Insertar(9, "Campus Prueba", "prueba", "-33-3949585","-70.38475734"," ",3);


//Insertar datos en Edificio
                _edificioDAO.Insertar(0, "A1", "Antonio varas 807, Providencia", "-33.43461680000000", "-70.61519980000000", "disponible", 3);
                _edificioDAO.Insertar(1, "A2", "Antonio varas 880, Providencia", "-33.4353601", "-70.61473699999990", "disponible", 3);
                _edificioDAO.Insertar(3, "R1", "República 237, Santiago", "33.451512", "-71.58134369999990", "disponible", 4);

                _edificioDAO.Insertar(4,"Prueba","prueba1","-33.4959686","-71.3985758","",9);


//Insertar datos en Facultad
                _facultadDAO.Insertar(0, "Ingenieria", "disponible", 3, 0);
                _facultadDAO.Insertar(1, "Medicina", "disponible", 4, 3);

//Insertar datos en Sitio
                _sitioDAO.Insertar(0, "Baño", "Baño A1", "Baños a lado derecho de escalera principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(2, "Enfermeria", "Enfermeria A2", "Enfermeria principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(3, "Oficina", "Cobranza", "A un costado de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(4, "Decanato", "Decano de Ingenieria", "Cuarto piso a un costado derecho", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(5, "Federación", "Federación Ingenieria", "Piso -1", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(6, "Oficina", "Secretario Académico", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(7, "Sala", "Sala 001", "Al costado derecho de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(8, "Sala", "Sala 002", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(9, "Sala", "Sala 403", "Cuarto piso", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(10, "Sala", "Sala 007", "Piso -1 bajando escaleras", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(11, "Sala", "Sala 202", "A un costado derecho de las escaleras principales", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(12, "Laboratorio", "Lab 001", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(13, "Laboratorio", "Lab 006", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 0);
                _sitioDAO.Insertar(14, "Laboratorio", "Lab 001", "Bajando a piso -1", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(15, "Casino", "Casino", "Cruzando edificio", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);
                _sitioDAO.Insertar(16, "Laboratorio", "Lab 08", "Al costado izquierdo de la entrada principal", "http://s8.postimg.org/ve200vnlx/gimnasioa2.png", "disponible", 1);

            }catch (Exception e){
//            Toast.makeText(this,"",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Problemas de conexion a internet, verifique e intente nuevamente",Toast.LENGTH_LONG).show();
        }
//
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
//        startService(new Intent(this, GpsService.class));

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
