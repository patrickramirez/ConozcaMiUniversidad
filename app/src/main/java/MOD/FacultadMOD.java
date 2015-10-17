package MOD;

import android.content.ContentValues;

/**
 * Created by jogan1075 on 17-10-15.
 */
public class FacultadMOD {

    private int id;
    private String nombreFacultad;
    private String direccion;
    private String estado;
    private String latitud;
    private String longitud;
    private int idcampus;
    private int idedificio;


    public static final String NOMBRE_TABLE_FACULTAD = "facultad";
    public static final String TABLE_FACULTAD_ID = "idFacultad";
    public static final String TABLE_FACULTAD_NOMBRE = "nombreFacultad";
    public static final String TABLE_FACULTAD_DIRECCION = "direccion";
    public static final String TABLE_FACULTAD_LATITUD = "latitud";
    public static final String TABLE_FACULTAD_LONGITUD = "longitud";
    public static final String TABLE_FACULTAD_ESTADO = "estado";
    public static final String TABLE_FACULTAD_IDCAMPUS = "idcampus";
    public static final String TABLE_FACULTAD_IDEDIFICIO = "idedificio";


    public static final String SCRIPT_CREACION_TABLA_FACULTAD = "CREATE TABLE " + NOMBRE_TABLE_FACULTAD + "("
            + TABLE_FACULTAD_ID + " INTEGER PRIMARY KEY, "
            + TABLE_FACULTAD_NOMBRE + " TEXT, "
            + TABLE_FACULTAD_DIRECCION + " TEXT, "
            + TABLE_FACULTAD_LATITUD + " TEXT, "
            + TABLE_FACULTAD_LONGITUD + " TEXT, "
            + TABLE_FACULTAD_ESTADO + " TEXT, "
            + TABLE_FACULTAD_IDCAMPUS + " INTEGER, "
            + TABLE_FACULTAD_IDEDIFICIO + " INTEGER)";


    public static ContentValues DatosEdificio(int id, String nombre, String direccion, String latitud,
                                              String longitud, String estado, int idcampus, int idedificio) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_FACULTAD_ID, id);
        valores.put(TABLE_FACULTAD_NOMBRE, nombre);
        valores.put(TABLE_FACULTAD_DIRECCION, direccion);
        valores.put(TABLE_FACULTAD_LATITUD, latitud);
        valores.put(TABLE_FACULTAD_LONGITUD, longitud);
        valores.put(TABLE_FACULTAD_ESTADO, estado);
        valores.put(TABLE_FACULTAD_IDCAMPUS, idcampus);
        valores.put(TABLE_FACULTAD_IDEDIFICIO, idedificio);


        return valores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public int getIdcampus() {
        return idcampus;
    }

    public void setIdcampus(int idcampus) {
        this.idcampus = idcampus;
    }

    public int getIdedificio() {
        return idedificio;
    }

    public void setIdedificio(int idedificio) {
        this.idedificio = idedificio;
    }
}
