package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import MOD.UniversidadMOD;
import Utils.DB_helper;


public class UniversidadDAO {

    private SQLiteDatabase database;
    private static UniversidadDAO instance;

    public static UniversidadDAO getInstance(Context context) {
        if (instance == null) {
            instance = new UniversidadDAO(context);
        }
        return instance;
    }

    private UniversidadDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
    }


    public void insert(ContentValues values) {

        try {
            database.insert("Universidad", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Insertar(int id, String nombre, String direccion, String latitud, String longitud, String estado) {
        database.insert(
                "Universidad",
                null,
                UniversidadMOD.DatosUniversidad(id, nombre, direccion, latitud, longitud, estado));
    }

    public List<UniversidadMOD> getUniversidadList() {
        List<UniversidadMOD> lista = new ArrayList<UniversidadMOD>();
        UniversidadMOD _uniMOD;

        String sql = "select nombre from universidad";
        Cursor c = database.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            do {
                _uniMOD = new UniversidadMOD();
                //_uniMOD.setId(c.getInt(c.getColumnIndex("id")));
                _uniMOD.setNombreUniversidad(c.getString(c.getColumnIndex("nombre")));


                lista.add(_uniMOD);
            } while (c.moveToNext());
        }


        return lista;


    }

    public UniversidadMOD getUniversidadbyId(int id){
        UniversidadMOD _uniMOD = null;
        String sql = "select * from universidad where id=" + id;

        Cursor c = database.rawQuery(sql, null);
        if (c.moveToNext()) {
            _uniMOD = new UniversidadMOD();
            _uniMOD.setNombreUniversidad(c.getString(c.getColumnIndex("nombre")));

        }
        return _uniMOD;

    }


    public Cursor SelectAllData() {

        try {


            String strSQL = "SELECT * FROM universidad";

            Cursor cursor = database.rawQuery(strSQL, null);

            return cursor;

        } catch (Exception e) {

            return null;

        }

    }


}
