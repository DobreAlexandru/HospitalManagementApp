package application;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CamereController implements Initializable{
	
	
	  	private static final boolean FALSE = false;

		@FXML
	    private Button btn_add_new;

	    @FXML
	    private Button btn_back;

	    @FXML
	    private Button btn_delete;

	    @FXML
	    private Button btn_edit;

	    @FXML
	    private Button btn_save;

	    @FXML
	    private TableColumn<Camere, String> col_nr;

	    @FXML
	    private TableColumn<Camere, String> col_stare;

	    @FXML
	    private TableColumn<Camere, Integer> col_id;

	    @FXML
	    private TableColumn<Camere, String> col_pacient;

	    @FXML
	    private Pane pane;

	    @FXML
	    private TableView<Camere> tbl_camere;

	    @FXML
	    private TextField txt_nr;

	    @FXML
	    private TextField txt_stare;

	    @FXML
	    private TextField txt_pacient;
	
	    private String query, nr, stare, pacient;
	    
	    private Map<String, Object> map;
	    private boolean EDIT=false, ADD=false;
	    private int ID;    
	    private FXMLLoader loader;
	    private DataAccessObject dao = new DataAccessObject();
	    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    	refreshTable();
    	
    	btn_back.setOnAction(e->{
			showMenu();
		});
    	
    	btn_add_new.setOnAction(e->{
			EDIT = false;
			ADD = true;
			insertNewAccount();	
		});
    	
    	btn_save.setOnAction(e->{
			saveAccount();
		});
    	
    	btn_delete.setOnAction(e->{
    		delete();
		});
    	
    	btn_edit.setOnAction(e->{
			ADD = false;
			EDIT = true;
			editAccount();
			
		});

    }
    private Stage stage; 

    private void insertNewAccount() {
		
    	txt_nr.setText("");
    	txt_stare.setText("");
    	txt_pacient.setText("");
		
	}
    
    private void saveAccount() {
		
		nr = txt_nr.getText();
		stare = txt_stare.getText();
		pacient = txt_pacient.getText();
		
		if (EDIT) {
		    query = "UPDATE camere SET numar='" + nr + "', stare='" + stare + "', pacient='" + pacient + "' WHERE camera_ID=" + ID + "";
		} else if (ADD) {
		    query = "INSERT INTO camere (numar, stare, pacient) VALUES ('" + nr + "', '" + stare + "', '" + pacient + "');";
		}
		
		if (query != null) {
		    dao.saveData(query);
		} else {
		    System.out.println("Șirul SQL este null.");
		}

		txt_nr.setText("");
    	txt_stare.setText("");
    	txt_pacient.setText("");
		
		refreshTable();
	}

	private void refreshTable() {
		initTable();
		query = "SELECT a.numar, a.stare, a.pacient, a.camera_ID FROM camere as a "
		         + "ORDER BY a.numar";

		tbl_camere.setItems(dao.getCamereData(query));
	}
    
	private void initTable() {
		
		col_nr.setCellValueFactory(cell->cell.getValue().getpNumar());;
		col_stare.setCellValueFactory(cell->cell.getValue().getpStare());;
		col_pacient.setCellValueFactory(cell->cell.getValue().getpPacient());;
		
	}
	
	private void delete(){
		
		Camere selected = tbl_camere.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		query="DELETE FROM camere WHERE camera_ID="+ID+"";
		dao.saveData(query);
		refreshTable();
	}
	
	private void editAccount() {
		Camere selected = tbl_camere.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		txt_nr.setText(selected.getpNumar().get());
		txt_stare.setText(selected.getpStare().get());
		txt_pacient.setText(selected.getpPacient().get());
	}
	
	private void showMenu() {
	    try {
	        loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Menu.fxml"));
	        MenuController controller = new MenuController();
	        loader.setController(controller);
	        loader.load();
	        Scene scene = new Scene(loader.getRoot());
	        scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
	        Stage menuStage = new Stage();
	        menuStage.setScene(scene);
	        menuStage.setResizable(false);
	        Image icon = new Image(getClass().getResourceAsStream("cfr.png"));
	        
	        menuStage.getIcons().add(icon);
	        menuStage.show();

	        Stage currentStage = (Stage) btn_back.getScene().getWindow();
	        currentStage.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}


