package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import MOD.EdificioMOD;
import MOD.FacultadMOD;
import Utils.DB_helper;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class FacultadDAO {

    private SQLiteDatabase database;

    private static FacultadDAO instance;

    public static FacultadDAO getInstance(Context context) {
        if (instance == null) {
            instance = new FacultadDAO(context);
        }
        return instance;
    }

    private FacultadDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
    }

    public List<FacultadMOD> getFacultadListbyNombreCampus(String NombreCampus) {
        List<FacultadMOD> lista = new ArrayList<FacultadMOD>();
        FacultadMOD _facultadMOD;
        String Idcampus = "";

        String sql1 = "select idcampus from campus where nombrecampus='" + NombreCampus + "'";
        Cursor ccc = database.rawQuery(sql1, null);
        if (ccc.moveToFirst()) {
            Idcampus = ccc.getString(0);
        }


        String sql = "select nombreFacultad from Facultad where idcampus ='" + Idcampus + "'";
        Cursor c = database.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            do {
                _facultadMOD = new FacultadMOD();
                _facultadMOD.setNombreFacultad(c.getString(c.getColumnIndex("nombreFacultad")));


                lista.add(_facultadMOD);
            } while (c.moveToNext());
        }


        return lista;


    }

    public void Insertar(int id, String nombre, String estado, int idcampus, int idedificio) {
        database.insert(
                "Facultad",
                null,
                FacultadMOD.DatosEdificio(id, nombre, estado, idcampus, idedificio));
    }

    public Cursor CursorGetDatosFacultad(String Facultad) {
        Boolean resp;
        String sql = "select nombreFacultad,estado,idedificio from facultad where nombreFacultad = '" + Facultad + "'";
        Cursor c = database.rawQuery(sql, null);

        return c;

    }

}
