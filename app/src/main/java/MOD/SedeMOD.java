package MOD;

import android.content.ContentValues;

/**
 * Created by Patrick on 12-10-2015.
 */
public class SedeMOD {

    private int idSede;
    private String nombreSede;
    private String direccion;
    private String latitud;
    private String longitud;
    private String estado;
    private int idUniversidad;


    public static final String NOMBRE_TABLE_SEDE= "sede";
    public static final String TABLE_SEDE_ID = "idSede";
    public static final String TABLE_SEDE_NOMBRE = "nombreSede";
    public static final String TABLE_SEDE_DIRECCION = "direccion";
    public static final String TABLE_SEDE_LATITUD = "latitud";
    public static final String TABLE_SEDE_LONGITUD = "longitud";
    public static final String TABLE_SEDE_ESTADO = "estado";
    public static final String TABLE_SEDE_IDUNIVERSIDAD = "iduniversidad";


    public static final String SCRIPT_CREACION_TABLA_SEDE = "CREATE TABLE " + NOMBRE_TABLE_SEDE + "("
            + TABLE_SEDE_ID + " INTEGER PRIMARY KEY, "
            + TABLE_SEDE_NOMBRE + " TEXT, "
            + TABLE_SEDE_DIRECCION + " TEXT, "
            + TABLE_SEDE_LATITUD + " TEXT, "
            + TABLE_SEDE_LONGITUD + " TEXT, "
            + TABLE_SEDE_ESTADO + " TEXT, "
            + TABLE_SEDE_IDUNIVERSIDAD + " INTEGER)";


    public static ContentValues DatosSede(int id,String nombre, String direccion, String latitud, String longitud, String estado, int iduniversidad) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_SEDE_ID, id);
        valores.put(TABLE_SEDE_NOMBRE, nombre);
        valores.put(TABLE_SEDE_DIRECCION, direccion);
        valores.put(TABLE_SEDE_LATITUD, latitud);
        valores.put(TABLE_SEDE_LONGITUD, longitud);
        valores.put(TABLE_SEDE_ESTADO, estado);
        valores.put(TABLE_SEDE_IDUNIVERSIDAD, iduniversidad);

        return valores;
    }



    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
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

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }
}
