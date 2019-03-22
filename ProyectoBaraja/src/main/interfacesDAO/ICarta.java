package main.interfacesDAO;

import java.util.ArrayList;

import main.models.Carta;

public interface ICarta {

	public void conexion();
	public void desconectar();
	public ArrayList<Carta> conectionCards();
	public ArrayList<Carta> cogerCartas();
	
}
