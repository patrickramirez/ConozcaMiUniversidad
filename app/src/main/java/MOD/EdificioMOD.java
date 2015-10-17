package MOD;

import android.content.ContentValues;

/**
 * Created by Patrick on 12-10-2015.
 */
public class EdificioMOD {

    private int idEdificio;
    private String nombreEdificio;
    private String direccion;
    private String latitud;
    private String longitud;
    private String estado;
    private int idCampus;

    public static final String NOMBRE_TABLE_EDIFICIO = "edificio";
    public static final String TABLE_EDIFICIO_ID = "idEdificio";
    public static final String TABLE_EDIFICIO_NOMBRE = "nombreEdificio";
    public static final String TABLE_EDIFICIO_DIRECCION = "direccion";
    public static final String TABLE_EDIFICIO_LATITUD = "latitud";
    public static final String TABLE_EDIFICIO_LONGITUD = "longitud";
    public static final String TABLE_EDIFICIO_ESTADO = "estado";
    public static final String TABLE_EDIFICIO_IDCAMPUS = "idcampus";


    public static final String SCRIPT_CREACION_TABLA_EDIFICIO = "CREATE TABLE " + NOMBRE_TABLE_EDIFICIO + "("
            + TABLE_EDIFICIO_ID + " INTEGER PRIMARY KEY, "
            + TABLE_EDIFICIO_NOMBRE + " TEXT, "
            + TABLE_EDIFICIO_DIRECCION + " TEXT, "
            + TABLE_EDIFICIO_LATITUD + " TEXT, "
            + TABLE_EDIFICIO_LONGITUD + " TEXT, "
            + TABLE_EDIFICIO_ESTADO + " TEXT, "
            + TABLE_EDIFICIO_IDCAMPUS + " INTEGER)";

    public static ContentValues DatosEdificio(int id, String nombre, String direccion, String latitud,
                                              String longitud, String estado, int idcampus) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_EDIFICIO_ID, id);
        valores.put(TABLE_EDIFICIO_NOMBRE, nombre);
        valores.put(TABLE_EDIFICIO_DIRECCION, direccion);
        valores.put(TABLE_EDIFICIO_LATITUD, latitud);
        valores.put(TABLE_EDIFICIO_LONGITUD, longitud);
        valores.put(TABLE_EDIFICIO_ESTADO, estado);
        valores.put(TABLE_EDIFICIO_IDCAMPUS, idcampus);

        return valores;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
