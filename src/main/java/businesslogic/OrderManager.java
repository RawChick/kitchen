package businesslogic;

import java.util.*;

import datastorage.OrderDAO;
import domain.Order;
import domain.Product;

public class OrderManager {
	private ArrayList<Product> products;
	private OrderDAO orderDAO;
	
	public OrderManager() {
		products = new ArrayList<Product>();
	}
	
	public void acceptOrder(int orderNr) {
		
	}
	
	public ArrayList<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		
		return orders;
	}
	
	public void removeOrder(int orderNr) {
		
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public Product searchProduct(int productNr) {
		Product product = null;
		
		for(Product p: products) {
			if(p.getProductNr() == productNr) {
				product = p;
			}
		}
		
		return product;
	}
}
