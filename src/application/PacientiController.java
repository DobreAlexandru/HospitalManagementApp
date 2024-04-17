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

public class PacientiController implements Initializable{
	
	
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
	    private TableColumn<Pacienti, String> col_adresa;

	    @FXML
	    private TableColumn<Pacienti, String> col_diagnostic;

	    @FXML
	    private TableColumn<Pacienti, Integer> colpersonal_id;

	    @FXML
	    private TableColumn<Pacienti, String> col_nume;

	    @FXML
	    private TableColumn<Pacienti, String> col_prenume;

	    @FXML
	    private TableColumn<Pacienti, String> col_programari;

	    @FXML
	    private TableColumn<Pacienti, String> col_sex;

	    @FXML
	    private TableColumn<Pacienti, String> col_tratament;

	    @FXML
	    private TableColumn<Pacienti, String> col_telefon;

	    @FXML
	    private Pane pane;

	    @FXML
	    private TableView<Pacienti> tbl_pacienti;

	    @FXML
	    private TextField txt_adresa;

	    @FXML
	    private TextField txt_tratament;

	    @FXML
	    private TextField txt_nume;

	    @FXML
	    private TextField txt_prenume;

	    @FXML
	    private TextField txt_sex;

	    @FXML
	    private TextField txt_diagnostic;

	    @FXML
	    private TextField txt_telefon;
	    
	    @FXML
	    private TextField txt_programari;
	
	    private String query, nume, prenume, sex, diagnostic, tratament, telefon, adresa, programari;
	    
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
		
    	txt_nume.setText("");
    	txt_prenume.setText("");
    	txt_sex.setText("");
    	txt_telefon.setText("");
    	txt_diagnostic.setText("");
    	txt_tratament.setText("");
    	txt_adresa.setText("");
    	txt_programari.setText("");
		
	}
    
    private void saveAccount() {
        nume = txt_nume.getText();
        prenume = txt_prenume.getText();
        sex = txt_sex.getText();
        telefon = txt_telefon.getText();
        diagnostic = txt_diagnostic.getText();
        tratament = txt_tratament.getText();
        adresa = txt_adresa.getText();
        programari = txt_programari.getText();

        if (EDIT) {
            query = "UPDATE pacienti SET nume='" + nume + "', prenume='" + prenume + "', sex='" + sex + "', telefon='" + telefon + "', diagnostic='" + diagnostic + "', tratament='" + tratament + "', adresa='" + adresa + "', programari='" + programari + "' WHERE pacient_ID=" + ID + "";
        } else if (ADD) {
            query = "INSERT INTO pacienti (nume, prenume, sex, telefon, adresa, diagnostic, tratament, programari) VALUES ('" + nume + "', '" + prenume + "', '" + sex + "', '" + telefon + "', '" + adresa + "', '" + diagnostic + "', '" + tratament + "', '" + programari + "');";
        }

        if (query != null) {
            dao.saveData(query);
        } else {
            System.out.println("Șirul SQL este null.");
        }

        txt_nume.setText("");
        txt_prenume.setText("");
        txt_sex.setText("");
        txt_telefon.setText("");
        txt_diagnostic.setText("");
        txt_tratament.setText("");
        txt_adresa.setText("");
        txt_programari.setText("");

        refreshTable();
    }


	private void refreshTable() {
		initTable();
		query = "SELECT a.nume, a.prenume, a.sex, a.telefon, a.adresa, a.diagnostic, a.tratament, a.programari, a.pacient_ID FROM pacienti as a "
		         + "ORDER BY a.nume";

		tbl_pacienti.setItems(dao.getPacientiData(query));
	}
    
	private void initTable() {
		
		col_adresa.setCellValueFactory(cell->cell.getValue().getpDiagnostic());;
		col_diagnostic.setCellValueFactory(cell->cell.getValue().getpTratament());;
		col_nume.setCellValueFactory(cell->cell.getValue().getpNume());;
		col_prenume.setCellValueFactory(cell->cell.getValue().getpPrenume());;
		col_programari.setCellValueFactory(cell->cell.getValue().getpProgramari());;
		col_sex.setCellValueFactory(cell->cell.getValue().getpSex());;
		col_tratament.setCellValueFactory(cell->cell.getValue().getpAdresa());;
		col_telefon.setCellValueFactory(cell->cell.getValue().getpTelefon());;
		colpersonal_id.setCellValueFactory(cell->cell.getValue().getpID().asObject());;
	}
	
	private void delete(){
		
		Pacienti selected = tbl_pacienti.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		query="DELETE FROM pacienti WHERE pacient_ID="+ID+"";
		dao.saveData(query);
		refreshTable();
	}
	
	private void editAccount() {
		Pacienti selected = tbl_pacienti.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		txt_nume.setText(selected.getpNume().get());
		txt_prenume.setText(selected.getpPrenume().get());
		txt_sex.setText(selected.getpSex().get());
		txt_telefon.setText(selected.getpTelefon().get());
		txt_adresa.setText(selected.getpAdresa().get());
		txt_diagnostic.setText(selected.getpDiagnostic().get());
		txt_tratament.setText(selected.getpTratament().get());
		txt_programari.setText(selected.getpProgramari().get());
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


