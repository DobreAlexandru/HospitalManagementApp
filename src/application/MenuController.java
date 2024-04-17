package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

	private static final boolean FALSE = false;

	@FXML
	private Button btn_personal;
	
	@FXML
	private Button btn_pacienti;
	
	@FXML
	private Button btn_camere;
	
	@FXML
	private Button btn_inventar;
	
	private FXMLLoader loader;
	
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	btn_personal.setOnAction(e->{
			showPersonal();
		});
    	
    	btn_pacienti.setOnAction(e->{
			showPacienti();
		});
    	
    	btn_camere.setOnAction(e->{
			showCamere();
		});
    	
    	btn_inventar.setOnAction(e->{
			showInventar();
		});
    }
    
   private void showPersonal() {
	   try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Personal.fxml"));
			PersonalController controller = new PersonalController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("personal.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(FALSE);
			Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
			stage.getIcons().add(icon);
			stage.show();
			
			Stage currentStage = (Stage) btn_personal.getScene().getWindow();
			currentStage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
   }
   
   private void showPacienti() {
	   try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Pacienti.fxml"));
			PacientiController controller = new PacientiController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("personal.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(FALSE);
			Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
			stage.getIcons().add(icon);
			stage.show();
			
			Stage currentStage = (Stage) btn_pacienti.getScene().getWindow();
			currentStage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
   }
   
   private void showCamere() {
	   try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Camere.fxml"));
			CamereController controller = new CamereController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("personal.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(FALSE);
			Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
			stage.getIcons().add(icon);
			stage.show();
			
			Stage currentStage = (Stage) btn_camere.getScene().getWindow();
			currentStage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
   }
   
   private void showInventar() {
	   try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Inventar.fxml"));
			InventarController controller = new InventarController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("personal.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(FALSE);
			Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
			stage.getIcons().add(icon);
			stage.show();
			
			Stage currentStage = (Stage) btn_inventar.getScene().getWindow();
			currentStage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
   }
}
