package MOD;

import android.content.ContentValues;

/**
 * Created by Patrick on 12-10-2015.
 */
public class UniversidadMOD {

    private int Id;
    private String NombreUniversidad;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String Estado;


    public static final String NOMBRE_TABLE_UNIVERSIDAD = "universidad";
    public static final String TABLE_UNIVERSIDAD_ID = "id";
    public static final String TABLE_UNIVERSIDAD_NOMBRE = "nombre";
    public static final String TABLE_UNIVERSIDAD_DIRECCION = "direccion";
    public static final String TABLE_UNIVERSIDAD_LATITUD = "latitud";
    public static final String TABLE_UNIVERSIDAD_LONGITUD = "longitud";
    public static final String TABLE_UNIVERSIDAD_ESTADO = "estado";


    public static final String SCRIPT_CREACION_TABLA_UNIVERSIDAD = "CREATE TABLE " + NOMBRE_TABLE_UNIVERSIDAD + "("
            + TABLE_UNIVERSIDAD_ID + " INTEGER PRIMARY KEY, " + TABLE_UNIVERSIDAD_NOMBRE + " TEXT, "
            + TABLE_UNIVERSIDAD_DIRECCION + " TEXT, " + TABLE_UNIVERSIDAD_LATITUD + " TEXT, " + TABLE_UNIVERSIDAD_LONGITUD + " TEXT, "
            + TABLE_UNIVERSIDAD_ESTADO + " TEXT)";

//    public static final String SCRIPT_CREACION_TABLA_UNIVERSIDAD = "CREATE TABLE "
//            +"universidad"+"("+
//            "id"+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//            "nombre"+" TEXT NOT NULL, "+
//            "direccion"+" TEXT NOT NULL, "+
//            "latitud"+" TEXT NOT NULL, "+
//            "longitud"+" TEXT NOT NULL, "+
//            "estado"+" TEXT NOT NULL)";


    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("idUniversidad", Id);
        values.put("NombreUniversidad", NombreUniversidad);
        values.put("Direccion", Direccion);
        values.put("Latitud", Latitud);
        values.put("Longitud", Longitud);
        values.put("Estado", Estado);


        return values;
    }

    public static ContentValues DatosUniversidad(int id,String nombre, String direccion, String latitud, String longitud, String estado) {
        ContentValues valores = new ContentValues();

        valores.put(TABLE_UNIVERSIDAD_ID, id);
        valores.put(TABLE_UNIVERSIDAD_NOMBRE, nombre);
        valores.put(TABLE_UNIVERSIDAD_DIRECCION, direccion);
        valores.put(TABLE_UNIVERSIDAD_LATITUD, latitud);
        valores.put(TABLE_UNIVERSIDAD_LONGITUD, longitud);
        valores.put(TABLE_UNIVERSIDAD_ESTADO, estado);

        return valores;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombreUniversidad() {
        return NombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        NombreUniversidad = nombreUniversidad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
