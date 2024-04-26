package com.messenger.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.messenger.chatapp.dao.UserDAO;
import com.messenger.chatapp.dto.UserDTO;
import com.messenger.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	private JTextField useridtxt;
	private JPasswordField pwdtxt;

	
	public static void main(String[] args) {
		UserScreen window = new UserScreen();
					
	}
	
	UserDAO userDAO = new UserDAO();
	//LOGIN
	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password = pwdtxt.getPassword();		//className@hashCode	
		
		UserDTO userDTO = new UserDTO(userid,password);
		try {
			String message = "";
			if(userDAO.isLogin(userDTO)) {
				message = "Welcome "+userid;
				UserInfo.User_Name= userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message = "Invalid Userid or password";
				JOptionPane.showMessageDialog(this, message);
			}
			//JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//REGISTER
	private void register() {
		String userid = useridtxt.getText();
		char[] password = pwdtxt.getPassword();		//className@hashCode	
		//UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid,password);
		try {
			int result = userDAO.add(userDTO);		//to check data added or not
			if (result > 0) {
				JOptionPane.showMessageDialog(this, "Registered Successfully"); 	//to show dialog box
				//System.out.println("Record Added....");
			}
			else {
				JOptionPane.showMessageDialog(this, "Register Failed");
				//System.out.println("Record not Added....");
			}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB issue....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some generic Exception Raised...");
			ex.printStackTrace();	//where is the exception
		}
		System.out.println(userid+ password);
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(206, 66, 187, 77);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(188, 203, 353, 43);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("User ID");
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		useridlbl.setBounds(51, 200, 81, 43);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdlbl.setBounds(51, 274, 101, 43);
		getContentPane().add(pwdlbl);
		
		pwdtxt = new JPasswordField();
		pwdtxt.setBounds(191, 277, 350, 43);
		getContentPane().add(pwdtxt);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginbt.setBounds(135, 379, 128, 43);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerbt.setBounds(359, 379, 128, 43);
		getContentPane().add(registerbt);
		setBackground(Color.WHITE);
		setSize(638,593);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
}
