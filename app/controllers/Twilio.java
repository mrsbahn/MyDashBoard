package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLResponse;

public class Twilio  extends Controller{
	// Handle an incoming SMS...
    public static Result msg(){
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
}
