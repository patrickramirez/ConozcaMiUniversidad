package Utils;

/**
 * Connect to api.
 *
 * @author Isken Huang (c) 2011
 * @version 1.4.0 
 */

import java.sql.DriverManager;
import java.util.ArrayList;

import android.util.Log;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class ConnectMySQL
{
	private final String PAGETAG = "ConnectMySQL";
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public static final String MYSQL_IP = "patrickdeila.no-ip.info";
	public static final String MYSQL_DBNAME = "cmu";
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "furelise";
	
	
	
	/* close connect */
	private void close()
	{
		try
		{
			if (resultSet != null)
			{
				resultSet.close();
			}
			
			if (statement != null)
			{
				statement.close();
			}
			
			if (connect != null)
			{
				connect.close();
			}
		}
		catch (Exception e)
		{
			
		}
	}
	
	
}
