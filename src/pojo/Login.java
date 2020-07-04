package pojo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.*;

import dao.*;

// TODO: Auto-generated Javadoc
/**
 * 登录界面类
 * The Class Login.
 *
 * @date 2020-7-3
 * @author buxinyi
 * @version  v1.0
 */

public class Login {
	
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
	
	/** The lbl north. */
	private JLabel lblNorth;
	
	/** The pan center. */
	private JPanel panCenter;
	
	/** The lbl id. */
	private JLabel lblId;
	
	/** The txt id. */
	private JTextField txtId;
	
	/** The lbl password. */
	private JLabel lblPassword;
	
	/** The txt password. */
	private JPasswordField txtPassword;
	
	/** The pan south. */
	private JPanel panSouth;
	
	/** The btn ok. */
	private JButton btnOk;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The btn register. */
	private JButton btnRegister;

	/**
	 * Instantiates a new login.
	 */
	public Login() {
		frame = new JFrame("登录窗口");
		panNorth = new JPanel();
		lblNorth = new JLabel();
		lblNorth.setIcon(new ImageIcon("D:\\filedemo\\cover.png"));

		panCenter = new JPanel();
		lblId = new JLabel("账号：");
		txtId = new JTextField(20);
		lblPassword = new JLabel("密码：");
		txtPassword = new JPasswordField(20);

		panSouth = new JPanel();
		btnOk = new JButton("确定");
		btnCancel = new JButton("退出");
		btnRegister = new JButton("注册");

		lblId.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnCancel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnRegister.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		frame.setSize(360, 280);
		frame.setLocation(500, 150);
		frame.setResizable(false);

		frame.setLayout(new BorderLayout());
		panNorth.setLayout(new FlowLayout());
		panCenter.setLayout(new GridLayout(2, 2));
		panSouth.setLayout(new FlowLayout());

		frame.add(panNorth, BorderLayout.NORTH);
		frame.add(panCenter, BorderLayout.CENTER);
		frame.add(panSouth, BorderLayout.SOUTH);

		panNorth.add(lblNorth);
		panCenter.add(lblId);
		panCenter.add(txtId);
		panCenter.add(lblPassword);
		panCenter.add(txtPassword);
		panSouth.add(btnOk);
		panSouth.add(btnCancel);
		panSouth.add(btnRegister);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 确定按钮
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(txtId.getText().trim().equals("")||txtPassword.getPassword().equals(""))){
				int userId = Integer.parseInt(txtId.getText());
				String password = new String(txtPassword.getPassword());
				String sql = "select * from user where userId='" + userId + "'";
				ResultSet rs = userDB.queryUserInfo(sql);
				try {
						if (rs.next()) {
							if (rs.getString(3).equals(password)) {//登录成功
								new FuntionMenu(userId);
								frame.setVisible(false);
								} else {
									JOptionPane.showMessageDialog(null, "密码错误！\n  请重新输入");
									txtPassword.setText(null);
								}
							} else {
								JOptionPane.showMessageDialog(null, "账号不存在！\n  请重新输入");
								txtId.setText(null);
								txtPassword.setText(null);
							}
						} catch (HeadlessException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						} catch (SQLException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
				}else {
					JOptionPane.showMessageDialog(null, "输入不能为空！\n  请重新输入");
				}
			}
		});
		// 退出按钮
		btnCancel.addActionListener(new ActionListener() {

	@Override
			public void actionPerformed(ActionEvent e) {
		        userDB.closeAll();
				System.exit(0);
			}
		});
		// 注册按钮
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Register();
			}
		});
	}
	
	/**
	 * Clear all.
	 */
	public void clearAll() {
		userDB.closeAll();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new Login();
	}
}
