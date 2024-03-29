package javaSwing_JDBC2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class JFrame1 extends JFrame
{
	
	private JPanel contentPane;
	private JTextField txtname;
	
	
	Connection con;
	private JPasswordField txtpass;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					JFrame1 frame = new JFrame1();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	
	
	
	public JFrame1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 512, 343);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 49, 72, 25);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 98, 72, 25);
		panel.add(lblPassword);
		
		txtname = new JTextField();
		txtname.setBounds(97, 51, 269, 23);
		panel.add(txtname);
		txtname.setColumns(10);
		
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(97, 184, 108, 33);
		panel.add(btnRegister);
		//Tao su kien cho nut Register
		btnRegister.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				try
				{
					con = new MyConnect().getcn();
					if(con==null)
					{
						JOptionPane.showMessageDialog(getParent(), "Kết nối SQL thất bại","Thông Báo",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Kết nối SQL Thành Công","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
					}
					
					//Kiểm tra username có tồn tại trước đó chưa (không được trùng Username)
					
					String user = txtname.getText();
					String sql = "Select * From admin where username like ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1,user);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(getParent(), "Username đã tồn tại","Thông Báo",JOptionPane.ERROR_MESSAGE);
						return;
					}		
					//Lấy password
					String strpass = new String(txtpass.getPassword());
					//Mã hoá password bằng thuật toán SHA-1
					MessageDigest md = MessageDigest.getInstance("SHA-1");
					byte []bytepass = md.digest(strpass.getBytes());
					//Insert account mới (Username, Password) vào database
					sql = "Insert Into admin values (?,?)";
					ps = con.prepareStatement(sql);
					
					ps.setString(1,user);
					ps.setString(2,new String(bytepass));//Convert(chuyển đổi) mảng byte về string
					int kq = ps.executeUpdate();
					if(kq == 1)
					{
						JOptionPane.showMessageDialog(getParent(), "Register SUCCESS","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Fail","Thông Báo",JOptionPane.ERROR_MESSAGE);
					}
					
					//Đóng tất cả các kết nối
					ps.close();
					con.close();
					
				}//TRY
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(NoSuchAlgorithmException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		});
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(215, 184, 108, 33);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				con = new MyConnect().getcn();
				if(con==null)
				{
					JOptionPane.showMessageDialog(getParent(), "Login Fail","Thông Báo",JOptionPane.ERROR_MESSAGE);
				}
				
				try
				{
					String user = txtname.getText();
					String strpass = new String(txtpass.getPassword());
					
					MessageDigest md = MessageDigest.getInstance("SHA-1");
					byte[] bytepass = md.digest(strpass.getBytes());
					
					//Sau khi mã hoá,chuyển ngược array byte về String để chuẩn bị so sánh với chuỗi String password lấy từ database
					String pass = new String(bytepass);
					
					//Lấy data theo username vừa login
					String sql = "Select * From admin where username like ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1,user);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						if(pass.equals(rs.getString(2)))
						{
							JOptionPane.showMessageDialog(getParent(), "Login Success","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
							//Tạo đối tượng từ JFrame2 --> để điều khiển mở JFrame2 lên
							JFrame2 abc = new JFrame2(user);
							abc.setVisible(true);
							dispose();//ẩn JFrame hiện hành
						}
						
						else
						{
							JOptionPane.showMessageDialog(getParent(), "Login Fail","Thông Báo",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Username không tồn tại","Thông Báo",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(NoSuchAlgorithmException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		});
		
		
		txtpass = new JPasswordField();
		txtpass.setBounds(97, 100, 269, 23);
		panel.add(txtpass);
	}
}
