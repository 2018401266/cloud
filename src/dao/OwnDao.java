package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * 从属信息数据库访问类
 * The Class OwnDao.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */
public class OwnDao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new own dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public OwnDao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}

	/**
	 * 保存相册和相片的从属信息
	 * Save own info.
	 *
	 * @param sql the sql
	 * @param albumId the album id
	 * @param imgId the img id
	 */
	
	public void saveOwnInfo(String sql, int albumId, int imgId) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumId);
			pstmt.setInt(2, imgId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新从属信息
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
	 * 根据SQL查询语句查询出来的从属信息
	 * Show own info.
	 *
	 * @param sql the sql
	 */
	
	public void showOwnInfo(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			int colCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colCount; i++)
					System.out.print(rs.getString(i));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除从属信息
	 * Delete own info.
	 *
	 * @param sql the sql
	 * @param id the id
	 */ 
	public void deleteOwnInfo(String sql, int id) {
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询用户信息，返回结果集
	 * Query own info.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	
	public ResultSet queryOwnInfo(String sql) {
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