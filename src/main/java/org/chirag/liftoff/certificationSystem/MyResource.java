package org.chirag.liftoff.certificationSystem;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.chirag.liftoff.certificationSystem.db.ConnectDb;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
//	@Path("myresource")
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//        return "Got it!";
//    }
	

	@Path("user")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllUser(String str) {
		return ConnectDb.getUser(str);
	}
	
	@Path("updatexUser")
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.TEXT_PLAIN)
	public void updateSingleUser(String str) {
		
//		JsonParser parser = new JsonParser();
//		JsonObject json = (JsonObject)parser.parse(str);
		
		ConnectDb.updateUser(str);
//		return "Registration Successful";
	}
	
	@Path("addxUser")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSingleUser(String str) {
		
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(str);
		
		ConnectDb.addUser(json);
		return "Registration Successful";
	}
	
	@Path("getxCourse")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getCourseDetails(String str) {
		
        return ConnectDb.getCourse(str);
    }
	
	@Path("applyxCourse")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN)
    public void applyForCourse(String str) {
		
        ConnectDb.applyCourse(str);
    }
}
