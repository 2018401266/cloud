package test;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import dao.AlbumDao;

// TODO: Auto-generated Javadoc
/**
 * The Class AlbumDaoTest.
 *
 * @date 2020-7-4
 * @author buxinyi
 * @version  v1.0
 */
public class AlbumDaoTest {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The album dao. */
	private AlbumDao albumDao=new AlbumDao(driverName,uri);
	
	/**
	 * Test save album info.
	 */
	@Test
	public void testSaveAlbumInfo() {
		String sql = "insert into album(albumId,albumName,albumDesc,userId) values(?,?,?,?)";
		albumDao.saveAlbumInfo(sql, 2020006, "宫", "12", 1101);
		String sql2="select * from album";
		ResultSet rs=albumDao.queryAlbumInfo(sql2);
		int rowCount = 0;
		try {
			while(rs.next())
			{
				rowCount++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(7,rowCount);
	}
	
	/**
	 * Test delete.
	 */
	@Test
	public void testDelete() {
		//int aid=2020006;
		String sql="delete from album where albumId=?";
		String sql2="select * from album";
		albumDao.delete_5(sql, 2020006);
		ResultSet rs=albumDao.queryAlbumInfo(sql2);
		int rowCount = 0;
		try {
			while(rs.next())
			{
				rowCount++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(6,rowCount);
	}
	
	/**
	 * Test query album info.
	 */
	@Test
	public void testQueryAlbumInfo() {
		String sql="select * from album";
		ResultSet rs=albumDao.queryAlbumInfo(sql);
		int rowCount = 0;
		try {
			while(rs.next())
			{
				rowCount++;
			}
			assertEquals(6,rowCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test update user info.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testUpdateUserInfo() throws SQLException {
		int id; 
		String sql="update album set albumName=? ,albumDesc=? where albumId=?";
		albumDao.updateUserInfo(sql, "兜率宫", "456", 20200007);
		String sql2="select albumId from album where albumName='兜率宫'";
		ResultSet rs=albumDao.queryAlbumInfo(sql2);
		try {
			while(rs.next()) {
				id=rs.getInt(0);
				assertEquals(2020007,id);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}

}
