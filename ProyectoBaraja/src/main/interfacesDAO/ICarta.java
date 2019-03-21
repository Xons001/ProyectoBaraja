package main.interfacesDAO;

import java.util.ArrayList;

import main.models.Carta;

public interface ICarta {

	public ArrayList<Carta> cogerCartas();
	public void conexion();
	public void desconectar();
	public ArrayList<Carta> conectionCards();
	
}
