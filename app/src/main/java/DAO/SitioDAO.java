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

    public Cursor getDatos2(String nombreEdificio, String NombreSitio) {
        String idedificio = "";
        String sql = "select idEdificio from edificio where nombreEdificio='" + nombreEdificio + "'";
        Cursor c = database.rawQuery(sql, null);
        if (c.moveToFirst()) {
            idedificio = c.getString(0);
        }

        String sql1 = "select * from sitio where NombreSitio='" + NombreSitio + "' and idEdificio='" + idedificio + "'";

        Cursor cc = database.rawQuery(sql1, null);
        return cc;

    }

    public Cursor getDatos3(String nombreEdificio, String NombreSitio) {
        String idedificio = "";


        String sql1 = "select edificio.nombreEdificio, edificio.direccion, edificio.latitud, edificio.longitud, edificio.estado from sitio " +
                "inner join edificio on edificio.idedificio = sitio.idEdificio" +
                " where sitio.NombreSitio='" + NombreSitio + "' and edificio.nombreEdificio='" + nombreEdificio + "'";

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


                String sql = "select * from sitio where tipo!='Sala' and tipo != 'Laboratorio' and tipo!= 'Oficina' and tipo!='Decanato' and tipo!= 'Departamento' order by tipo asc";
                Cursor c = database.rawQuery(sql, null);

                if (c.moveToFirst()) {
                    do {

                        String idedif = c.getString(c.getColumnIndex("idEdificio")).toString();

                        if (idedif.equalsIgnoreCase(ideficio)) {
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


                    } while (c.moveToNext());
                }
            } while (cc.moveToNext());
        }

        return lista;


    }


    public List<SitioMOD> getOficinasList(String campus) {
        List<SitioMOD> lista = new ArrayList<SitioMOD>();
        SitioMOD _edificioMOD = null;

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


                String sql = "select * from sitio where tipo='Oficina' or tipo='Decanato' or tipo='Departamento'";
                Cursor c = database.rawQuery(sql, null);

                if (c.moveToFirst()) {
                    do {
                        String idedif = c.getString(c.getColumnIndex("idEdificio")).toString();
                        if (idedif.equalsIgnoreCase(ideficio)) {
                            String tipoSitio = c.getString(c.getColumnIndex("NombreSitio")).toString();
                            _edificioMOD = new SitioMOD();


                            _edificioMOD.setTipoSitio(tipoSitio);
                            lista.add(_edificioMOD);
                        }

                    } while (c.moveToNext());
                }

            } while (cc.moveToNext());
        }

        return lista;


    }


    public List<SitioMOD> getSalasLaboratoriosList(String Edificio) {
        List<SitioMOD> lista = new ArrayList<SitioMOD>();
        SitioMOD _edificioMOD = null;

        String Idcampus = "";
        String ideficio = "";


        String sql2 = "select distinct idEdificio from edificio where nombreEdificio ='" + Edificio + "'";
        Cursor cc = database.rawQuery(sql2, null);
        if (cc.moveToFirst()) {

            do {
                ideficio = cc.getString(0);


                String sql = "select * from sitio where tipo='Sala' or tipo='Laboratorio' order by NombreSitio asc";
                Cursor c = database.rawQuery(sql, null);

                if (c.moveToFirst()) {
                    do {
                        String idedif = c.getString(c.getColumnIndex("idEdificio")).toString();
                        if (idedif.equalsIgnoreCase(ideficio)) {
                            String tipoSitio = c.getString(c.getColumnIndex("NombreSitio")).toString();
                            _edificioMOD = new SitioMOD();


                            _edificioMOD.setTipoSitio(tipoSitio);
                            lista.add(_edificioMOD);
                        }

                    } while (c.moveToNext());
                }

            } while (cc.moveToNext());
        }

        return lista;


    }
}
