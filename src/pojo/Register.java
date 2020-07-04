package pojo;

import javax.swing.*;

import dao.UserDao;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * 用户注册类
 * The Class Register.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */

public class Register {
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The user DB. */
	UserDao userDB = new UserDao(driverName, uri);

	/** The frame. */
	private JFrame frame;
	
	/** The pan north. */
	private JPanel panNorth;
	
	/** The pan center. */
	private JPanel panCenter;
	
	/** The pan south. */
	private JPanel panSouth;
	
	/** The lbl icon. */
	private JLabel lblIcon;
	
	/** The lbl new user. */
	private JLabel lblNewUser;
	
	/** The txt new user. */
	private JTextField txtNewUser;
	
	/** The lbl password. */
	private JLabel lblPassword;
	
	/** The txt password. */
	private JPasswordField txtPassword;
	
	/** The lbl password 1. */
	private JLabel lblPassword1;
	
	/** The txt password 1. */
	private JPasswordField txtPassword1;
	
	/** The btn ok. */
	private JButton btnOk;
	
	/** The btn clear. */
	private JButton btnClear;

	/**
	 * Instantiates a new register.
	 */
	public Register() {
		frame = new JFrame("注册窗口");
		panNorth = new JPanel();
		panCenter = new JPanel();
		panSouth = new JPanel();
		lblIcon = new JLabel(new ImageIcon("D://filedemo//cover.png"));
		lblNewUser = new JLabel("输入用户名：");
		txtNewUser = new JTextField(20);
		lblPassword = new JLabel("输入密码：");
		txtPassword = new JPasswordField(20);
		lblPassword1 = new JLabel("重复输入密码：");
		txtPassword1 = new JPasswordField(20);
		btnOk = new JButton("确定");
		btnClear = new JButton("返回");

		lblNewUser.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblPassword1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnClear.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		frame.setSize(400, 300);
		frame.setLocation(600, 180);

		frame.setLayout(new BorderLayout());

		panCenter.setLayout(new GridLayout(3, 2));
		panSouth.setLayout(new FlowLayout());

		frame.add(panNorth, BorderLayout.NORTH);
		frame.add(panCenter, BorderLayout.CENTER);
		frame.add(panSouth, BorderLayout.SOUTH);

		panNorth.add(lblIcon);

		panCenter.add(lblNewUser);
		panCenter.add(txtNewUser);
		panCenter.add(lblPassword);
		panCenter.add(txtPassword);
		panCenter.add(lblPassword1);
		panCenter.add(txtPassword1);

		panSouth.add(btnOk);
		panSouth.add(btnClear);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 确定按钮
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkInfo(txtNewUser.getText(),
						new String(txtPassword.getPassword()),
								new String(txtPassword1.getPassword()))==true) {
					String user2 = txtNewUser.getText();
					String sql = "select * from user where userName='" + user2 + "'";
					ResultSet rs = userDB.queryUserInfo(sql);
					try {
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "用户名已存在，请重新输入");
							txtNewUser.setText("");
							txtPassword.setText("");
							txtPassword1.setText("");
						} else if (!(new String(txtPassword.getPassword())
								.equals(new String(txtPassword1.getPassword())))) {
							JOptionPane.showMessageDialog(null, "密码不一致，请重新输入密码");
							txtPassword.setText("");
							txtPassword1.setText("");
						} else {
							String sql2 = "insert into user values(?,?,?)";
							int userId3 = getNewId();
							String userName3 = txtNewUser.getText();
							String password3 = new String(txtPassword.getPassword());
							userDB.saveUserInfo(sql2, userId3, userName3, password3);
							int r = JOptionPane.showConfirmDialog(null, "注册成功！\n" 
							+ "您的账号为" + userId3, "确认",
									JOptionPane.YES_OPTION);
							if (r == JOptionPane.YES_OPTION) {
								frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
							}

							new Login();
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else {
					JOptionPane.showMessageDialog(null, "信息不合法，密码需大于六位");
				}
					
				

			}

		});
		// 返回按钮,返回登录界面
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userDB.closeAll();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	
	    /**
    	 * Check info.
    	 *
    	 * @param name the name
    	 * @param pw1 the pw 1
    	 * @param pw2 the pw 2
    	 * @return true, if successful
    	 */
	    
	public boolean checkInfo(String name,String pw1,String pw2) {
		if(name==null||pw1.length()>=6||pw2.length()>=6) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Gets the new id.
	 *
	 * @return the new id
	 */

	public int getNewId(){
		 		int ID = 1;	
		 		String sql = "select max(userId) from user";
		 			ResultSet rs = userDB.queryUserInfo(sql);
		 			try {
						if(rs.next()){
							ID = rs.getInt(1) + 1;//新的职工编号
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 		return ID;
	 	} 

}
