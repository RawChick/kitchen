package domain;

import java.sql.Time;

public class Product {
	private int productNr;
	private String name;
	private long price;
	private Time preparationTime;
	
	public Product(int productNr, String name, long price, Time preparationTime) {
		this.productNr = productNr;
		this.name = name;
		this.price = price;
		this.preparationTime = preparationTime;
	}
	
	
	public Time getPreparationTime(){
		return preparationTime;
	}
	
	public int getProductNr() {
		return productNr;
	}
	
	public String getName() {
		return name;
	}
	
	public long getPrice() {
		return price;
	}
}
