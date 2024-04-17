package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	private static final boolean FALSE = false;

	@FXML
	private Button btn_login;
	
	@FXML
	private PasswordField txt_password;

	@FXML
    private TextField txt_username;
	
	private FXMLLoader loader;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		btn_login.setOnAction(e->{
			tryLog();
		});
		
	}

	  private void tryLog() {
	        String username = txt_username.getText();
	        String password = txt_password.getText();

	        DataAccessObject dataAccessObject = new DataAccessObject();
			if (dataAccessObject.checkLogin(username, password)) {
	            showMenu();
	        } 
	    }
	
	private void showMenu(){
		try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Menu.fxml"));
			MenuController controller = new MenuController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			
			Stage stage = new Stage();
			scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(FALSE);
			Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
			stage.getIcons().add(icon);
			
			stage.show();
			
			Stage currentStage = (Stage) btn_login.getScene().getWindow();
			currentStage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

