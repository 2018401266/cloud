package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 相册数据库访问类
 * The Class AlbumDao.
 *
 * @date 2020-7-3
 * @author nieming
 * @version  v1.0
 */
public class AlbumDao {

	/** The pstmt. */

	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new album dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	
	public AlbumDao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}
    
	/**
	 * 保存相册信息
	 * Save album info.
	 *
	 * @param sql the sql
	 * @param albumId the album id
	 * @param albumName the album name
	 * @param albumDesc the album desc
	 * @param userId the user id
	 */
	public void saveAlbumInfo(String sql, int albumId, String albumName, String albumDesc, int userId) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumId);
			pstmt.setString(2, albumName);
			pstmt.setString(3, albumDesc);
			pstmt.setInt(4, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据SQL查询语句查询出来的相册信息
	 * Show alubm info.
	 *
	 * @param sql the sql
	 */
	
	public void showAlubmInfo(String sql) {
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
	 * 更新相册信息
	 * Update user info.
	 *
	 * @param sql the sql
	 * @param albumName the album name
	 * @param albumDesc the album desc
	 * @param albumId the album id
	 */

		public void updateUserInfo(String sql, String albumName, String albumDesc, int albumId) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, albumName);
				pstmt.setString(2, albumDesc);
				pstmt.setInt(3, albumId);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * 查询相册用户信息，返回结果集
	 * Query album info.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
		
	public ResultSet queryAlbumInfo(String sql) {
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
	 * Query album.
	 * 根据用户账号查询相册用户信息，返回结果集
	 * @param sql the sql
	 * @param userId the user id
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */

	public ResultSet queryAlbum(String sql, int userId) throws SQLException {
		if(conn.isClosed()) {//如果数据库连接关闭就不需要再进行查询了
			return null;
		}else {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, userId);
			rs = this.pstmt.executeQuery();
			return rs;
		}
			
	}

	/**
	 * Delete 5.
	 * 删除相册信息
	 * @param sql the sql
	 * @param aid the aid
	 */

	public void delete_5(String sql, int aid) {
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, aid);
			this.pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭资源
	 * Close all.
	 */
	public void closeAll() {
		try {
			if (!(rs == null)) {
				rs.close();
				pstmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
