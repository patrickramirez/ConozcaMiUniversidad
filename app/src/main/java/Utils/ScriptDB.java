package Utils;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import MOD.CampusMOD;
import MOD.EdificioMOD;
import MOD.SedeMOD;
import MOD.UniversidadMOD;

/**
 * Created by Patrick on 25-09-2015.
 */
public class ScriptDB{

    public static final String SCRIPT_DELETE_TABLE = "DROP TABLE IF EXISTS ";

        public boolean createDB(SQLiteDatabase db) {

            boolean ret = false;

            try {

                db.execSQL(UniversidadMOD.SCRIPT_CREACION_TABLA_UNIVERSIDAD);
                db.execSQL(EdificioMOD.SCRIPT_CREACION_TABLA_EDIFICIO);
                db.execSQL(SedeMOD.SCRIPT_CREACION_TABLA_SEDE);
                db.execSQL(CampusMOD.SCRIPT_CREACION_TABLA_CAMPUS);

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

                ret = true;
            } catch (Exception e) {
                Log.d("Exception CreateDB", e.getMessage());
            }

            return ret;
        }
}
