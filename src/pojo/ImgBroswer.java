package pojo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.ImgDao;
import dao.OwnDao;
import test.ImgExcelUtil;
    

    // TODO: Auto-generated Javadoc
    /**
     * 图片浏览界面类
     * The Class ImgBroswer.
     *
     * @date 2020-7-3
     * @author buxinyi
     * @version  v1.0
     */
    
public class ImgBroswer {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The own DB. */
	OwnDao ownDB = new OwnDao(driverName, uri);
	
	/** The img DB. */
	ImgDao imgDB = new ImgDao(driverName, uri);

	/** The ieu. */
	ImgExcelUtil ieu=new ImgExcelUtil();
	
	/** The frame. */
	private JFrame frame = new JFrame("图片浏览器");
	
	/** The idlist. */
	ArrayList<Integer> idlist = new ArrayList<>();// 用于保存当前相册的图片id
	
	/** The addrlist. */
	ArrayList<String> addrlist = new ArrayList<>();// 用于保存当前相册的图片地址
	
	/** The namelist. */
	ArrayList<String> namelist = new ArrayList<>();// 用于保存当前相册的图片名
	
	/** The pathlist. */
	ArrayList<String> pathlist = new ArrayList<>();// 用于保存当前相册图片的路径

	/** The jfc. */
	JFileChooser jfc = new JFileChooser();
	
	/** The filter. */
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "gif","png");
	
	/** The path. */
	private String path;

	/** The pan. */
	private JPanel pan = new JPanel();
	
	/** The lbl. */
	private JLabel lbl = new JLabel();// 用于在frame中显示
	
	/** The lbl message. */
	private JLabel lblMessage = new JLabel();

	/** The tool bar. */
	private JToolBar toolBar = new JToolBar();
	
	/** The imgo. */
	String imgo = "D:\\filedemo\\edit.jpg";
	
	/** The iio. */
	ImageIcon iio = new ImageIcon(imgo);
	
	/** The btn add. */
	JButton btnAdd = new JButton(iio);

	/** The imgl. */
	String imgl = "D:\\filedemo\\l.png";
	
	/** The iil. */
	ImageIcon iil = new ImageIcon(imgl);
	
	/** The btn last. */
	JButton btnLast = new JButton(iil);

	/** The imgn. */
	String imgn = "D:\\filedemo\\r.png";
	
	/** The iin. */
	ImageIcon iin = new ImageIcon(imgn);
	
	/** The btn next. */
	JButton btnNext = new JButton(iin);

	/** The imgen. */
	String imgen = "D:\\filedemo\\en.png";
	
	/** The iien. */
	ImageIcon iien = new ImageIcon(imgen);
	
	/** The btnen. */
	JButton btnen = new JButton(iien);

	/** The imgsh. */
	String imgsh = "D:\\filedemo\\sh.png";
	
	/** The iish. */
	ImageIcon iish = new ImageIcon(imgsh);
	
	/** The btnsh. */
	JButton btnsh = new JButton(iish);

	/** The imghome. */
	String imghome = "D:\\filedemo\\home.png";
	
	/** The iih. */
	ImageIcon iih = new ImageIcon(imghome);
	
	/** The btnhome. */
	JButton btnhome = new JButton(iih);

	/** The imgdel. */
	String imgdel = "D:\\filedemo\\d.png";
	
	/** The iid. */
	ImageIcon iid = new ImageIcon(imgdel);
	
	/** The btndel. */
	JButton btndel = new JButton(iid);
	
	/** The imge. */
	String imge = "D:\\filedemo\\ex.jpg";
	
	/** The iie. */
	ImageIcon iie = new ImageIcon(imge);
	
	/** The btne. */
	JButton btne = new JButton(iie);
	
	/** The index. */
	int index = 0;
    
    /** The size. */
    int size;
	
	/**
	 * Inits the.
	 *
	 * @param albumId the album id
	 * @param userId the user id
	 */
	public void init(int albumId,int userId) {
		btnAdd.setToolTipText("添加本地图片");
		btnLast.setToolTipText("上一张");
		btnNext.setToolTipText("下一张");
		btnen.setToolTipText("放大");
		btnsh.setToolTipText("缩小");
		btnhome.setToolTipText("返回上一级");
		btndel.setToolTipText("删除");
		btne.setToolTipText("导出");
		toolBar.add(btnAdd);
		toolBar.add(btnLast);
		toolBar.add(btnNext);
		toolBar.add(btnen);
		toolBar.add(btnsh);
		toolBar.add(btndel);
		toolBar.add(btnhome);
		toolBar.add(btne);

		toolBar.setFloatable(false);// 不移动

		frame.setSize(800, 600);
		frame.setLocation(300, 100);
		frame.add(toolBar, BorderLayout.NORTH);
		frame.add(pan, BorderLayout.CENTER);

		pan.setLayout(new BorderLayout());
		pan.add(lblMessage, BorderLayout.NORTH);
		lblMessage.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		pan.add(lbl, BorderLayout.CENTER);
		lbl.setOpaque(true);
		frame.setVisible(true);

		String sqlinit = "select img.imgId,imgAddr,imgName from own,img" 
		+ " where own.imgId=img.imgId and"
				+ " albumId='" + albumId + "'";// 该相册的所有图片
		ResultSet rs = ownDB.queryOwnInfo(sqlinit);
		try {
			while (rs.next()) {
				idlist.add(rs.getInt("imgId"));
				addrlist.add(rs.getString("imgAddr"));
				namelist.add(rs.getString("imgName"));
			}
			for (int i = 0; i < addrlist.size(); i++) {
				pathlist.add(addrlist.get(i) + "\\" + namelist.get(i));// 存储图片地址
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		size = pathlist.size();
		if (size == 0) {
			lblMessage.setText("该相册没有图片");
			lbl.setIcon(null);
		} else {
			ImageIcon icon = new ImageIcon(pathlist.get(index));
			lbl.setIcon(icon);
			lblMessage.setText(namelist.get(index) + " --- 第" + 1 + "/" + size + "张");
		}

		btnAdd.addActionListener(new ActionListener() {// 增加图片按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				jfc.setFileFilter(filter);
				if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {
					path = jfc.getSelectedFile().getPath();
					int i = path.lastIndexOf("\\");
					int id = getNewImgId();
					String addr = path.substring(0, i + 1);
					String name = path.substring(i + 1);
					String sqladd = "insert into img values(?,?,?)";
					String sqladd2 = "insert into own values(?,?)";
					imgDB.saveImgInfo(sqladd, id, name, addr);
					int r = JOptionPane.showConfirmDialog(null, "确定添加该图片到相册"
					+ albumId + "中？", "确认",
							JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.YES_OPTION) {
						ownDB.saveOwnInfo(sqladd2, albumId, id);
					}
					JOptionPane.showMessageDialog(null, "添加成功");
					idlist.clear();
					namelist.clear();
					addrlist.clear();
					pathlist.clear();
					String sqlinit = "select img.imgId,imgAddr,imgName from own,img" 
							+ " where own.imgId=img.imgId and"
										+ " albumId='" + albumId + "'";// 该相册的所有图片
					ResultSet rs = ownDB.queryOwnInfo(sqlinit);
					try {
						while (rs.next()) {
							idlist.add(rs.getInt("imgId"));
							addrlist.add(rs.getString("imgAddr"));
							namelist.add(rs.getString("imgName"));
						}
						for (int ii = 0; ii < addrlist.size(); ii++) {
							pathlist.add(addrlist.get(ii) + "\\"
						+ namelist.get(ii));// 存储图片地址
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					size = pathlist.size();
					index=0;
					if(size>0) {
						ImageIcon icon = new ImageIcon(pathlist.get(index));
					    lbl.setIcon(icon);
					    lblMessage.setText(namelist.get(index) + " --- 第" 
					    + 1 + "/" + size + "张");
					}
					
				}
			}
		});

		btnLast.addActionListener(new ActionListener() {// 上一张

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (index == 0) {
					JOptionPane.showMessageDialog(null, "当前为第一张图片");
				} else if (index > 0) {
					index--;
					ImageIcon icon = new ImageIcon(pathlist.get(index));
					lbl.setIcon(icon);
					lblMessage.setText(namelist.get(index) + "." + " --- 第" + (index + 1) + "/" + size + "张");
				}

			}

		});
		
		btnNext.addActionListener(new ActionListener() {// 下一张

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (index == size - 1) {
					JOptionPane.showMessageDialog(null, "当前为最后一张图片");
				} else if (index < size - 1) {
					index++;
					ImageIcon icon = new ImageIcon(pathlist.get(index));
					lbl.setIcon(icon);
					lblMessage.setText(namelist.get(index) + " --- 第" + (index + 1) + "/" + size + "张");
				}
			}

		});
		
		btnen.addActionListener(new ActionListener() {// 放大

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon img = (ImageIcon) (lbl.getIcon());
				if (img != null) {
					int width = img.getIconWidth() * 2;
					int height = img.getIconHeight() * 2;
					ImageIcon newimg = new ImageIcon(
							img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
					lbl.setIcon(newimg);
				} else {
					return;
				}
			}

		});

		btnsh.addActionListener(new ActionListener() {// 缩小
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon img = (ImageIcon) (lbl.getIcon());
				if (img != null) {
					int width = img.getIconWidth() / 2;
					int height = img.getIconHeight() / 2;
					ImageIcon newimg = new ImageIcon(
							img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
					lbl.setIcon(newimg);
				} else {
					return;
				}
			}

		});

		btnhome.addActionListener(new ActionListener() {// 返回

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				idlist.clear();
				namelist.clear();
				addrlist.clear();
				pathlist.clear();
				ownDB.closeAll();
				imgDB.closeAll();
				new FuntionMenu(userId);
			}

		});
		
		btne.addActionListener(new ActionListener() {// 导出

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ieu.exportExcel();
				JOptionPane.showMessageDialog(null, "图片信息导出成功！");
			}

		});

		btndel.addActionListener(new ActionListener() {// 将该图片从数据库中删除

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sqldel = "delete from own where albumId+'" + albumId + "' and imgId=?";
				String sqldel2 = "delete from img where img.imgId=?";
				int r = JOptionPane.showConfirmDialog(null, "确认删除该图片?\n源文件也会被删除！",
						"确认", JOptionPane.OK_CANCEL_OPTION);
				if (r == JOptionPane.OK_OPTION) {
					ownDB.deleteOwnInfo(sqldel, idlist.get(index));
					imgDB.deleteImgInfo(sqldel2, idlist.get(index));
				}
				JOptionPane.showMessageDialog(null, "删除成功");
				idlist.clear();
				namelist.clear();
				addrlist.clear();
				pathlist.clear();
				String sqlinit = "select img.imgId,imgAddr,imgName from own,img" 
			+ " where own.imgId=img.imgId and"
						+ " albumId='" + albumId + "'";// 该相册的所有图片
				ResultSet rs = ownDB.queryOwnInfo(sqlinit);
				try {
					while (rs.next()) {
						idlist.add(rs.getInt("imgId"));
						addrlist.add(rs.getString("imgAddr"));
						namelist.add(rs.getString("imgName"));
					}
					for (int i = 0; i < addrlist.size(); i++) {
						pathlist.add(addrlist.get(i) + "\\" + namelist.get(i));// 存储图片地址
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				size = pathlist.size();
				index=0;
				if (size == 0) {
					lblMessage.setText("该相册没有图片");
					lbl.setIcon(null);
				} else {
					ImageIcon icon = new ImageIcon(pathlist.get(index));
					lbl.setIcon(icon);
					lblMessage.setText(namelist.get(index) + " --- 第" + 1 + "/" + size + "张");
				}
				
			}

		});

	}


	/**
	 * Gets the new img id.
	 *
	 * @return the new img id
	 */

	public int getNewImgId() {
		int ID = 1;
		String sql = "select max(imgId) from img";
		ResultSet rs = imgDB.queryImgInfo(sql);
		try {
			if (rs.next()) {
				ID = rs.getInt(1) + 1;// 新的图片id
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ID;
	}

}
