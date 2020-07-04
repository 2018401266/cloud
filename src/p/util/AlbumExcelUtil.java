package p.util;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import dao.AlbumDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.*;


    // TODO: Auto-generated Javadoc
/**
 * 相册信息导入导出工具类
     * The Class AlbumExcelUtil.
     *
     * @date 2020-7-3
     * @author buxinyi
     * @version  v1.0
     */
    
public class AlbumExcelUtil {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The album DB. */
	AlbumDao albumDB = new AlbumDao(driverName, uri);
	
	/** The user id. */
	private int userId;
	
	/**
	 * Instantiates a new album excel util.
	 *
	 * @param userId the user id
	 */
	public AlbumExcelUtil(int userId) {
		this.userId=userId;
	}
	
	/**
	 * Import excel.
	 *
	 * @param path the path
	 */
	public void importExcel(String path) {
		Workbook workbook = null;
		File file=new File(path);
		try {
			workbook=Workbook.getWorkbook(file);
			Sheet sheet=workbook.getSheet(0);
			int rows=sheet.getRows();

			for(int i=1;i<rows;i++) {//省去列标题行
				Cell IdCell=sheet.getCell(0,i);
				Cell NameCell=sheet.getCell(1,i);
				Cell DescCell=sheet.getCell(2,i);
				String Idstr=IdCell.getContents();
				String NameAll=NameCell.getContents();
				String DescAll=DescCell.getContents();
				
				int IdAll=Integer.valueOf(Idstr);
				String sql="insert into album (albumId,albumName,albumDesc,userId) values(?,?,?,?)";
				albumDB.saveAlbumInfo(sql, IdAll, NameAll, DescAll,this.userId);
			}
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
	}
	
	/**
	 * Export excel.
	 */
	public void exportExcel() {
		WritableWorkbook workbook=null;
		try {
		File file=new File("D://"+this.userId+"export.xls");
		workbook=Workbook.createWorkbook(file);
		WritableSheet sheet=workbook.createSheet("相册信息", 0);//创建导出的用户信息的sheet
		
		Label ailabel=new Label(0,0,"相册编号");
		Label anlabel=new Label(1,0,"相册名");
		Label adlabel=new Label(2,0,"相册描述");
		
		sheet.addCell(ailabel);
		sheet.addCell(anlabel);
		sheet.addCell(adlabel);
		
		String sql="select * from album where userId='"+this.userId+"'";
		ResultSet Rs=albumDB.queryAlbumInfo(sql);
		int rowNum=1;
		while(Rs.next()) {
			Label outId=new Label(0,rowNum,String.valueOf(Rs.getInt(1)));
			Label outName=new Label(1,rowNum,Rs.getString(2));
			Label outDesc=new Label(2,rowNum,Rs.getString(3));
			
			sheet.addCell(outId);
			sheet.addCell(outName);
			sheet.addCell(outDesc);
			rowNum++;
		}
		workbook.write();
		Rs.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
