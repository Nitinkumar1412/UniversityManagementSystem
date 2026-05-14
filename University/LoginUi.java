package com.University;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.University.util.DBConnection;
import com.mysql.cj.protocol.Resultset;

public class LoginUi extends JFrame implements ActionListener
{
	JLabel Username, Password;
	JTextField tuser,tpass;
	JButton login,reset;
	
	public static void main(String[] args) 
	{
		LoginUi lui = new LoginUi();
		lui.setSize(800, 800);
		lui.setVisible(true);
		lui.setTitle("University Login");
		
	}

	public LoginUi()
	{
		setLayout(new FlowLayout());
		Username = new JLabel("Username");
		Password = new JLabel("password");
		tuser = new JTextField(50);
		tpass = new JTextField(50);
		login = new JButton("Login");
		reset = new JButton("Reset");
		add(Username);
		add(tuser);
		add(Password);
		add(tpass);
		add(login);
		add(reset);
		login.addActionListener(this);
		reset.addActionListener(this);
	}


	@Override
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(login))
		{
			String UserName = tuser.getText();
			String password = tpass.getText();
			
			try
			{
				Connection conn = DBConnection.getConnection();
				
				String query = "SELECT * FROM users WHERE email = ? and password = ?";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, UserName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null,"Successfully Login"+UserName);
				}
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		else if( e.getSource().equals(reset)) {
			tuser.setText("");
			tpass.setText("");
		}
		
	}
}
