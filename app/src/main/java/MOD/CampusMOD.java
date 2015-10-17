package MOD;

import android.content.ContentValues;

/**
 * Created by Patrick on 12-10-2015.
 */
public class CampusMOD {

    private int idCampus;
    private String nombreCampus;
    private String direccion;
    private String latitud;
    private String longitud;
    private String estado;
    private int idSede;

    public static final String NOMBRE_TABLE_CAMPUS= "campus";
    public static final String TABLE_CAMPUS_ID = "idcampus";
    public static final String TABLE_CAMPUS_NOMBRE = "nombrecampus";
    public static final String TABLE_CAMPUS_DIRECCION = "direccion";
    public static final String TABLE_CAMPUS_LATITUD = "latitud";
    public static final String TABLE_CAMPUS_LONGITUD = "longitud";
    public static final String TABLE_CAMPUS_ESTADO = "estado";
    public static final String TABLE_CAMPUS_IDSEDE = "idsede";

    public static final String SCRIPT_CREACION_TABLA_CAMPUS = "CREATE TABLE " + NOMBRE_TABLE_CAMPUS + "("
            + TABLE_CAMPUS_ID + " INTEGER PRIMARY KEY, "
            + TABLE_CAMPUS_NOMBRE + " TEXT, "
            + TABLE_CAMPUS_DIRECCION + " TEXT, "
            + TABLE_CAMPUS_LATITUD + " TEXT, "
            + TABLE_CAMPUS_LONGITUD + " TEXT, "
            + TABLE_CAMPUS_ESTADO + " TEXT, "
            + TABLE_CAMPUS_IDSEDE + " INTEGER)";


    public static ContentValues DatosCampus(int id,String nombre, String direccion, String latitud, String longitud, String estado, int idsede) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_CAMPUS_ID, id);
        valores.put(TABLE_CAMPUS_NOMBRE, nombre);
        valores.put(TABLE_CAMPUS_DIRECCION, direccion);
        valores.put(TABLE_CAMPUS_LATITUD, latitud);
        valores.put(TABLE_CAMPUS_LONGITUD, longitud);
        valores.put(TABLE_CAMPUS_ESTADO, estado);
        valores.put(TABLE_CAMPUS_IDSEDE, idsede);

        return valores;
    }


    public int getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(int idCampus) {
        this.idCampus = idCampus;
    }

    public String getNombreCampus() {
        return nombreCampus;
    }

    public void setNombreCampus(String nombreCampus) {
        this.nombreCampus = nombreCampus;
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

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
