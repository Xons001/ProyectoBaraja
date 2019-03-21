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
	protected static String URI = "xmldb:exist://localhost:8844/exist/xmlrpc"; 
	protected static String collectionPath = "/db/ProyectoBaraja/"; 
	protected static String resourceName = "cartas";

	private static ConectionExist conectionExist;
	private static Class c1;
	private Database database;
	private ArrayList<Carta> cartas = new ArrayList<Carta>();


	private void conexion() {

		try {
			c1 = Class.forName(DRIVER);
			try {
				database = (Database) c1.newInstance();
				try {
					DatabaseManager.registerDatabase(database);
				} catch (XMLDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	private void desconectar() {
		c1 = null;
		database = null;
	}


	public static ConectionExist getInstance() {
		if(conectionExist==null) {
			conectionExist = new ConectionExist();
		}
		return conectionExist;
	}

	private ConectionExist() {
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

	}

	@Override
	public ArrayList<Carta> cogerCartas() {
		return cartas;
	}


}
