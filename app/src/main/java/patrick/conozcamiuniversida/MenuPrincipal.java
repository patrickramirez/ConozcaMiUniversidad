package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import patrick.conozcamiuniversida.R;

public class MenuPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void BtnEdificio(View v) {
        startActivity(new Intent(this, EdificioSearch.class));
        finish();
    }

    public void BtnFacultad(View v) {
        startActivity(new Intent(this, FacultadSearch.class));
        finish();
    }

    public void BtnCambiarRegistro(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void BtnAreaServicios(View v) {
        startActivity(new Intent(this, AreaServicio.class));
        finish();
    }

    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            finish();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME
                && event.getRepeatCount() == 0) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
