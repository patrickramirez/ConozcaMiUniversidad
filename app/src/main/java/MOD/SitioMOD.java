package MOD;

import android.content.ContentValues;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class SitioMOD {

    private int id;
    private String tipoSitio;
    private String nombreSitio;
    private String planoSitio;
    private String estado;
    private int idedificio;

    public static final String NOMBRE_TABLE_SITIO = "sitio";
    public static final String TABLE_SITIO_ID = "idSitio";
    public static final String TABLE_SITIO_TIPO = "tipo";
    public static final String TABLE_SITIO_NOMBRE = "nombreSitio";
    public static final String TABLE_SITIO_PLANOSITIO = "planoSitio";
    public static final String TABLE_SITIO_ESTADO = "estado";
    public static final String TABLE_SITIO_IDEDIFICIO = "idEdificio";

    public static final String SCRIPT_CREACION_TABLA_SITIO = "CREATE TABLE " + NOMBRE_TABLE_SITIO + "("
            + TABLE_SITIO_ID + " INTEGER PRIMARY KEY, "
            + TABLE_SITIO_TIPO + " TEXT, "
            + TABLE_SITIO_NOMBRE + " TEXT, "
            + TABLE_SITIO_PLANOSITIO + " TEXT, "
            + TABLE_SITIO_ESTADO + " TEXT, "
            + TABLE_SITIO_IDEDIFICIO + " INTEGER)";


    public static ContentValues DatosSITIO(int id, String tipo, String nombre, String planositio,
                                           String estado, int idedificio) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_SITIO_ID, id);
        valores.put(TABLE_SITIO_TIPO, tipo);
        valores.put(TABLE_SITIO_NOMBRE, nombre);
        valores.put(TABLE_SITIO_PLANOSITIO, planositio);
        valores.put(TABLE_SITIO_ESTADO, estado);
        valores.put(TABLE_SITIO_IDEDIFICIO, idedificio);


        return valores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoSitio() {
        return tipoSitio;
    }

    public void setTipoSitio(String tipoSitio) {
        this.tipoSitio = tipoSitio;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getPlanoSitio() {
        return planoSitio;
    }

    public void setPlanoSitio(String planoSitio) {
        this.planoSitio = planoSitio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdedificio() {
        return idedificio;
    }

    public void setIdedificio(int idedificio) {
        this.idedificio = idedificio;
    }
}
