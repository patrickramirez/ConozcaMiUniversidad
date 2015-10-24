package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import MOD.SedeMOD;
import MOD.SitioMOD;
import Utils.DB_helper;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class SitioDAO {

    private SQLiteDatabase database;
    private static SitioDAO instance;

    public static SitioDAO getInstance(Context context) {
        if (instance == null) {
            instance = new SitioDAO(context);
        }
        return instance;
    }

    private SitioDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
    }

    public void Insertar(int id, String tipo, String nombre, String planositio,
                         String estado, int idedificio) {
        database.insert(
                "sitio",
                null,
                SitioMOD.DatosSITIO(id, tipo, nombre, planositio, estado, idedificio));
    }

    public Cursor getDatos(String nombreEdificio, String tipo) {
        String idedificio = "";
        String sql = "select idEdificio from edificio where nombreEdificio='" + nombreEdificio + "'";
        Cursor c = database.rawQuery(sql, null);
        if (c.moveToFirst()) {
            idedificio = c.getString(0);
        }

        String sql1 = "select * from sitio where tipo='" + tipo + "' and idEdificio='" + idedificio + "'";

        Cursor cc = database.rawQuery(sql1, null);
        return cc;

    }
}
