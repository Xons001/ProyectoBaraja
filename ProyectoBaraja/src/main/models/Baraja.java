package main.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Baraja {

	private String deckName;
	private String deckValue;
	private ArrayList<Carta> baraja;
	
	//Constructor
	public Baraja(String deckName, String deckValue,ArrayList<Carta> baraja) {
		this.deckName = deckName;
		this.deckValue = deckValue;
		this.baraja = baraja;
	}

	//Getters
	public String getDeckName() {
		return deckName;
	}
	public String getDeckValue() {
		return deckValue;
	}
	public ArrayList<Carta> getBaraja() {
		return baraja;
	}

	//Setters
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	public void setDeckValue(String deckValue) {
		this.deckValue = deckValue;
	}
	public void setBaraja(ArrayList<Carta> baraja) {
		this.baraja = baraja;
	}

	@Override
	public String toString() {
		return "Baraja [deckName=" + deckName + ", deckValue=" + deckValue + ", baraja=" + baraja + "]";
	}
	
	
	
	
}
