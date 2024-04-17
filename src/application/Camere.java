package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Camere{

	private final StringProperty pNumar;
	private final StringProperty pStare;
	private final StringProperty pPacient;
	private final IntegerProperty pID;
	
	
	public Camere(String pNumar, String pStare, String pPacient, int pID) {
		this.pNumar = new SimpleStringProperty(pNumar);
		this.pStare = new SimpleStringProperty(pStare);
		this.pPacient = new SimpleStringProperty(pPacient);
		this.pID = new SimpleIntegerProperty(pID);
	}

	public StringProperty getpNumar() {
		return pNumar;
	}

	public StringProperty getpStare() {
		return pStare;
	}

	public StringProperty getpPacient() {
		return pPacient;
	}

	public IntegerProperty getpID() {
		return pID;
	}
}