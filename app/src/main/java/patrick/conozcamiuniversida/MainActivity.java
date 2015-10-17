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

import DAO.SedeDAO;
import DAO.UniversidadDAO;
import MOD.SedeMOD;
import MOD.UniversidadMOD;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    UniversidadDAO _uniDAO;
    SedeDAO _sedeDAO;
    Spinner spinnerregistro,spinnerregistro1;

    String[] items;
    ArrayAdapter<String> adapter;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerregistro =(Spinner)findViewById(R.id.spinnerregistro);

        spinnerregistro1=(Spinner)findViewById(R.id.spinnerregistro1);
        _uniDAO = UniversidadDAO.getInstance(this);
        _sedeDAO = SedeDAO.getInstance(this);

        /*habilitar para insertar datos borrando todo de 0
        _uniDAO.Insertar(0,"Universidad Andres Bello","republica 237","","","disponible");
        _uniDAO.Insertar(1,"Universidad Diego Portales","ejercito 100","","","disponible");
        _sedeDAO.Insertar(0,"Santiago","republica 237","","","disponible",0);
        _sedeDAO.Insertar(1,"Viña","quillota 980","","","disponible",0);
        _sedeDAO.Insertar(2,"Santiago","ejercito 100","","","disponible",1);*/

        List<UniversidadMOD> list = _uniDAO.getUniversidadList();
        items = new String[list.size()];
        for ( int i= 0; i < list.size(); i++){

            items[i] = list.get(i).getNombreUniversidad();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);

        spinnerregistro.setAdapter(adapter);
        spinnerregistro.setOnItemSelectedListener(this);


        prefs=      getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

    }
    public void BtnEntrar(View v){
//        startActivity(new Intent(this, Menu_principal.class));
//        finish();
        Toast.makeText(this,"entre",Toast.LENGTH_SHORT).show();

    }

    public void cargarspinner2(String nombre){

        List<SedeMOD> list = _sedeDAO.getListsedes(nombre);
        items = new String[list.size()];

        for ( int i= 0; i < list.size(); i++){

            items[i] = list.get(i).getNombreSede();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                items);

        spinnerregistro1.setAdapter(adapter);
        spinnerregistro1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();

        SharedPreferences.Editor editor;

        switch (parent.getId()) {

            case R.id.spinnerregistro:
                editor = prefs.edit();
                editor.putString("Universidad", nombre);
                editor.commit();
                break;
            case R.id.spinnerregistro1:
                editor = prefs.edit();
                editor.putString("sede", nombre);
                editor.commit();

        }

        cargarspinner2(nombre);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
