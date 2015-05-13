package domain;

public class Product {
	private int productNr;
	private String name;
	private long price;
	
	public Product(int productNr, String name, long price) {
		this.productNr = productNr;
		this.name = name;
		this.price = price;
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
