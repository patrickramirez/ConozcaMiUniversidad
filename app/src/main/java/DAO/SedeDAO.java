package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import MOD.SedeMOD;
import MOD.UniversidadMOD;
import Utils.DB_helper;

/**
 * Created by Patrick on 12-10-2015.
 */
public class SedeDAO {

    private SQLiteDatabase database;
    private static SedeDAO instance;

    public static SedeDAO getInstance(Context context) {
        if (instance == null) {
            instance = new SedeDAO(context);
        }
        return instance;
    }

    private SedeDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
    }

    public void Insertar(int id, String nombre, String direccion, String latitud, String longitud, String estado, int iduniversidad) {
        database.insert(
                "sede",
                null,
                SedeMOD.DatosSede(id, nombre, direccion, latitud, longitud, estado, iduniversidad));
    }

    public List<SedeMOD> getListsedes(String nombre) {
        List<SedeMOD> lista = new ArrayList<SedeMOD>();
        SedeMOD _sedeMOD;
        int iduniversidad=0;

        String sql1 = "select id from universidad where nombre='"+ nombre +"'";
        Cursor cc = database.rawQuery(sql1,null);
        if(cc.moveToFirst()){
            iduniversidad = cc.getInt(0);
        }
        String sql = "select * from sede where iduniversidad="+ iduniversidad;
        Cursor c = database.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            do {
                _sedeMOD = new SedeMOD();
                //_uniMOD.setId(c.getInt(c.getColumnIndex("id")));
                _sedeMOD.setNombreSede(c.getString(c.getColumnIndex("nombreSede")));


                lista.add(_sedeMOD);
            } while (c.moveToNext());
        }


        return lista;


    }


}
