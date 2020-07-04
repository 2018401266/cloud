package pojo;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import dao.AlbumDao;
import dao.OwnDao;
import dao.UserDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import pojo.AlterAlbum;
import test.AlbumExcelUtil;

    // TODO: Auto-generated Javadoc
/**
     * The Class FuntionMenu.
     *功能菜单，可以选择账户管理和相册管理
     *
     * @date 2020-7-3
     * @author buxinyi
     * @version  v1.0
     */
    
public class FuntionMenu {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The album DB. */
	AlbumDao albumDB = new AlbumDao(driverName, uri);
	
	/** The own DB. */
	OwnDao ownDB=new OwnDao(driverName, uri);

	/** The jfc. */
	JFileChooser jfc = new JFileChooser();
	
	/** The aeu. */
	AlbumExcelUtil aeu;
	
	/** The frame. */
	private JFrame frame;
	
	/** The tab. */
	private JTabbedPane tab;
	
	/** The index. */
	int index = 0;
	
	/** The pan per. */
	// 第一个选项卡 账户管理
	private JPanel panPer;
	
	/** The lbl name. */
	private JLabel lblName;
	
	/** The lbl pwd. */
	private JLabel lblPwd;
	
	/** The lbl pwd 1. */
	private JLabel lblPwd1;
	
	/** The txt name. */
	private JTextField txtName;
	
	/** The txt pwd. */
	private JPasswordField txtPwd;
	
	/** The txt pwd 1. */
	private JPasswordField txtPwd1;
	
	/** The btn save. */
	private JButton btnSave;
	
	/** The btn back. */
	private JButton btnBack;
	
	/** The btn exit. */
	private JButton btnExit;
	
	/** The lbl tip. */
	private JLabel lblTip;
	
	/** The pan north. */
	private JPanel panNorth;
	
	/** The pan center. */
	private JPanel panCenter;
	
	/** The pan south. */
	private JPanel panSouth;

	/** The pan album. */
	// 第二个选项卡 相册管理
	private JPanel panAlbum;
	
	/** The pan new A. */
	private JPanel panNewA;
	
	/** The pan operation. */
	private JPanel panOperation;
	
	/** The tool bar. */
	private JToolBar toolBar;
	
	/** The btn new A. */
	private JButton btnNewA;
	
	/** The btn edit A. */
	private JButton btnEditA;
	
	/** The btn del A. */
	private JButton btnDelA;
	
	/** The lbl A name. */
	// 相册新建工具
	private JLabel lblAName;
	
	/** The lbl A desc. */
	private JLabel lblADesc;
	
	/** The txt A name. */
	private JTextField txtAName;
	
	/** The txt A desc. */
	private JTextField txtADesc;
	
	/** The btn create. */
	private JButton btnCreate;
	
	/** The pan edit A. */
	// 相册编辑工具
	private JPanel panEditA;
	
	/** The table. */
	private JTable table;
	
	/** The model. */
	DefaultTableModel model;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;

	/** The pan del A. */
	// 相册导入导出工具
	private JPanel panDelA;
	
	/** The btn album ex. */
	private JButton btnAlbumEx;
	
	/** The btn album im. */
	private JButton btnAlbumIm;
	
	/**
	 * Instantiates a new funtion menu.
	 *
	 * @param userId the user id
	 */
	public FuntionMenu(int userId) {
		frame=new JFrame();
		frame.setTitle("功能界面");
		frame.setLocation(250, 100);
		frame.setSize(500, 300);

		aeu=new AlbumExcelUtil(userId);
		tab = new JTabbedPane();
		// 账户管理界面
		panPer = new JPanel();
		panNorth = new JPanel();
		panCenter = new JPanel();
		panSouth = new JPanel();
		panCenter.setLayout(new GridLayout(3, 2));

		panPer.setLayout(new BorderLayout());
		panPer.add(panNorth, BorderLayout.NORTH);
		panPer.add(panCenter, BorderLayout.CENTER);
		panPer.add(panSouth, BorderLayout.SOUTH);

		String tip = "用户" +userId+ "，欢迎您！\n"
				+ "您可以在下面修改您的信息";
		lblTip = new JLabel(tip);

		lblName = new JLabel("用户名");
		lblPwd = new JLabel("新密码");
		lblPwd1 = new JLabel("确认新密码");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtPwd1 = new JPasswordField(20);
		btnExit = new JButton("退出登录");
		btnSave = new JButton("保存");
		btnBack = new JButton("注销账号");
		btnBack.setBorderPainted(false);

		panNorth.add(lblTip);
		panCenter.add(lblName);
		panCenter.add(txtName);
		panCenter.add(lblPwd);
		panCenter.add(txtPwd);
		panCenter.add(lblPwd1);
		panCenter.add(txtPwd1);

		panSouth.add(btnSave);
		panSouth.add(btnExit);
		panSouth.add(btnBack);

		lblTip.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblPwd.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblPwd1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnExit.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnSave.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnBack.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		// 相册管理界面
		panAlbum = new JPanel();// 相册管理公用面板
		panOperation = new JPanel();// 相册操作面板，在公用面板的中间
		panAlbum.setLayout(new BorderLayout());
		CardLayout c = new CardLayout();
		panOperation.setLayout(c);

		toolBar = new JToolBar(SwingConstants.VERTICAL);
		btnNewA = new JButton("新建相册");
		btnEditA = new JButton("相册编辑");
		btnDelA = new JButton("导入导出相册");

		btnNewA.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnEditA.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnDelA.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		// 相册新建面板
		panNewA = new JPanel();
		lblAName = new JLabel("相册名：");
		txtAName = new JTextField(20);
		lblADesc = new JLabel("相册描述:");
		txtADesc = new JTextField(20);
		btnCreate = new JButton("创建");

		lblAName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblADesc.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnCreate.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		lblAName.setSize(10, 10);
		txtAName.setSize(20, 10);
		lblADesc.setSize(10, 10);
		txtADesc.setSize(20, 20);
		panNewA.add(lblAName);
		panNewA.add(txtAName);
		panNewA.add(lblADesc);
		panNewA.add(txtADesc);
		panNewA.add(btnCreate);

		toolBar.add(btnNewA);
		toolBar.add(btnEditA);
		toolBar.add(btnDelA);

		panNewA.setVisible(true);
		// 相册编辑面板
		panEditA = new JPanel();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 4||column == 5||column == 6)// 第五列为修改操作,第六列为删除列,第七列为浏览图片列
					return true;
				else
					return false;
			}
		};
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 列宽不能改变
		panEditA.add(scrollPane);
		panEditA.setVisible(true);
		// 相册导入导出面板
		panDelA = new JPanel();
		btnAlbumEx = new JButton("导出相册");
		btnAlbumEx.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnAlbumIm = new JButton("导入相册");
		btnAlbumIm.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panDelA.add(btnAlbumEx);
		panDelA.add(btnAlbumIm);
		panDelA.setVisible(true);

		panOperation.add(panNewA, "1");
		panOperation.add(panEditA, "2");
		panOperation.add(panDelA, "3");

		panAlbum.add(toolBar, BorderLayout.WEST);
		panAlbum.add(panOperation, BorderLayout.CENTER);

		frame.add(tab);
		tab.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		tab.add("相册管理", panAlbum);
		tab.add("账号管理", panPer);
		
		frame.pack();
		frame.setVisible(true);
		// 相册管理按钮
		btnNewA.addActionListener(new ActionListener() {// 相册管理选项---新建相册按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) table.getModel()).getDataVector().clear();   //清除表格数据
				((DefaultTableModel) table.getModel()).fireTableDataChanged();//通知模型更新
				c.show(panOperation, "1");
			}
		});
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int c = table.getSelectedColumn();
				int row = table.getSelectedRow();
				if (c == 4) {//修改
					int albumId=(int) table.getValueAt(row, 0);
					panEditA.add(new AlterAlbum(albumId));
					Query(userId);
				}else if(c==5){
						int albumId = (int) table.getValueAt(row, 0);// 输入的相册编号
						String sqldel = "delete from album where albumId=?";
						String sqldel2="delete from own where albumId=?";
						int r = JOptionPane.showConfirmDialog(btnCreate, "确定删除该相册\n" 
						+ "相册编号为" + albumId, "确认",
									JOptionPane.YES_NO_OPTION);
								if (r == JOptionPane.YES_OPTION) {
									ownDB.deleteOwnInfo(sqldel2, albumId);
									albumDB.delete_5(sqldel, albumId);
									Query(userId);
								}
				}else if(c==6){
					    close();
						new ImgBroswer().init((int) table.getValueAt(row, 0),userId);
							}
					}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		btnEditA.addActionListener(new ActionListener() {// 相册管理选项---编辑相册按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(panOperation, "2");
				Query(userId);
			}
		});
		btnDelA.addActionListener(new ActionListener() {// 相册管理选项---导入导出相册按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) table.getModel()).getDataVector().clear();   //清除表格数据
				((DefaultTableModel) table.getModel()).fireTableDataChanged();//通知模型更新
				c.show(panOperation, "3");
			}

		});
		
		btnAlbumIm.addActionListener(new ActionListener() {// 导入按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {
					String path = jfc.getSelectedFile().getPath();
					aeu.importExcel(path);
					JOptionPane.showMessageDialog(null, "导入成功");
				}  
			}
		});
		
		btnAlbumEx.addActionListener(new ActionListener() {// 导出按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				aeu.exportExcel();
				JOptionPane.showMessageDialog(null, "导出成功");
			}
		});
		
		btnCreate.addActionListener(new ActionListener() {// 新建按钮相册---创建按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtAName.getText() != null) {
					String sql = "insert into album(albumId,albumName,albumDesc,userId) values(?,?,?,?)";
					int aid = getNewAlbumId();
					String aname = txtAName.getText();
					String adesc = txtADesc.getText();
					albumDB.saveAlbumInfo(sql, aid, aname, adesc, userId);
					JOptionPane.showConfirmDialog(btnCreate, "相册创建成功！\n" + "相册编号为"
					+ aid, "确认", JOptionPane.YES_OPTION);
					txtAName.setText(null);
					txtADesc.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "用户名不能为空，请重新输入");
				}
			}
		});
		// 账户管理按钮
		btnSave.addActionListener(new ActionListener() {// 账户管理选项--保存按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkInfo(txtName.getText(), new String(txtPwd.getPassword()),
						new String(txtPwd1.getPassword())) == true) {
					if (!(new String(txtPwd.getPassword()).equals(new String(txtPwd1.getPassword())))) {
						JOptionPane.showMessageDialog(null, "密码不一致，请重新输入密码");
						txtPwd.setText("");
						txtPwd1.setText("");
					} else {

						String userNewName = txtName.getText();
						String userNewPwd = new String(txtPwd.getPassword());
						String sql = "update user set userName=? ,userPwd=? where userId=?";
						UserDao userDB = new UserDao(driverName, uri);
						userDB.updateUserInfo(sql, userNewName, userNewPwd, userId);
						JOptionPane.showConfirmDialog(btnSave, "修改成功", "确认", JOptionPane.YES_OPTION);
						txtName.setText("");
						txtPwd.setText("");
						txtPwd1.setText("");
						userDB.closeAll();
					}

				} else {
					JOptionPane.showMessageDialog(null, "信息不合法，密码需大于六位");
				}
			}

		});
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 账户管理选项--销户按钮
				UserDao userDB = new UserDao(driverName, uri);
				int r = JOptionPane.showConfirmDialog(null, "确定注销账户？"
						+ "\n注销后所有信息都不会保存！", "确认",
						JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
				    String sqldel = "delete from user where userId=?";
					userDB.deleteUserInfo(sqldel, userId);
					JOptionPane.showMessageDialog(null, "您的账户已注销，后会有期");
					albumDB.closeAll();
					userDB.closeAll();
					System.exit(0);
				}
			}
		});
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 账户管理选项--返回按钮
				int r = JOptionPane.showConfirmDialog(null, "确定退出登录？", "确认",
						JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					frame.setVisible(false);
					new Login().clearAll();
				}
			}
		});
	}


	
	    /**
	     * 检查用户输入的修改信息是否合法
    	 * Check info.
    	 *
    	 * @param name the name
    	 * @param pw1 the pw 1
    	 * @param pw2 the pw 2
    	 * @return true, if successful
    	 */
	    
	public boolean checkInfo(String name, String pw1, String pw2) {
		if (name == null || pw1.length() >= 6 || pw2.length() >= 6) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成相册新帐号
	 * Gets the new album id.
	 *
	 * @return the new album id
	 */

	public int getNewAlbumId() {
		int ID = 1;
		String sql = "select max(albumId) from album";
		ResultSet rs = albumDB.queryAlbumInfo(sql);
		try {
			if (rs.next()) {
				ID = rs.getInt(1) + 1;// 新的相册编号
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ID;
	}
	
	
	    /**
	     * 查询该用户的相册信息
    	 * Query.
    	 *
    	 * @param userId the user id
    	 */
	    
	public void Query(int userId) {
		String sqlquery = "select albumId,albumName,albumDesc,albumNum "
				+ "from album where userId=?";
				ResultSet rs;
				ResultSetMetaData rsmd;
				try {
					rs = albumDB.queryAlbum(sqlquery, userId);
					Vector<String> title = new Vector<String>(); 
					title.add("相册编号");
					title.add("相册名称");
					title.add("相册描述");
					title.add("图片数");
					title.add("操作1");
					title.add("操作2");
					title.add("操作3");
					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
					int rowCount = 0;
					while (rs.next()) {
						rowCount++;
						Vector<Object> rowdata = new Vector<Object>();
						for (int i = 1; i <= 7; i++) {
							if(i==1) {
								rowdata.add(rs.getInt(i));
							}else if(i>1 && i<5) {
								rowdata.add(rs.getString(i));
							}else if(i==5) {
								rowdata.add("修改信息");
							}else if(i==6){
								rowdata.add("删除");
							}else {
								rowdata.add("浏览图片");
							}
						}
						data.add(rowdata);
					}
					model.setDataVector(data, title);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				};
	}
	
	/**
	 * Close.
	 */
	public void close() {
		frame.dispose();
		albumDB.closeAll();
		ownDB.closeAll();
	}
}
