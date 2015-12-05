package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.ConnectMySQL;
import Utils.DB_helper;

/**
 * Created by jogan1075 on 05-12-15.
 */
public class MysqlDAO {

    private SQLiteDatabase database;
    private static MysqlDAO instance;
    ConnectMySQL mysql;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public static MysqlDAO getInstance(Context context) {
        if (instance == null) {
            instance = new MysqlDAO(context);
        }
        return instance;
    }

    private MysqlDAO(Context context) {
        DB_helper _DataHelper = DB_helper.getInstance(context);
        database = _DataHelper.getWritableDatabase();
        mysql = new ConnectMySQL();
    }


    public String getEstadoEdificioMysql(String Edificio) {
        String resp= null;
        String path;
        String sql = "select * from EDIFICIO where nombreEdificio = '" + Edificio + "'";

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Class.forName("com.mysql.jdbc.Driver");

            path = "jdbc:mysql://" + ConnectMySQL.MYSQL_IP + "/"
                    + ConnectMySQL.MYSQL_DBNAME + "?" + "user="
                    + ConnectMySQL.MYSQL_USERNAME + "&password="
                    + ConnectMySQL.MYSQL_PASSWORD;


            connect = (Connection) DriverManager.getConnection(path);
            statement = (Statement) connect.createStatement();
            resultSet = (ResultSet) statement.executeQuery(sql);

            while (resultSet.next()) {
                resp = resultSet.getString("estado");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resp;
    }

    public String getEstadoResultadoMapaMysql(String nombreEdificio, String tipo) {

        String resp= null;
        String path;
        String sql = "select SITIO.estado from EDIFICIO inner join SITIO on SITIO.idEdificio = EDIFICIO.idEdificio " +
                "where EDIFICIO.nombreEdificio = '" + nombreEdificio + "' and SITIO.tipo = '"+tipo+"'";



        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Class.forName("com.mysql.jdbc.Driver");

            path = "jdbc:mysql://" + ConnectMySQL.MYSQL_IP + "/"
                    + ConnectMySQL.MYSQL_DBNAME + "?" + "user="
                    + ConnectMySQL.MYSQL_USERNAME + "&password="
                    + ConnectMySQL.MYSQL_PASSWORD;


            connect = (Connection) DriverManager.getConnection(path);
            statement = (Statement) connect.createStatement();
            resultSet = (ResultSet) statement.executeQuery(sql);

            while (resultSet.next()) {
                resp = resultSet.getString("estado");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resp;

    }

    public String GetEstadoCampusMysql(String campus) {

        String resp= null;
        String path;


        String sql = "select nombrecampus,direccion,latitud,longitud,estado from CAMPUS where nombrecampus = '" + campus + "'";

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Class.forName("com.mysql.jdbc.Driver");

            path = "jdbc:mysql://" + ConnectMySQL.MYSQL_IP + "/"
                    + ConnectMySQL.MYSQL_DBNAME + "?" + "user="
                    + ConnectMySQL.MYSQL_USERNAME + "&password="
                    + ConnectMySQL.MYSQL_PASSWORD;


            connect = (Connection) DriverManager.getConnection(path);
            statement = (Statement) connect.createStatement();
            resultSet = (ResultSet) statement.executeQuery(sql);

            while (resultSet.next()) {
                resp = resultSet.getString("estado");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resp;
    }



    public String getEstadoFACULTADMysql(String FACULTAD) {
        String resp= null;
        String path;

            String sql = "select * from FACULTAD where nombreFacultad = '" + FACULTAD + "'";

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Class.forName("com.mysql.jdbc.Driver");

            path = "jdbc:mysql://" + ConnectMySQL.MYSQL_IP + "/"
                    + ConnectMySQL.MYSQL_DBNAME + "?" + "user="
                    + ConnectMySQL.MYSQL_USERNAME + "&password="
                    + ConnectMySQL.MYSQL_PASSWORD;


            connect = (Connection) DriverManager.getConnection(path);
            statement = (Statement) connect.createStatement();
            resultSet = (ResultSet) statement.executeQuery(sql);

            while (resultSet.next()) {
                resp = resultSet.getString("estado");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resp;
    }

    public String getDatos2(String nombreEdificio, String NombreSitio) {
        String resp= null;
        String path;
        String sql = "select EDIFICIO.estado from EDIFICIO inner join SITIO on SITIO.idEdificio = EDIFICIO.idEdificio" +
                " where EDIFICIO.nombreEdificio='" + nombreEdificio + "' and SITIO.NombreSitio='"+NombreSitio+"'";


        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Class.forName("com.mysql.jdbc.Driver");

            path = "jdbc:mysql://" + ConnectMySQL.MYSQL_IP + "/"
                    + ConnectMySQL.MYSQL_DBNAME + "?" + "user="
                    + ConnectMySQL.MYSQL_USERNAME + "&password="
                    + ConnectMySQL.MYSQL_PASSWORD;


            connect = (Connection) DriverManager.getConnection(path);
            statement = (Statement) connect.createStatement();
            resultSet = (ResultSet) statement.executeQuery(sql);

            while (resultSet.next()) {
                resp = resultSet.getString("estado");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resp;

    }


    public String getDatos3(String nombreEdificio, String NombreSitio) {

        String resp= null;
        String path;

        String sql = "select EDIFICIO.estado from SITIO " +
                "inner join EDIFICIO on EDIFICIO.idedificio = SITIO.idEdificio" +
                " where SITIO.NombreSitio='" + NombreSitio + "' and EDIFICIO.nombreEdificio='" + nombreEdificio + "'";

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Class.forName("com.mysql.jdbc.Driver");

            path = "jdbc:mysql://" + ConnectMySQL.MYSQL_IP + "/"
                    + ConnectMySQL.MYSQL_DBNAME + "?" + "user="
                    + ConnectMySQL.MYSQL_USERNAME + "&password="
                    + ConnectMySQL.MYSQL_PASSWORD;


            connect = (Connection) DriverManager.getConnection(path);
            statement = (Statement) connect.createStatement();
            resultSet = (ResultSet) statement.executeQuery(sql);

            while (resultSet.next()) {
                resp = resultSet.getString("estado");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resp;

    }

}
