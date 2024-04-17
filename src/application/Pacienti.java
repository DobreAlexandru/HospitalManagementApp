package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pacienti{

	private final StringProperty pAdresa;
	private final StringProperty pDiagnostic;
	private final StringProperty pNume;
	private final StringProperty pPrenume;
	private final StringProperty pProgramari;
	private final StringProperty pSex;
	private final StringProperty pTratament;
	private final StringProperty pTelefon;
	private final IntegerProperty pID;
	
	
	public Pacienti(String pNume, String pPrenume, String pSex, String pTelefon, String pDiagnostic, String pTratament, String pAdresa, String pProgramari, int pID) {
		this.pNume = new SimpleStringProperty(pNume);
		this.pPrenume = new SimpleStringProperty(pPrenume);
		this.pSex = new SimpleStringProperty(pSex);
		this.pDiagnostic = new SimpleStringProperty(pDiagnostic);
		this.pTratament = new SimpleStringProperty(pTratament);
		this.pAdresa = new SimpleStringProperty(pAdresa);
		this.pProgramari= new SimpleStringProperty(pProgramari);
		this.pTelefon = new SimpleStringProperty(pTelefon);
		this.pID = new SimpleIntegerProperty(pID);
	}

	public StringProperty getpNume() {
		return pNume;
	}

	public StringProperty getpPrenume() {
		return pPrenume;
	}

	public StringProperty getpSex() {
		return pSex;
	}

	public StringProperty getpDiagnostic() {
		return pDiagnostic;
	}
	
	public StringProperty getpAdresa() {
		return pAdresa;
	}
	
	public StringProperty getpTratament() {
		return pTratament;
	}
	
	public StringProperty getpTelefon() {
		return pTelefon;
	}
	
	public StringProperty getpProgramari() {
		return pProgramari;
	}
	
	public IntegerProperty getpID() {
		return pID;
	}
}