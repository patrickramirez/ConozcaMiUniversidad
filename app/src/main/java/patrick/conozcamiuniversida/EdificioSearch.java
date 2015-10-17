package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import DAO.EdificioDAO;
import MOD.EdificioMOD;
import MOD.UniversidadMOD;

public class EdificioSearch extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerEdificio;
    EdificioDAO _edificioDAO;

    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificio_search);

        spinnerEdificio = (Spinner) findViewById(R.id.spinnerEdificio);
        _edificioDAO = EdificioDAO.getInstance(this);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        loadSpinnerEdificio();

    }

    public void btnBuscar(View v) {
        startActivity(new Intent(this, ResultadoEdificioSearch.class));
        finish();
    }

    private void loadSpinnerEdificio() {
        String NombreCampus = prefs.getString("campus", "");

        List<EdificioMOD> list = _edificioDAO.getEdificioListbyNombreCampus(NombreCampus);
        items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getNombreEdificio();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);
        spinnerEdificio.setAdapter(adapter);
        spinnerEdificio.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerUniversidad:
                String nombreEdificio = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("NombreEdificioSeleccionado", nombreEdificio);
                editor.commit();


                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
