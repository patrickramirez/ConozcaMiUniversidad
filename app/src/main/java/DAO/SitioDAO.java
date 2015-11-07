package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import MOD.EdificioMOD;
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

    public void Insertar(int id, String tipo, String nombreSitio, String detalle, String planositio,
                         String estado, int idedificio) {
        database.insert(
                "sitio",
                null,
                SitioMOD.DatosSITIO(id, tipo, nombreSitio, detalle, planositio, estado, idedificio));
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

    public List<SitioMOD> getSitioList(String campus) {
        List<SitioMOD> lista = new ArrayList<SitioMOD>();
        SitioMOD _edificioMOD;

        String Idcampus = "";
        String ideficio = "";

        String sql1 = "select idcampus from campus where nombrecampus='" + campus + "'";
        Cursor ccc = database.rawQuery(sql1, null);
        if (ccc.moveToFirst()) {
            Idcampus = ccc.getString(0);
        }

        String sql2 = "select distinct idEdificio from edificio where idcampus ='" + Idcampus + "'";
        Cursor cc = database.rawQuery(sql2, null);
        if (cc.moveToFirst()) {

            do {
                ideficio = cc.getString(0);


                String sql = "select distinct tipo from sitio where idedificio = '" + ideficio + "'";
                Cursor c = database.rawQuery(sql, null);

                if (c != null && c.moveToFirst()) {
                    do {

                        if (!c.getString(c.getColumnIndex("tipo")).equalsIgnoreCase("sala")) {
                            if (!c.getString(c.getColumnIndex("tipo")).equals("laboratorio")) {
                                if (!c.getString(c.getColumnIndex("tipo")).equals("oficina")) {
                                    if (!c.getString(c.getColumnIndex("tipo")).equals("departamento")) {
                                        if (!c.getString(c.getColumnIndex("tipo")).equals("decanato")) {

                                            String tipoSitio = c.getString(c.getColumnIndex("tipo"));

                                            if (lista.size() != 0) {
                                                for (int i = 0; i < lista.size(); i++) {
                                                    _edificioMOD = lista.get(i);
                                                    if (!_edificioMOD.getTipoSitio().equalsIgnoreCase(tipoSitio)) {
                                                        _edificioMOD = new SitioMOD();
                                                        _edificioMOD.setTipoSitio(tipoSitio);


                                                        lista.add(_edificioMOD);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                _edificioMOD = new SitioMOD();
                                                _edificioMOD.setTipoSitio(tipoSitio);


                                                lista.add(_edificioMOD);
                                            }


                                        }
                                    }
                                }
                            }
                        }


                    } while (c.moveToNext());
                }
            } while (cc.moveToNext());
        }

        return lista;


    }
}
