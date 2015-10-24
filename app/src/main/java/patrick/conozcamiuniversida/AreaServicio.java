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
import MOD.EdificioMOD;

public class AreaServicio extends Activity implements AdapterView.OnItemSelectedListener {

    EdificioDAO _edificioDAO;
    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;
    Spinner spinnerEdificiosAreas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_area_servicio);

        _edificioDAO = EdificioDAO.getInstance(this);
        spinnerEdificiosAreas = (Spinner) findViewById(R.id.spinnerEdificiosAreas);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        loadSpinnerEdificio();
    }

    public void BtnBanio(View v) {
        SharedPreferences.Editor editor;
        editor = prefs.edit();
        editor.putString("TipoSeleccionado", "Baño");
        editor.commit();
        startActivity(new Intent(this, ResultadoAreasServicios.class));
        finish();
    }

    private void loadSpinnerEdificio() {
        String NombreCampus = prefs.getString("campus", "");

        List<EdificioMOD> list = _edificioDAO.getEdificioListbyNombreCampus(NombreCampus);
        items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            items[i] = list.get(i).getNombreEdificio();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinnerEdificiosAreas.setAdapter(adapter);
        spinnerEdificiosAreas.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerEdificiosAreas:
                String nombreEdificio = parent.getItemAtPosition(position).toString();
                editor = prefs.edit();
                editor.putString("NombreEdificioSeleccionadoEnArea", nombreEdificio);
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
