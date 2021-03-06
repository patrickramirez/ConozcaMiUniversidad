package MOD;

import android.content.ContentValues;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class SitioMOD {

    private int id;
    private String tipoSitio;
    private String detalleSitio;
    private String planoSitio;
    private String estado;
    private int idedificio;
    private String nombreSitio;

    public static final String NOMBRE_TABLE_SITIO = "sitio";
    public static final String TABLE_SITIO_ID = "idSitio";
    public static final String TABLE_SITIO_TIPO = "tipo";
    public static final String TABLE_SITIO_NOMBRESITIO = "NombreSitio";
    public static final String TABLE_SITIO_DETALLE = "detalleSitio";
    public static final String TABLE_SITIO_PLANOSITIO = "planoSitio";
    public static final String TABLE_SITIO_ESTADO = "estado";
    public static final String TABLE_SITIO_IDEDIFICIO = "idEdificio";

    public static final String SCRIPT_CREACION_TABLA_SITIO = "CREATE TABLE " + NOMBRE_TABLE_SITIO + "("
            + TABLE_SITIO_ID + " INTEGER PRIMARY KEY, "
            + TABLE_SITIO_TIPO + " TEXT, "
            + TABLE_SITIO_NOMBRESITIO + " TEXT, "
            + TABLE_SITIO_DETALLE + " TEXT, "
            + TABLE_SITIO_PLANOSITIO + " TEXT, "
            + TABLE_SITIO_ESTADO + " TEXT, "
            + TABLE_SITIO_IDEDIFICIO + " INTEGER)";


    public static ContentValues DatosSITIO(int id, String tipo, String nombreSitio, String detalle, String planositio,
                                           String estado, int idedificio) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_SITIO_ID, id);
        valores.put(TABLE_SITIO_TIPO, tipo);
        valores.put(TABLE_SITIO_NOMBRESITIO, nombreSitio);
        valores.put(TABLE_SITIO_DETALLE, detalle);
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

    public String getDetalleSitio() {
        return detalleSitio;
    }

    public void setDetalleSitio(String nombreSitio) {
        this.detalleSitio = nombreSitio;
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

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }
}
