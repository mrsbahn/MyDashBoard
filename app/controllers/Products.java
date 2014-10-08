package controllers;

import java.util.List;

import models.Product;
import play.mvc.Controller;
import play.mvc.Result;
//import views.html.products.list;
public class Products extends Controller{
	
	public static Result list(){
	List<Product> products=Product.findAll();
	//return ok(list.render(products));
	return ok( );
	}
	
	public static Result newProduct(){
		return ok();
	}
	public static Result details(String ean){
		return ok();
	}
	public static Result save(){
		return ok();
	}

}
