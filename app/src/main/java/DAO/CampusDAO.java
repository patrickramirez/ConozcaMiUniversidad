package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import MOD.CampusMOD;
import MOD.SedeMOD;
import Utils.DB_helper;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class CampusDAO {

    private SQLiteDatabase database;
    private static CampusDAO instance;

    public static CampusDAO getInstance(Context context) {
        if (instance == null) {
            instance = new CampusDAO(context);
        }
        return instance;
    }

    private CampusDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
    }

    public void Insertar(int id, String nombre, String direccion, String latitud, String longitud, String estado, int idSede) {
        database.insert(
                "campus",
                null,
                CampusMOD.DatosCampus(id, nombre, direccion, latitud, longitud, estado, idSede));
    }

    public List<CampusMOD> getListCampusbynombreSede(String nombresede, String NombreUniversidad) {
        List<CampusMOD> lista = new ArrayList<CampusMOD>();
        CampusMOD _campusMOD;
        String idusede = "";
        String iduniversidad = "";

        String sql2 = "select id from universidad where nombre='" + NombreUniversidad + "'";
        Cursor ccc = database.rawQuery(sql2, null);
        if (ccc.moveToFirst()) {
            iduniversidad = ccc.getString(0);
        }

        String sql1 = "select idSede from sede where nombreSede='" + nombresede + "' and iduniversidad='" + iduniversidad + "'";
        Cursor cc = database.rawQuery(sql1,null);
        if(cc.moveToFirst()){
            idusede = cc.getString(0);
        }
        String sql = "select * from campus where idsede='"+ idusede + "'";
        Cursor c = database.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            do {
                _campusMOD = new CampusMOD();
                //_uniMOD.setId(c.getInt(c.getColumnIndex("id")));
                _campusMOD.setNombreCampus(c.getString(c.getColumnIndex("nombrecampus")));


                lista.add(_campusMOD);
            } while (c.moveToNext());
        }


        return lista;


    }
}
