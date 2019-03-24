package main.implementacionesDAO;

import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import main.interfacesDAO.IBaraja;
import main.models.Baraja;

public class ConectionMongoDB implements IBaraja{

	private MongoClientURI connectionString;
	private MongoClient mongoClient;
	private MongoDatabase dbMongo;
	private MongoCollection<Document> collectionMongo;
	private Document userDoc;


	private void conexion() {
		connectionString = new MongoClientURI("mongodb://localhost:27017");
		mongoClient = new MongoClient(connectionString);		
	}


	private void desconectar() {
		mongoClient.close();
		connectionString=null;
	}


	private void conectarOcrearColeccion() {
		//Con esto buscamos en mongoDB una base de datos llamada Baraja
		dbMongo = mongoClient.getDatabase("dbBaraja");
		//Si no existe la crea con ese nombre
		collectionMongo = dbMongo.getCollection("Baraja");		
	}


	public void deconectarColeccion() {
		dbMongo = null;
		collectionMongo = null;

	}

	public Baraja getBarajaPorNombre(String name) {

		conexion();
		conectarOcrearColeccion();
		MongoCursor<Document> cursor = collectionMongo.find(Filters.eq("deckName", name)).iterator();

		Baraja baraja;
		if(cursor.hasNext()) {
			Document document = cursor.next();
			baraja = new Gson().fromJson(document.toJson(), Baraja.class);
			return baraja;
		} else {
			baraja = null;
			return null;
		}
	}

	public void saveBaraja(Baraja b1) {
		
		conexion();
		conectarOcrearColeccion();
		MongoCursor<Document> cursor = collectionMongo.find(Filters.eq("deckName", b1.getDeckName())).iterator();
		Gson gson = new Gson();
		String JSON = gson.toJson(b1);
		userDoc = Document.parse(JSON);
		if(!cursor.hasNext()) {
			collectionMongo.insertOne(userDoc);
		}

		desconectar();
		deconectarColeccion();
	}

	public void modificarBarajaCreada(Baraja b1) {
		conexion();
		conectarOcrearColeccion();
		MongoCursor<Document> cursor = collectionMongo.find(Filters.eq("deckName", b1.getDeckName())).iterator();
		Gson gson = new Gson();
		String JSON = gson.toJson(b1);
		userDoc = Document.parse(JSON);
		collectionMongo.deleteOne(userDoc);
		collectionMongo.insertOne(userDoc);
	}
}
