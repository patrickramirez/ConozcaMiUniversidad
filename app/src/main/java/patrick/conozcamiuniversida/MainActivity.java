package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import DAO.CampusDAO;
import DAO.SedeDAO;
import DAO.UniversidadDAO;
import MOD.CampusMOD;
import MOD.SedeMOD;
import MOD.UniversidadMOD;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    UniversidadDAO _uniDAO;
    SedeDAO _sedeDAO;
    CampusDAO _campusDAO;
    Spinner spinneruniversidad, spinnersede, spinnercampus;

    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;

    String nombreUniverisdad, nombreSede, nombreCampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinneruniversidad = (Spinner) findViewById(R.id.spinnerUniversidad);
        spinnersede = (Spinner) findViewById(R.id.spinnerSede);
        spinnercampus = (Spinner) findViewById(R.id.spinnerCampus);

        _uniDAO = UniversidadDAO.getInstance(this);
        _sedeDAO = SedeDAO.getInstance(this);
        _campusDAO = CampusDAO.getInstance(this);


    /*   _uniDAO.Insertar(0, "Universidad Andres Bello", "republica 237", "", "", "disponible");
        _uniDAO.Insertar(1, "Universidad Diego Portales", "ejercito 100", "", "", "disponible");
        _sedeDAO.Insertar(0, "Santiago", "republica 237", "", "", "disponible", 0);
        _sedeDAO.Insertar(1, "Viña", "quillota 980", "", "", "disponible", 0);
        _sedeDAO.Insertar(2, "Santiago", "ejercito 100", "", "", "disponible", 1);
        _campusDAO.Insertar(0, "Campus Antonio Varas", "antonio varas 100", "", "", "disponible", 0);
        _campusDAO.Insertar(1, "Campus republica", "republica 100", "", "", "disponible", 0);

        */


        loadSpinnerUniversidad();

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

    }

    public void BtnEntrar(View v) {

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Bienvenida", "SI");
        editor.commit();
        startActivity(new Intent(this, MenuPrincipal.class));
        finish();


    }

    private void loadSpinnerUniversidad() {

        List<UniversidadMOD> list = _uniDAO.getUniversidadList();
        items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getNombreUniversidad();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);
        spinneruniversidad.setAdapter(adapter);
        spinneruniversidad.setOnItemSelectedListener(this);

    }


    public void cargarspinnerSede(String nombre) {

        List<SedeMOD> list = _sedeDAO.getListsedes(nombre);
        items = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getNombreSede();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);

        spinnersede.setAdapter(adapter);
        spinnersede.setOnItemSelectedListener(this);
    }

    public void cargarspinnerCampus(String nombre, String iduniversidad) {

        List<CampusMOD> list = _campusDAO.getListCampusbynombreSede(nombre, iduniversidad);
        items = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getNombreCampus();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);

        spinnercampus.setAdapter(adapter);
        spinnercampus.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerUniversidad:
                nombreUniverisdad = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("Universidad", nombreUniverisdad);
                editor.commit();

                cargarspinnerSede(nombreUniverisdad);
                break;
            case R.id.spinnerSede:
                nombreSede = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("sede", nombreSede);
                editor.commit();
                cargarspinnerCampus(nombreSede, nombreUniverisdad);
                break;

            case R.id.spinnerCampus:
                nombreCampus = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("campus", nombreCampus);
                editor.commit();
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
