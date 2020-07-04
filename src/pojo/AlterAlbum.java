package pojo;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.AlbumDao;


    // TODO: Auto-generated Javadoc
    /**
     * 修改相册描述和名称界面
     * The Class AlterAlbum.
     *
     * @date 2020-7-3
     * @author buxinyi
     * @version  v1.0
     */
    
public class AlterAlbum extends JPanel{
	
	/** The driver name. */
	String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=12345&useSSL=true";
	
	/** The album DB. */
	AlbumDao albumDB = new AlbumDao(driverName, uri);
	
	/** The lbl new album name. */
	private JLabel lblNewAlbumName;
	
	/** The txt new album name. */
	private JTextField txtNewAlbumName;
	
	/** The lbl new album desc. */
	private JLabel lblNewAlbumDesc;
	
	/** The txt new album desc. */
	private JTextField txtNewAlbumDesc;
	
	/** The btn ack. */
	private JButton btnAck;
	
	/** The btn out. */
	private JButton btnOut;
	
	/**
	 * Instantiates a new alter album.
	 *
	 * @param albumId the album id
	 */
	public AlterAlbum(int albumId) {
		this.setLayout(new GridLayout(3,2));
		lblNewAlbumName=new JLabel("新相册名：");
		txtNewAlbumName=new JTextField(10);
		lblNewAlbumDesc=new JLabel("新描述：");
		txtNewAlbumDesc=new JTextField(10);
		btnAck=new JButton("确认修改");
		btnOut=new JButton("返回");
		
		lblNewAlbumName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewAlbumDesc.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnAck.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOut.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		this.add(lblNewAlbumName);
		this.add(txtNewAlbumName);
		this.add(lblNewAlbumDesc);
		this.add(txtNewAlbumDesc);
		this.add(btnAck);
		this.add(btnOut);
		this.setVisible(true);
	
		btnAck.addActionListener(new ActionListener() {//修改相册信息

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtNewAlbumName.getText()!=null) {
					String albumNewName=txtNewAlbumName.getText();
					String albumNewDesc=txtNewAlbumDesc.getText();
					String sqlalter= "update album set albumName=? ,albumDesc=? where albumId=?";
					int r = JOptionPane.showConfirmDialog(null, "确定修改?", "确认",
									JOptionPane.OK_CANCEL_OPTION);
							if (r == JOptionPane.OK_OPTION)
								albumDB.updateUserInfo(sqlalter, albumNewName, albumNewDesc, albumId);
				                txtNewAlbumName.setText(null);
				                txtNewAlbumDesc.setText(null);
				 }else {
					JOptionPane.showMessageDialog(null, "新相册名不能为空！");
				 }
				
			}
		});
		btnOut.addActionListener(new ActionListener() {//返回上一个界面，即FuntionMenu界面

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		this.setVisible(false);
	}
}
