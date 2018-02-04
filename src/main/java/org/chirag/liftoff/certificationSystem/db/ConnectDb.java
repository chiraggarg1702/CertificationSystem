package org.chirag.liftoff.certificationSystem.db;
  
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.Iterator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  
import static com.mongodb.client.model.Filters.*;


public class ConnectDb {
	
	public static MongoDatabase connectDB() {
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		 
		 MongoDatabase db = mongo.getDatabase("certSystem");
		 
		 return db;
		 
	}

	
	public static String getUser(String str) {
		
		String[] s = str.split(",");
		
		MongoDatabase db = connectDB();
		
//		String str = "\"r\"";
		
		
		MongoCollection<Document> collection = db.getCollection("userRegistration");
		Document cursor = collection.find(or(eq("email", s[0]),eq("password",s[1]))).first();

		return cursor.toJson();
		 
	}

	
	public static void updateUser(String str) {
		
		String[] s = str.split(",");
		
		MongoDatabase db = connectDB();
		
//		String str = "\"r\"";
		
		
		MongoCollection<Document> collection = db.getCollection("userRegistration");
//		Document cursor = collection.find(or(eq("email", s[0]),eq("password",s[1]))).first();
		
		collection.updateMany(Filters.eq("email", s[0]), Updates.combine(Updates.set("fname",s[1]),
				Updates.set("lname",s[2]),
				Updates.set("contact",s[4]),
				Updates.set("password",s[3])));
				
//				combine(set("fname",user.get("email").toString()),set("fname",user.get("email").toString())));
//		DBCollection collection = (DBCollection)db.getCollection("userRegistration");
//				db.getCollection("userRegistration");
		
//		BasicDBObject query = new BasicDBObject();
//		query.put("fname", "a");
		
//		Bson filter = new Bson() {
//			
//			@Override
//			public <TDocument> BsonDocument toBsonDocument(Class<TDocument> arg0, CodecRegistry arg1) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
		
//		BsonDocument filter = new BsonDocument();
//		filter.put("fname", BsonValue.asString());
//		DBCursor cursor = collection.find(query);
//		
//		DBCursor c = coll.f;
		 
//		 FindIterable<Document> iter = collection.find(eq("fname", "a")).first();
		 
//		 Iterator it = (Iterator) iter.iterator();
		 
//		 String str="";
		 
//		 while(it.hasNext()) {
//			 System.out.println(it.next());
//			 }
//		while(cursor.) {
//			System.out.println(cursor.next());
//		}
//		return cursor.toJson();
		
		Document cursor = collection.find(or(eq("email",s[0] ),eq("password",s[3]))).first();
		System.out.println(cursor);
		 
	}
	
	public static void addUser(JsonObject user) {
		MongoDatabase db = connectDB();
		 
		MongoCollection<Document> collection = db.getCollection("userRegistration");
		
		
		 Document document = new Document("fname", user.get("fname").toString()) 
			      .append("lname", user.get("lname").toString())
			      .append("email", user.get("email").toString()) 
			      .append("password", user.get("passwd").toString()) 
			      .append("contact", user.get("contact").toString())
			      .append("appliedCourse", null)
			      .append("logicStatus","Not Applied");
			      collection.insertOne(document); 
		
//		 Document document = new Document("fname", "George") 
//			      .append("lname", "qwr")
//			      .append("email", "qew.mailinator.com") 
//			      .append("password", "abcd") 
//			      .append("contact", null);
//			      collection.insertOne(document); 
			      System.out.println("Document inserted successfully");
	}
	
public static String getCourse(String str) {
		
//		String[] s = str.split(",");
		
		MongoDatabase db = connectDB();
		
//		String str = "\"r\"";
		
		
		MongoCollection<Document> collection = db.getCollection("manageCourse");
		Document cursor = collection.find(eq("course_id",str)).first();

		return cursor.toJson().toString();
		 
	}

public static void applyCourse(String str) {
	
	String[] s = str.split(",");
	switch (s[2]) {
	case "1":
		str="OCJP";
		break;
	case "2":
		str="OCA";
		break;
	case "3":
		str="OCP";
		break;

	default:
		break;
	}
	
	MongoDatabase db = connectDB();
	
//	String str = "\"r\"";
	
	
	
	MongoCollection<Document> collection = db.getCollection("userRegistration");
//	MongoCollection<Document> collection2 = db.getCollection("manageCourse");
//	Document cursor = collection.find(eq("course_id",str)).first();
	collection.updateMany(Filters.eq("email", s[0]), Updates.combine(Updates.set("logicStatus","applied"),
			Updates.set("appliedCourse",str)));
//	return cursor.toJson().toString();
	 
}
	

}
