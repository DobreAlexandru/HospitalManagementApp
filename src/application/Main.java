package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private FXMLLoader loader;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		
		
		try {
			
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Log.fxml"));
			LoginController controller = new LoginController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Log In");
			 Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
			  primaryStage.getIcons().add(icon);

			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}