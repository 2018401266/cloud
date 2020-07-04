package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * 数据库连接类
 * The Class DBConnection.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */

public class DBConnection {
	
	/**
	 * Gets the connection.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 * @return the connection
	 */
	public static Connection getConnection(String driverName, String uri) {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(uri);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}
