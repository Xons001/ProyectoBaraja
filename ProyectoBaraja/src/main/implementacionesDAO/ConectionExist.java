package main.implementacionesDAO;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xmldb.api.DatabaseManager; 
import org.xmldb.api.base.Collection; 
import org.xmldb.api.base.Database; 
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.google.gson.Gson;

import main.interfacesDAO.ICarta;
import main.models.Carta;

public class ConectionExist implements ICarta{

	protected static String DRIVER = "org.exist.xmldb.DatabaseImpl"; 
	//Para probarlo tienes que poner la ruta correcta de donde tengas la base de datos
	protected static String URI = "xmldb:exist://localhost:8844/exist/xmlrpc/db/ProyectoBaraja"; 
	//Tienes que fijarte como se llama el archivo de xml en la bd Exist, fijarte si tiene el xml al final o no.
	protected static String resourceName = "cartas";

	private static Class c1;
	private Database database;
	private ArrayList<Carta> cartas = new ArrayList<Carta>();


	public void conexion() {
		try {
			c1 = Class.forName(DRIVER);
			database = (Database) c1.newInstance();
			DatabaseManager.registerDatabase(database);
			
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException | XMLDBException e) {
			e.printStackTrace();
		}

	}
	
	public void desconectar() {
		c1 = null;
		database = null;
	}
	
	
	@Override
	public ArrayList<Carta> cogerCartas() {
		return cartas;
	}
	
	public ArrayList<Carta> conectionCards() {
		conexion();
		try {
			Collection col = DatabaseManager.getCollection(URI); 
	
			XMLResource res = (XMLResource) col.getResource(resourceName);
			JSONObject xmlJSONObj = XML.toJSONObject((String)res.getContent());
	
			JSONArray allCards = xmlJSONObj.getJSONObject("cards").getJSONArray("card");
	
			cartas.clear();
			for (Object object : allCards) {
				Carta data = new Gson().fromJson(object.toString(), Carta.class);
				cartas.add(data);
				
			}
	
		} catch (XMLDBException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		return cartas;
	
	}
	
	
}
