package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import DAO.EdificioDAO;
import DAO.SitioDAO;

public class ResultadoMapaSala extends Activity {
    ImageView imageView4;
    SitioDAO _sitioDAO;
    EdificioDAO _edificioDAO;
    SharedPreferences prefs;
    TextView txtdetalleplano, txtNombreEdificioplano, txtestadoplano;


    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF startPoint = new PointF();
    PointF midPoint = new PointF();
    float oldDist = 1f;
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_mapa_sala);

        imageView4 = (ImageView) findViewById(R.id.imageView4);
        txtdetalleplano = (TextView) findViewById(R.id.txtdetalleplano);
        txtNombreEdificioplano = (TextView) findViewById(R.id.txtNombreEdificioplano);
        txtestadoplano = (TextView) findViewById(R.id.txtestadoplano);

        _sitioDAO = SitioDAO.getInstance(this);
        _edificioDAO = EdificioDAO.getInstance(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String EdificioArea = prefs.getString("NombreSalaLaboratorioSelect", "");
        String tipoSeleccionado = prefs.getString("NombreOficinaSalaSelect", "");

        Cursor cursor = _sitioDAO.getDatos2(tipoSeleccionado, EdificioArea);
        String ruta;
        if (cursor.moveToFirst()) {

            ruta = cursor.getString(cursor.getColumnIndex("planoSitio"));

            Picasso.with(getApplicationContext())
                    .load(ruta)
                    .placeholder(R.drawable.ic_launcher)
                    .error(R.drawable.ic_launcher).into(imageView4);

            txtNombreEdificioplano.setText(cursor.getString(cursor.getColumnIndex("NombreSitio")));
            txtestadoplano.setText(cursor.getString(cursor.getColumnIndex("estado")));
            txtdetalleplano.setText(cursor.getString(cursor.getColumnIndex("detalleSitio")));

        }
    }


    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botï¿½n al pulsar ir a atrï¿½s

            startActivity(new Intent(this, ResultadoSala.class));
            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

