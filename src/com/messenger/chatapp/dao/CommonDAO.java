//for creating connection

package com.messenger.chatapp.dao;

import static com.messenger.chatapp.utils.ConfigReader.getValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//throw early catch later
public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		// step1 - load driver
		Class.forName(getValue("Driver"));
		
		//step2 - making connection
		final String Connection_String = getValue("Connection_Url");
		String User_ID = getValue("UserID");
		String Password = getValue("Password");
		Connection con = DriverManager.getConnection(Connection_String, User_ID, Password);
		
		if(con != null) {	//to check connection
			System.out.println("Connection Created...");
			//con.close();
		}
		return con;
	}
	
}
