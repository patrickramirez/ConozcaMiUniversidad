package patrick.conozcamiuniversida;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by jogan1075 on 05-12-15.
 */
public class ConoscaApp extends Application {
    public void onCreate() {
        super.onCreate();
        turnGPSOn();
//        startService(new Intent(this, GpsService.class));
    }

    private void turnGPSOn() {
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "ConozceTuUniversidad");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }
}
