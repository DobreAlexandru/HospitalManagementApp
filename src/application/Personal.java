package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personal{

	private final StringProperty pAdresa;
	private final StringProperty pEmail;
	private final StringProperty pNume;
	private final StringProperty pPrenume;
	private final StringProperty pProgram;
	private final StringProperty pSex;
	private final StringProperty pSpecializare;
	private final StringProperty pTelefon;
	private final IntegerProperty pID;
	
	
	public Personal(String pNume, String pPrenume, String pSex, String pTelefon, String pEmail, String pSpecializare, String pAdresa, String pProgram, int pID) {
		this.pNume = new SimpleStringProperty(pNume);
		this.pPrenume = new SimpleStringProperty(pPrenume);
		this.pSex = new SimpleStringProperty(pSex);
		this.pEmail = new SimpleStringProperty(pEmail);
		this.pSpecializare = new SimpleStringProperty(pSpecializare);
		this.pAdresa = new SimpleStringProperty(pAdresa);
		this.pProgram= new SimpleStringProperty(pProgram);
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

	public StringProperty getpSpecializare() {
		return pSpecializare;
	}
	
	public StringProperty getpAdresa() {
		return pAdresa;
	}
	
	public StringProperty getpEmail() {
		return pEmail;
	}
	
	public StringProperty getpTelefon() {
		return pTelefon;
	}
	
	public StringProperty getpProgram() {
		return pProgram;
	}
	
	public IntegerProperty getpID() {
		return pID;
	}
}