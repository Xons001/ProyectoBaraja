package main.implementacionesDAO;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.interfacesDAO.IBaraja;

public class ConectionMongoDB implements IBaraja{

	private MongoClientURI connectionString;
	private MongoClient mongoClient;
	private MongoDatabase dbMongo;
	private MongoCollection<Document> collectionMongo;
	
	@Override
	public void conexion() {
		connectionString = new MongoClientURI("mongodb://localhost:27017");
		mongoClient = new MongoClient(connectionString);		
	}

	@Override
	public void desconectar() {
		mongoClient.close();
		connectionString=null;
	}

	
	
}
