package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * 图片数据库访问类
 * The Class ImgDao.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */
public class ImgDao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new img dao.
	 *
	 * @param driverName the driver name
	 * @param uri the uri
	 */
	public ImgDao(String driverName, String uri) {
		conn = DBConnection.getConnection(driverName, uri);
	}

	/**
	 * 上传图片信息
	 * Save img info.
	 *
	 * @param sql the sql
	 * @param Id the id
	 * @param Name the name
	 * @param addr the addr
	 */

	public void saveImgInfo(String sql, int Id, String Name, String addr) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id);
			pstmt.setString(2, Name);
			pstmt.setString(3, addr);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update img info.
	 *
	 * @param sql the sql
	 */
	// 更新图片信息
	public void updateImgInfo(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除图片信息
	 * Delete img info.
	 *
	 * @param sql the sql
	 * @param id the id
	 */
	
	public void deleteImgInfo(String sql, int id) {
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询图片信息，返回结果集
	 * Query img info.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	 
	public ResultSet queryImgInfo(String sql) {
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
	 * 关闭资源
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
