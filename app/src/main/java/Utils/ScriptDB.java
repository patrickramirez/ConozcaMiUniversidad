package Utils;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import MOD.CampusMOD;
import MOD.EdificioMOD;
import MOD.FacultadMOD;
import MOD.SedeMOD;
import MOD.SitioMOD;
import MOD.UniversidadMOD;

/**
 * Created by Patrick on 25-09-2015.
 */
public class ScriptDB {

    public static final String SCRIPT_DELETE_TABLE = "DROP TABLE IF EXISTS ";

    public boolean createDB(SQLiteDatabase db) {

        boolean ret = false;

        try {

            try {
                Log.i("JMC", "create tablet universidad");
                db.execSQL(UniversidadMOD.SCRIPT_CREACION_TABLA_UNIVERSIDAD);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Log.i("JMC", "create tablet edificio");
                db.execSQL(EdificioMOD.SCRIPT_CREACION_TABLA_EDIFICIO);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Log.i("JMC", "create tablet sede");
                db.execSQL(SedeMOD.SCRIPT_CREACION_TABLA_SEDE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Log.i("JMC", "create tablet campus");
                db.execSQL(CampusMOD.SCRIPT_CREACION_TABLA_CAMPUS);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Log.i("JMC", "create tablet facultad");
                db.execSQL(FacultadMOD.SCRIPT_CREACION_TABLA_FACULTAD);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Log.i("JMC", "create tablet sitio");
                db.execSQL(SitioMOD.SCRIPT_CREACION_TABLA_SITIO);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ret = true;
        } catch (Exception e) {
            Log.e("Exception CreateDB", e.getMessage());
        }

        return ret;
    }

    public boolean dropTable(SQLiteDatabase db) {
        boolean ret = false;

        try {
            db.execSQL(SCRIPT_DELETE_TABLE + UniversidadMOD.NOMBRE_TABLE_UNIVERSIDAD);
            db.execSQL(SCRIPT_DELETE_TABLE + EdificioMOD.NOMBRE_TABLE_EDIFICIO);
            db.execSQL(SCRIPT_DELETE_TABLE + SedeMOD.NOMBRE_TABLE_SEDE);
            db.execSQL(SCRIPT_DELETE_TABLE + CampusMOD.NOMBRE_TABLE_CAMPUS);
            db.execSQL(SCRIPT_DELETE_TABLE + FacultadMOD.NOMBRE_TABLE_FACULTAD);
            db.execSQL(SCRIPT_DELETE_TABLE + SitioMOD.NOMBRE_TABLE_SITIO);

            ret = true;
        } catch (Exception e) {
            Log.d("Exception CreateDB", e.getMessage());
        }

        return ret;
    }
}
