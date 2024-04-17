package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccessObject {

    private DBConnection database = new DBConnection();

    public boolean checkLogin(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = database.getConnection();
            String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            database.close(connection, preparedStatement, resultSet);
        }
    }
    
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Connection connect;
    
    public void saveData(String query) {
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			database.close(connect, pstmt, null);
		}
			
	}
    
    public ObservableList<Personal> getAccountsData(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Personal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    
    public ObservableList<Pacienti> getPacientiData(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Pacienti(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    
    public ObservableList<Camere> getCamereData(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Camere(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
    public ObservableList<Inventar> getInventarData(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Inventar(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
