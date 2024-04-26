// perform CRUD on user

package com.messenger.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.messenger.chatapp.dto.UserDTO;
import com.messenger.chatapp.utils.Encryption;

public class UserDAO {
	//to check login
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con= null;
		PreparedStatement pstmt = null; 	
		ResultSet rs = null;
		final String SQL = "select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs = pstmt.executeQuery();
			
			return rs.next();
//			if(rs.next()) {		//checks has a row come?
//				return true;
//			}
//			else {
//				return false;
//			}	
		}
		finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	//ad data to database during Register
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception{
		System.out.println("Received "+userDTO.getUserid()+" "+userDTO.getPassword());
		//put in database
		Connection connection = null;
		//query object
		Statement stmt = null;
		
		try {	//guarded region - exception can occur
			connection = CommonDAO.createConnection(); 		//connection created
			//to enable to use query
			stmt = connection.createStatement();
			//Insect into users (userid,password) values('satish','1234');
			int record = stmt.executeUpdate("insert into users (userid,password) Values('" + userDTO.getUserid()+"','" + Encryption.passwordEncrypt(new String(userDTO.getPassword())) + "')");	//insert,delete,update
			//if recorded successfully then record=1
			return record;
		}
		
		finally {	//always execute except system.exit(0)
			if(stmt != null) {
				stmt.close();
			}
			if (connection != null){
				connection.close();
			}
		}

	}
}
