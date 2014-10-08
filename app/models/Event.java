package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Event extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;	
	private String eventName;
	private String eventAddress;
	private String maxPerson;

 
 

	public String getId() {
		return id;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public String getMaxPerson() {
		return maxPerson;
	}
}
