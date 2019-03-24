package main.componentes;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import main.implementacionesDAO.ConectionExist;
import main.models.Carta;
import main.ventanas.VentanaPrincipal;

public class MetodosCartas {

	public static ArrayList<Carta> cartasNombre = new ArrayList<>();
	public static ArrayList<Carta> cartasEscogidas = new ArrayList<>();

	
	public static ConectionExist ce;
	public static VentanaPrincipal vp;


	public static DefaultListModel insertarCartasListas(DefaultListModel modelo) {
		ce = new ConectionExist();
		//nos conectamos en la base de datos exist y guardamos los valores del xml en un arrayList de carta
		cartasNombre = ce.conectionCards();
		//instanciamos un default list model para poder guardar
		DefaultListModel modelo1 = new DefaultListModel();
		//printamos todos los datos del list model en la lista
		for (Carta carta : cartasNombre) {
			modelo1.addElement(carta.getName() + " - " + carta.getValue() + " valor");
		}
		//retorna el modelo donde se printara todas las cartas del xml
		return modelo1;
	}

	public static void pasarCartasLista(DefaultListModel modelo, DefaultListModel modelo2, JList lista1, JList lista2) {
		//inserta el elemento de la carta de la lista de la izquierda a la lista de la derecha y lo guarda
		modelo2.addElement(cartasNombre.get(lista1.getSelectedIndex()));
		modelo.remove(lista1.getSelectedIndex());
		lista2.setModel(modelo2);
		lista1.setModel(modelo);
	}
}
