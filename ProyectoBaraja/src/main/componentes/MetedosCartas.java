package main.componentes;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import main.implementacionesDAO.ConectionExist;
import main.models.Carta;

public class MetedosCartas {

	public static ArrayList<Carta> cartasNombre;
	
	public static DefaultListModel insertarCartasListas() {
		ConectionExist ce = new ConectionExist();
		cartasNombre = ce.conectionCards();
		DefaultListModel modelo = new DefaultListModel();
		for (Carta carta : cartasNombre) {
			modelo.addElement(carta.getName());
		}
		return modelo;
	}
}
