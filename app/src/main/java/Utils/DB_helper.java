package Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * Created by Patrick on 02-10-2015.
 */
public class DB_helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cmu.db";
    private ScriptDB _scriptDB;

    private static DB_helper instance;

    private DB_helper(Context context) {
        /*Guardar archivo de base de datos en otra ruta*/
        super(context, Environment.getExternalStorageDirectory() + File.separator + "conozcomiuniversidad" + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
       /*Guardar archivo de base de datos en ruta por defecto*/
        // super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _scriptDB = new ScriptDB();
    }

    public static DB_helper getInstance(Context context) {
        if (instance == null) {
            instance = new DB_helper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        _scriptDB.createDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        _scriptDB.dropTable(db);
        onCreate(db);
    }
}