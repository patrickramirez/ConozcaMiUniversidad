package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import DAO.EdificioDAO;
import DAO.SitioDAO;

public class ResultadoMapaArea extends Activity {

    ImageView imageView4;
    SitioDAO _sitioDAO;
    EdificioDAO _edificioDAO;
    SharedPreferences prefs;
    TextView txtdetalleplano, txtNombreEdificioplano, txtestadoplano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_mapa_area);

        imageView4 = (ImageView) findViewById(R.id.imageView4);
        txtdetalleplano = (TextView) findViewById(R.id.txtdetalleplano);
        txtNombreEdificioplano = (TextView) findViewById(R.id.txtNombreEdificioplano);
        txtestadoplano = (TextView) findViewById(R.id.txtestadoplano);

        _sitioDAO = SitioDAO.getInstance(this);
        _edificioDAO = EdificioDAO.getInstance(this);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String EdificioArea = prefs.getString("NombreEdificioSeleccionadoEnArea", "");
        String tipoSeleccionado = prefs.getString("TipoSeleccionado", "");

        Cursor cursor = _sitioDAO.getDatos(EdificioArea, tipoSeleccionado);
        String ruta;
        if (cursor.moveToFirst()) {

            ruta = cursor.getString(cursor.getColumnIndex("planoSitio"));

            //  imageView4.setImageResource(Integer.parseInt(ruta));
        }
    }
}
