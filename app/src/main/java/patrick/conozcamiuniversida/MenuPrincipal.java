package patrick.conozcamiuniversida;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
