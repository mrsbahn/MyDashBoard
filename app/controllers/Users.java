package controllers;

import models.User;
import play.mvc.Controller;

public class Users extends Controller{
public static void showUser(String id){
	ok();
}
public static void deleteUser(String id){
	ok();
}
public static void createUser(User user){
	showUser(user.id);
}
}
