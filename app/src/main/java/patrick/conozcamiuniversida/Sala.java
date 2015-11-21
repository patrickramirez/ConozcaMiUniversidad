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

import java.util.List;

import DAO.EdificioDAO;
import DAO.SitioDAO;
import MOD.EdificioMOD;
import MOD.SitioMOD;

public class Sala extends Activity implements AdapterView.OnItemSelectedListener {

    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;
    Spinner spinnerSalaLaboratorio, spinnerEdificioSala;

    EdificioDAO _edificioDAO;
    SitioDAO _sitioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sala);

        spinnerEdificioSala = (Spinner) findViewById(R.id.spinnerEdificioSala);
        spinnerSalaLaboratorio = (Spinner) findViewById(R.id.spinnerSalaLaboratorio);

        _edificioDAO = EdificioDAO.getInstance(this);
        _sitioDAO = SitioDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        LoadSpinnerEdificios();
    }

    private void LoadSpinnerEdificios() {
        String Nombre = prefs.getString("campus", "");

        List<EdificioMOD> lista = _edificioDAO.getEdificioListbyNombreCampus(Nombre);
        items = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            items[i] = lista.get(i).getNombreEdificio();

        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinnerEdificioSala.setAdapter(adapter);
        spinnerEdificioSala.setOnItemSelectedListener(this);

    }

    private void loadSpinnerSalaLaboratorio() {
        String Nombre = prefs.getString("NombreOficinaSalaSelect", "");

        List<SitioMOD> list = _sitioDAO.getSalasLaboratoriosList(Nombre);
        items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getTipoSitio();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinnerSalaLaboratorio.setAdapter(adapter);
        spinnerSalaLaboratorio.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerEdificioSala:
                String nombre = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("NombreOficinaSalaSelect", nombre);
                editor.commit();

                loadSpinnerSalaLaboratorio();
                break;

            case R.id.spinnerSalaLaboratorio:
//                String nombre1 = parent.getItemAtPosition(position).toString();
//                editor = prefs.edit();
//                editor.putString("NombreEdificioOficinaSelect", nombre1);
//                editor.commit();


                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void btnBuscar(View v) {

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
