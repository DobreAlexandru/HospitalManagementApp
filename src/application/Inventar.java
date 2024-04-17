package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventar{

	private final StringProperty pTip;
	private final StringProperty pCantitate;
	private final StringProperty pData;
	private final StringProperty pCost;
	private final IntegerProperty pID;
	
	
	public Inventar(String pTip, String pCantitate, String pData, String pCost, int pID) {
		this.pTip = new SimpleStringProperty(pCantitate);
		this.pCantitate = new SimpleStringProperty(pCantitate);
		this.pData = new SimpleStringProperty(pData);
		this.pCost = new SimpleStringProperty(pCost);
		this.pID = new SimpleIntegerProperty(pID);
	}

	public StringProperty getpTip() {
		return pTip;
	}

	public StringProperty getpCantitate() {
		return pCantitate;
	}

	public StringProperty getpData() {
		return pData;
	}
	
	public StringProperty getpCost() {
		return pCost;
	}

	public IntegerProperty getpID() {
		return pID;
	}
}