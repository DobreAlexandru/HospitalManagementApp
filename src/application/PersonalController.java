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

public class PersonalController implements Initializable{
	
	
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
	    private TableColumn<Personal, String> colpersonal_adresa;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_email;

	    @FXML
	    private TableColumn<Personal, Integer> colpersonal_id;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_nume;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_prenume;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_program;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_sex;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_specializare;

	    @FXML
	    private TableColumn<Personal, String> colpersonal_telefon;

	    @FXML
	    private Pane pane;

	    @FXML
	    private TableView<Personal> tbl_personal;

	    @FXML
	    private TextField txtpersonal_adresa;

	    @FXML
	    private TextField txtpersonal_email;

	    @FXML
	    private TextField txtpersonal_nume;

	    @FXML
	    private TextField txtpersonal_prenume;

	    @FXML
	    private TextField txtpersonal_sex;

	    @FXML
	    private TextField txtpersonal_specializare;

	    @FXML
	    private TextField txtpersonal_telefon;
	    
	    @FXML
	    private TextField txtpersonal_program;
	
	    private String query, nume, prenume, sex, email, specializare, telefon, adresa, program;
	    
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
		
    	txtpersonal_nume.setText("");
    	txtpersonal_prenume.setText("");
    	txtpersonal_sex.setText("");
    	txtpersonal_telefon.setText("");
    	txtpersonal_email.setText("");
    	txtpersonal_specializare.setText("");
    	txtpersonal_adresa.setText("");
    	txtpersonal_program.setText("");
		
	}
    
    private void saveAccount() {
		
		nume = txtpersonal_nume.getText();
		prenume = txtpersonal_prenume.getText();
		sex = txtpersonal_sex.getText();
		telefon = txtpersonal_telefon.getText();
		email = txtpersonal_email.getText();
		specializare = txtpersonal_specializare.getText();
		adresa = txtpersonal_adresa.getText();
		program = txtpersonal_program.getText();
		if (EDIT) {
		    query = "UPDATE angajati SET nume='" + nume + "', prenume='" + prenume + "', sex='" + sex + "', telefon='" + telefon + "', email='" + email + "', specializare='" + specializare + "', adresa='" + adresa + "', program='" + program + "' WHERE account_ID=" + ID + "";
		} else if (ADD) {
		    query = "INSERT INTO angajati (nume, prenume, sex, telefon, email, specializare, adresa, program) VALUES ('" + nume + "', '" + prenume + "', '" + sex + "', '" + telefon + "', '" + email + "', '" + specializare + "', '" + adresa + "', '" + program + "');";
		}
		
		if (query != null) {
		    dao.saveData(query);
		} else {
		    System.out.println("Șirul SQL este null.");
		}

		txtpersonal_nume.setText("");
    	txtpersonal_prenume.setText("");
    	txtpersonal_sex.setText("");
    	txtpersonal_telefon.setText("");
    	txtpersonal_email.setText("");
    	txtpersonal_specializare.setText("");
    	txtpersonal_adresa.setText("");
    	txtpersonal_program.setText("");
		
		refreshTable();
	}

	private void refreshTable() {
		initTable();
		query = "SELECT a.nume, a.prenume, a.sex, a.telefon, a.email, a.specializare, a.adresa, a.program, a.account_ID FROM angajati as a "
		         + "ORDER BY a.nume";

		tbl_personal.setItems(dao.getAccountsData(query));
	}
    
	private void initTable() {
		
		colpersonal_adresa.setCellValueFactory(cell->cell.getValue().getpAdresa());;
		colpersonal_email.setCellValueFactory(cell->cell.getValue().getpEmail());;
		colpersonal_nume.setCellValueFactory(cell->cell.getValue().getpNume());;
		colpersonal_prenume.setCellValueFactory(cell->cell.getValue().getpPrenume());;
		colpersonal_program.setCellValueFactory(cell->cell.getValue().getpProgram());;
		colpersonal_sex.setCellValueFactory(cell->cell.getValue().getpSex());;
		colpersonal_specializare.setCellValueFactory(cell->cell.getValue().getpSpecializare());;
		colpersonal_telefon.setCellValueFactory(cell->cell.getValue().getpTelefon());;
		colpersonal_id.setCellValueFactory(cell->cell.getValue().getpID().asObject());;
	}
	
	private void delete(){
		
		Personal selected = tbl_personal.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		query="DELETE FROM angajati WHERE account_ID="+ID+"";
		dao.saveData(query);
		refreshTable();
	}
	
	private void editAccount() {
		Personal selected = tbl_personal.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		txtpersonal_nume.setText(selected.getpNume().get());
		txtpersonal_prenume.setText(selected.getpPrenume().get());
		txtpersonal_sex.setText(selected.getpSex().get());
		txtpersonal_telefon.setText(selected.getpTelefon().get());
		txtpersonal_email.setText(selected.getpEmail().get());
		txtpersonal_specializare.setText(selected.getpSpecializare().get());
		txtpersonal_adresa.setText(selected.getpAdresa().get());
		txtpersonal_program.setText(selected.getpProgram().get());
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


