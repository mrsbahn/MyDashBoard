package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Model;
import models.*;
import views.html.*;

import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
 
import org.codehaus.jackson.node.ArrayNode;
 

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLResponse;

public class Application extends Controller {
	
	public static Result addPerson(){
		Person person = Form.form(Person.class).bindFromRequest().get();
		person.save();		 
		return redirect(routes.Application.index());	
		//return ok(Json.toJson(person));
		//return redirect("http://localhost:9000/#displayData");
	}
	public static Result getPerson(){
		List<Person> persons = new Model.Finder(String.class, Person.class).all();
		 return ok(Json.toJson(persons));
		 
	}
	public static Result addEvent(){
		Event event = Form.form(Event.class).bindFromRequest().get();
		event.save();
		return redirect(routes.Application.index());		
		
		// return ok(Json.toJson(allEvents));
	}
	
	
	
	public static Result getEvent(){
		List<Event> allEvents = new Model.Finder(String.class, Event.class).all();
		 return ok(Json.toJson(allEvents));
		 
	}
	
	
	 public static Result index() {
		    return ok(index.render("Hallo"));
		  }
	 


    
	 public static Result messages(){
	    	//Use the TwimlResponse object to build up the XML reply.
	    	TwiMLResponse twiml = new TwiMLResponse();
	    	Message message = new Message("Thanks for sending me an SMS!");
	    	try {
				twiml.append(message);
			} catch (Exception e) {
				Logger.error("An error occurred trying to append the message verb to the response verb.", e);
			}
	    	return ok(twiml.toXML()).as("text/xml");
	    }

	 
	//Table
	 	@BodyParser.Of(BodyParser.Json.class)
		public static Result list(){
			  /**
		     * Get needed params
		     */
			Map<String, String[]> params = request().queryString();
		    Integer iTotalRecords = Person.find.findRowCount();
		    String filter = params.get("sSearch")[0];
		    Integer pageSize = Integer.valueOf(params.get("iDisplayLength")[0]);
		    Integer page = Integer.valueOf(params.get("iDisplayStart")[0]) / pageSize;
			
		    /**
		     * Get sorting order and column
		     */
		    String sortBy = "name";
		    String order = params.get("sSortDir_0")[0];

		    switch(Integer.valueOf(params.get("iSortCol_0")[0])) {
		      case 0 :  sortBy = "gender"; break;
		      case 1 :  sortBy = "name"; break;
		      case 2 :  sortBy = "address"; break;
		    }
			
		    /**
		     * Get page to show from database
		     * It is important to set setFetchAhead to false, since it doesn't benefit a stateless application at all.
		     */
		    Page<Person> personPage = Person.find.where(
		      Expr.or(
		        Expr.ilike("gender", "%"+filter+"%"),
		          Expr.or(
		            Expr.ilike("name", "%"+filter+"%"),
		            Expr.ilike("address", "%"+filter+"%")
		          )
		        )
		      )
		      .orderBy(sortBy + " " + order + ", id " + order)
		      .findPagingList(pageSize).setFetchAhead(false)
		      .getPage(page);

		    Integer iTotalDisplayRecords = personPage.getTotalRowCount();
			
			
		    /**
		     * Construct the JSON to return
		     */
		    ObjectNode result = Json.newObject();

		    result.put("sEcho", Integer.valueOf(params.get("sEcho")[0]));
		    result.put("iTotalRecords", iTotalRecords);
		    result.put("iTotalDisplayRecords", iTotalDisplayRecords);

		    com.fasterxml.jackson.databind.node.ArrayNode an =  result.putArray("aaData");

		    for(Person c : personPage.getList()) {
		      ObjectNode row = Json.newObject();
		      row.put("0", c.gender);
		      row.put("1", c.name);
		      row.put("2", c.address);
		      an.add(row);
		    }

		    return ok(result);
		  }
	 
}
