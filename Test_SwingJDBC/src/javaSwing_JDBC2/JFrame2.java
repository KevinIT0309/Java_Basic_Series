package javaSwing_JDBC2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;

public class JFrame2 extends JFrame
{

	private JPanel contentPane;
	private static JFrame frame;
	private String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new JFrame();
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
	
	
	
	
	
	public JFrame2(String userlogin)
	{
		this.username = userlogin;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHello = new JLabel("Hello");
		lblHello.setForeground(Color.BLUE);
		lblHello.setBounds(140, 11, 147, 38);
		contentPane.add(lblHello);
		lblHello.setText(lblHello.getText()+" "+username);
	}
}
