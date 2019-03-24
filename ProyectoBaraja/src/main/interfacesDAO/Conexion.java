package main.interfacesDAO;

import main.implementacionesDAO.ConectionMongoDB;

public class Conexion {

	
	public IBaraja  baraja;
	
	
	
	public void getBaraja() {
		baraja = new ConectionMongoDB();
	}
}
