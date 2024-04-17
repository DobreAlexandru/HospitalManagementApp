package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	


	private final String dburl = "jdbc:mysql://localhost:3306/spital"; 
	private final String username = "root";
	private final String password = "";	
	private Connection connect;
	
	public DBConnection() {
			
	}
	
	public Connection getConnection() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connect = DriverManager.getConnection(dburl, username, password);
	        System.out.println("Conexiune la bază de date stabilită cu succes!");
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        System.out.println("Eroare la stabilirea conexiunii la bază de date: " + e.getMessage());
	    }
	    return connect;
	}


	
	public void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(connect != null)
				connect.close();
			if(pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void close(Connection connect, PreparedStatement pstmt) {
		try {
			close(connect, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();}
	}
	
	public void close(PreparedStatement pstmt) {
		try {
			close(null, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();}
	}
	
}
