import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import models.Person;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;


public class Global  extends GlobalSettings{
	public void onStart(Application app) {

	    /**
	     * Here we load the initial data into the database
	     * Disable it for now. 
	     */
		
//	    if(Ebean.find(Person.class).findRowCount() == 0) {
//	      Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
//	      Ebean.save(all.get("person"));
//	    }
	  }
}
