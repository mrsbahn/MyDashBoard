package models;

import java.util.ArrayList;
import java.util.List;

public class Product {
	public String ean;
	public String name;
	public String description;
	private static List<Product> products;
	static{	
		products = new ArrayList<Product>();
		products.add(new Product("1111111111111","Paperclips 1", "Paper sclips describtion 1"));
		products.add(new Product("2222222222222","Paperclips 2", "Paper sclips describtion 2"));
		products.add(new Product("3333333333333","Paperclips 3", "Paper sclips describtion 3"));
		products.add(new Product("4444444444444","Paperclips 4", "Paper sclips describtion 4"));
		products.add(new Product("5555555555555","Paperclips 5", "Paper sclips describtion 5"));
	}
	public Product(){}
	public Product(String ean, String name, String description) {
		super();
		this.ean = ean;
		this.name = name;
		this.description = description;
	}

	public String toString() {
		return "Product [ean=" + ean + ", name=" + name + ", description="
				+ description + "]";
	}
	
	public static List<Product> findAll(){
		return new ArrayList<Product>(products);
	}
	public static Product findByEan(String ean){
		for(Product candidate:products){
			if(candidate.ean.equals(ean));
			return candidate;
		}
		return null;
	}
	public static List<Product> findByName(String name){
		final List<Product> results= new ArrayList<Product>();
		for(Product candidate:products){
			if(candidate.name.toLowerCase().contains(name.toLowerCase()));
			results.add(candidate);
		}
		return results;
	}
	
	public static boolean remove(Product product){
		return products.remove(product);
	}
	
	public void save(){
		products.remove(findByEan(this.ean));
		products.add(this);
	}
	
	

}
