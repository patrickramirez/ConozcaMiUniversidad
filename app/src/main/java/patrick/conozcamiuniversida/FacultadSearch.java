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
import DAO.FacultadDAO;
import MOD.EdificioMOD;
import MOD.FacultadMOD;

public class FacultadSearch extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerFacultad;
    FacultadDAO _facultadDAO;

    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_facultad_search);

        spinnerFacultad = (Spinner) findViewById(R.id.spinnerFacultad);
        _facultadDAO = FacultadDAO.getInstance(this);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        loadSpinnerFacultad();
    }

    private void loadSpinnerFacultad() {
        String NombreCampus = prefs.getString("campus", "");

        List<FacultadMOD> list = _facultadDAO.getFacultadListbyNombreCampus(NombreCampus);
        items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getNombreFacultad();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);
        spinnerFacultad.setAdapter(adapter);
        spinnerFacultad.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerFacultad:
                String nombreEdificio = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("NombreFacultadSeleccionado", nombreEdificio);
                editor.commit();


                break;
        }

    }

    public void btnBuscar(View v) {
        startActivity(new Intent(this, ResultadoFacultadSearch.class));
        finish();
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
