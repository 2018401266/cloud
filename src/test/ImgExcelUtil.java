package test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import dao.ImgDao;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.*;
// TODO: Auto-generated Javadoc

/**
 * 图片信息导出工具
 * The Class ImgExcelUtil.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */
  
public class ImgExcelUtil {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The img DB. */
	ImgDao imgDB = new ImgDao(driverName, uri);
	
	/**
	 * Export excel.
	 */
	@Test
	public void exportExcel() {
		WritableWorkbook workbook=null;
		try {
		File file=new File("D://imgexport.xls");
		workbook=Workbook.createWorkbook(file);
		WritableSheet sheet=workbook.createSheet("图片信息", 0);//创建导出的用户信息的sheet
		
		Label idlabel=new Label(0,0,"图片编号");
		Label namelabel=new Label(1,0,"图片名");
		Label addrlabel=new Label(2,0,"图片地址");
		
		sheet.addCell(idlabel);
		sheet.addCell(namelabel);
		sheet.addCell(addrlabel);
		
		String sql="select * from img";
		ResultSet Rs=imgDB.queryImgInfo(sql);
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
