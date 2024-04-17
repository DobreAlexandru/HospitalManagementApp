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

public class InventarController implements Initializable{
	
	
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
	    private TableColumn<Inventar, String> col_tip;

	    @FXML
	    private TableColumn<Inventar, String> col_cantitate;

	    @FXML
	    private TableColumn<Inventar, Integer> col_id;

	    @FXML
	    private TableColumn<Inventar, String> col_data;
	    
	    @FXML
	    private TableColumn<Inventar, String> col_cost;

	    @FXML
	    private Pane pane;

	    @FXML
	    private TableView<Inventar> tbl_inventar;

	    @FXML
	    private TextField txt_tip;

	    @FXML
	    private TextField txt_cantitate;

	    @FXML
	    private TextField txt_data;
	    
	    @FXML
	    private TextField txt_cost;
	
	    private String query, tip, cantitate, data, cost;
	    
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
		
    	txt_tip.setText("");
    	txt_cantitate.setText("");
    	txt_data.setText("");
    	txt_cost.setText("");
	}
    
    private void saveAccount() {
		
		tip = txt_tip.getText();
		cantitate = txt_cantitate.getText();
		data = txt_data.getText();
		cost = txt_cost.getText();
		
		if (EDIT) {
		    query = "UPDATE inventar SET tip='" + tip + "', cantitate='" + cantitate + "', data='" + data + "', cost='" + cost + "' WHERE inventar_ID=" + ID + "";
		} else if (ADD) {
		    query = "INSERT INTO inventar (tip, cantitate, data, cost) VALUES ('" + tip + "', '" + cantitate + "', '" + data + "', '" + cost + "');";
		}
		
		if (query != null) {
		    dao.saveData(query);
		} else {
		    System.out.println("Șirul SQL este null.");
		}

		txt_tip.setText("");
    	txt_cantitate.setText("");
    	txt_data.setText("");
    	txt_cost.setText("");
		
		refreshTable();
	}

	private void refreshTable() {
		initTable();
		query = "SELECT a.tip, a.cantitate, a.data, a.cost, a.inventar_ID FROM inventar as a "
		         + "ORDER BY a.tip";

		tbl_inventar.setItems(dao.getInventarData(query));
	}
    
	private void initTable() {
		
		col_tip.setCellValueFactory(cell->cell.getValue().getpTip());;
		col_cantitate.setCellValueFactory(cell->cell.getValue().getpCantitate());;
		col_data.setCellValueFactory(cell->cell.getValue().getpData());;
		col_cost.setCellValueFactory(cell->cell.getValue().getpCost());;
		
	}
	
	private void delete(){
		
		Inventar selected = tbl_inventar.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		query="DELETE FROM inventar WHERE inventar_ID="+ID+"";
		dao.saveData(query);
		refreshTable();
	}
	
	private void editAccount() {
		Inventar selected = tbl_inventar.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		txt_tip.setText(selected.getpTip().get());
		txt_cantitate.setText(selected.getpCantitate().get());
		txt_data.setText(selected.getpData().get());
		txt_cost.setText(selected.getpCost().get());
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


