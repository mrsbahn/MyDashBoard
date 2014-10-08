package models;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ning.http.client.Request;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.mvc.Result;

 
@Entity
public class Person extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//sorry public due to simplicty(demo pupose only) of not using get/set methods

	@Id
	public String id;	
	@Required(message = "validation.required.emphasis")
	public String gender;
	@Required(message = "validation.required.emphasis")
	public String name;
	@Required(message = "validation.required.emphasis")
	public String address;
	 
	
	
	
	
	public static Model.Finder<Long, Person> find = new Model.Finder<Long, Person>(Long.class, Person.class);
	public static List<Person> findAll(){
		return find.all();
	}
	 
	public String toString() {
		return "Person [gender=" + gender + ", name=" + name + ", address="
				+ address + "]";
	}

	public   String getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	
	
}
