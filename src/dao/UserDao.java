package dao;

import java.sql.*;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDao.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */

public class UserDao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new user dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public UserDao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}

	/**
	 * 保存用户信息
	 * Save user info.
	 *
	 * @param sql the sql
	 * @param userId the user id
	 * @param userName the user name
	 * @param userPwd the user pwd
	 */
	
	public void saveUserInfo(String sql, int userId, String userName, String userPwd) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userPwd);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户信息
	 * Update user info.
	 *
	 * @param sql the sql
	 * @param userName the user name
	 * @param userPwd the user pwd
	 * @param id the id
	 */
	
	public void updateUserInfo(String sql, String userName, String userPwd, int id) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userPwd);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Delete user info.
	 *
	 * @param sql the sql
	 * @param id the id
	 */
	
	public void deleteUserInfo(String sql, int id) {
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Query user info.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	
	public ResultSet queryUserInfo(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return rs;
		}
	}

	/**
	 * Close all.
	 */

	public void closeAll() {
		try {
			if (!(rs == null)) {
				rs.close();
				//pstmt.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
