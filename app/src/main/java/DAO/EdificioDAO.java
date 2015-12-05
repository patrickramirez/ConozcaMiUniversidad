package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import MOD.CampusMOD;
import MOD.EdificioMOD;
import MOD.UniversidadMOD;
import Utils.DB_helper;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class EdificioDAO {

    private SQLiteDatabase database;
    private static EdificioDAO instance;

    public static EdificioDAO getInstance(Context context) {
        if (instance == null) {
            instance = new EdificioDAO(context);
        }
        return instance;
    }

    private EdificioDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
    }

    public void Insertar(int id, String nombre, String direccion, String latitud, String longitud, String estado, int idCampus) {
        database.insert(
                "edificio",
                null,
                EdificioMOD.DatosEdificio(id, nombre, direccion, latitud, longitud, estado, idCampus));
    }

    public List<EdificioMOD> getEdificioListbyNombreCampus(String NombreCampus) {
        List<EdificioMOD> lista = new ArrayList<EdificioMOD>();
        EdificioMOD _edificioMOD;
        String Idcampus = "";

        String sql1 = "select idcampus from campus where nombrecampus='" + NombreCampus + "'";
        Cursor ccc = database.rawQuery(sql1, null);
        if (ccc.moveToFirst()) {
            Idcampus = ccc.getString(0);
        }


        String sql = "select nombreEdificio from edificio where idcampus ='" + Idcampus + "' order by nombreEdificio asc";
        Cursor c = database.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            do {
                _edificioMOD = new EdificioMOD();
                _edificioMOD.setNombreEdificio(c.getString(c.getColumnIndex("nombreEdificio")));


                lista.add(_edificioMOD);
            } while (c.moveToNext());
        }


        return lista;


    }


    public List<EdificioMOD> getEdificioListbyTipoSitio(String Nombre) {
        List<EdificioMOD> lista = new ArrayList<EdificioMOD>();
        EdificioMOD _edificioMOD;
        String Idcampus = "";

        String sql1 = "select idEdificio from sitio where tipo='" + Nombre + "'";
        Cursor ccc = database.rawQuery(sql1, null);
        if (ccc.moveToFirst()) {

            do {
                Idcampus = ccc.getString(0);


                String sql = "select nombreEdificio from edificio where idEdificio ='" + Idcampus + "' order by nombreEdificio asc";
                Cursor c = database.rawQuery(sql, null);

                if (c != null && c.moveToFirst()) {
                    do {
                        _edificioMOD = new EdificioMOD();
                        _edificioMOD.setNombreEdificio(c.getString(c.getColumnIndex("nombreEdificio")));


                        lista.add(_edificioMOD);
                    } while (c.moveToNext());
                }

            } while (ccc.moveToNext());
        }


        return lista;


    }

    public Cursor CursorGetDatosEdificio(String Edificio) {
        Boolean resp;
        String sql = "select nombreEdificio,direccion,latitud,longitud,estado from edificio where nombreEdificio = '" + Edificio + "'";
        Cursor c = database.rawQuery(sql, null);

        return c;

    }

    public Cursor CursorGetDatosEdificiobyID(String id) {
        Boolean resp;
        String sql = "select direccion,latitud,longitud from edificio where idEdificio = '" + id + "'";
        Cursor c = database.rawQuery(sql, null);

        return c;

    }


    public List<EdificioMOD> getEdificioListbyNombreSitio(String Nombre) {
        List<EdificioMOD> lista = new ArrayList<EdificioMOD>();
        EdificioMOD _edificioMOD;
        String Idcampus = "";

        String sql1 = "select idEdificio from sitio where NombreSitio='" + Nombre + "'";
        Cursor ccc = database.rawQuery(sql1, null);
        if (ccc.moveToFirst()) {

            do {
                Idcampus = ccc.getString(0);


                String sql = "select nombreEdificio from edificio where idEdificio ='" + Idcampus + "'";
                Cursor c = database.rawQuery(sql, null);

                if (c != null && c.moveToFirst()) {
                    do {
                        _edificioMOD = new EdificioMOD();
                        _edificioMOD.setNombreEdificio(c.getString(c.getColumnIndex("nombreEdificio")));


                        lista.add(_edificioMOD);
                    } while (c.moveToNext());
                }

            } while (ccc.moveToNext());
        }


        return lista;


    }


    public Cursor getEdificioaExplorarbyUniversidad(String NombreUniversidad) {


        String sql1 = "select edificio.latitud, edificio.longitud, edificio.nombreEdificio from universidad " +
                "inner join sede on sede.iduniversidad = universidad.id " +
                "inner join campus on campus.idsede = sede.idSede " +
                "inner join edificio on edificio.idEdificio = campus.idcampus " +
                "where universidad.nombre='" + NombreUniversidad + "'";

        Cursor cursor= database.rawQuery(sql1, null);

        if(cursor.moveToFirst()){
            String x = cursor.getString(0);
        }
        return cursor;


    }

}
