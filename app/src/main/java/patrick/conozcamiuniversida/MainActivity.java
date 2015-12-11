package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        spinneruniversidad = (Spinner) findViewById(R.id.spinnerUniversidad);
        spinnersede = (Spinner) findViewById(R.id.spinnerSede);
        spinnercampus = (Spinner) findViewById(R.id.spinnerCampus);

        _uniDAO = UniversidadDAO.getInstance(this);
        _sedeDAO = SedeDAO.getInstance(this);
        _campusDAO = CampusDAO.getInstance(this);

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

        if(list.size()>0){
            items = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {

                items[i] = list.get(i).getNombreUniversidad();
            }

            adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);
            spinneruniversidad.setAdapter(adapter);
            spinneruniversidad.setOnItemSelectedListener(this);
        }else{
            Toast.makeText(this, "No existen datos asociados al item", Toast.LENGTH_LONG).show();
        }


    }


    public void cargarspinnerSede(String nombre) {

        List<SedeMOD> list = _sedeDAO.getListsedes(nombre);


        if(list.size()>0){
            items = new String[list.size()];

            for (int i = 0; i < list.size(); i++) {

                items[i] = list.get(i).getNombreSede();
            }

            adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);

            spinnersede.setAdapter(adapter);
            spinnersede.setOnItemSelectedListener(this);
        }else{
            Toast.makeText(this, "No existen datos asociados al item", Toast.LENGTH_LONG).show();
        }

    }

    public void cargarspinnerCampus(String nombre, String iduniversidad) {

        List<CampusMOD> list = _campusDAO.getListCampusbynombreSede(nombre, iduniversidad);

        if(list.size()>0){
            items = new String[list.size()];

            for (int i = 0; i < list.size(); i++) {

                items[i] = list.get(i).getNombreCampus();
            }

            adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);

            spinnercampus.setAdapter(adapter);
            spinnercampus.setOnItemSelectedListener(this);
        }else{
            Toast.makeText(this, "No existen datos asociados al item", Toast.LENGTH_LONG).show();
        }

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

    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botï¿½n al pulsar ir a atrï¿½s

            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME && event.getRepeatCount() == 0) {


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
