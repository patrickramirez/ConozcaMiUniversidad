package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import DAO.EdificioDAO;
import DAO.SitioDAO;
import MOD.EdificioMOD;
import MOD.SitioMOD;

public class AreaServicio extends Activity implements AdapterView.OnItemSelectedListener {

    EdificioDAO _edificioDAO;
    SitioDAO _sitioDAO;
    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;
    Spinner spinnerEdificiosAreas1, spinnerAreaServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_area_servicio);

        _edificioDAO = EdificioDAO.getInstance(this);
        _sitioDAO = SitioDAO.getInstance(this);
        spinnerEdificiosAreas1 = (Spinner) findViewById(R.id.spinnerEdificioAreasServicios1);
        spinnerAreaServicios = (Spinner) findViewById(R.id.spinnerAreaServicios);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        try {
            LoadSpinnerAreas();
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }

    }

    public void btnBuscar(View v) {
        startActivity(new Intent(this, ResultadoAreasServicios.class));
        finish();
    }

    private void LoadSpinnerAreas() {
        String Nombre = prefs.getString("campus", "");
        List<SitioMOD> lista = _sitioDAO.getSitioList(Nombre);

        if(lista.size() >0 ) {
            items = new String[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                String tipo = lista.get(i).getTipoSitio();
                items[i] = tipo;
            }

            adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);

            spinnerAreaServicios.setAdapter(adapter);
            spinnerAreaServicios.setOnItemSelectedListener(this);
        }else{
            Toast.makeText(this, "No existen datos asociados al item", Toast.LENGTH_LONG).show();
        }

    }

    private void loadSpinnerEdificio() {
        String Nombre = prefs.getString("NombreTipoSitioSelect", "");

        List<EdificioMOD> list = _edificioDAO.getEdificioListbyTipoSitio(Nombre);

        if(list.size() >0) {
            items = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {

                items[i] = list.get(i).getNombreEdificio();
            }

            adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);

            spinnerEdificiosAreas1.setAdapter(adapter);
            spinnerEdificiosAreas1.setOnItemSelectedListener(this);
        }else{
            Toast.makeText(this, "No existen datos asociados al item", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerAreaServicios:
                String nombre = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("NombreTipoSitioSelect", nombre);
                editor.commit();

                loadSpinnerEdificio();
                break;

            case R.id.spinnerEdificioAreasServicios1:
                String nombre1 = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("NombreEdificioAreaServicioSelect", nombre1);
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

            startActivity(new Intent(this, MenuPrincipal.class));
            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
