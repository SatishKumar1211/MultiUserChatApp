package com.messenger.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int counter;
	//construct
	public UserView() {
		counter = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);	//window cannot maximize
		setTitle("Login");
		setLocationRelativeTo(null);
		//setLocation(500,150);
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial", Font.BOLD,40));
		Container container = this.getContentPane();
		container.setLayout(null);	//to stop default layout of container
		welcome.setBounds(100, 70, 200, 60); 	//(x axis,y axis,width,height)	
		container.add(welcome);
		JButton button = new JButton("Count"); 	//source
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("Count"+counter++);
			}
		});
		button.setBounds(100,300,200,50);
		container.add(button);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		UserView userView = new UserView();
	}
}
