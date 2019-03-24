package main.interfacesDAO;

import main.models.Baraja;

public interface IBaraja {

	public Baraja getBarajaPorNombre(String name);
	public void saveBaraja(Baraja b1);
	public void modificarBarajaCreada(Baraja b1);
}
