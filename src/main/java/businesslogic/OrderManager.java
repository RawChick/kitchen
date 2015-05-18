package businesslogic;

import java.util.*;

import datastorage.OrderDAO;
import domain.Order;
import domain.Product;
import domain.OrderRow;

public class OrderManager {
	private ArrayList<Product> products;
	private ArrayList<Order> orders;
	private ArrayList<OrderRow> orderRows;
	private OrderDAO orderDAO;
	
	public OrderManager() {
		products = new ArrayList<Product>();
		orders = new ArrayList<Order>();
		orderRows = new ArrayList<OrderRow>();
		
		fillTestData();
	}
	
	public void fillTestData() {
		Date date1 = new Date(2015, 05, 18);
		Order order1 = new Order(1, 1, "geplaatst", date1);
		Product product1 = new Product(1, "Bier", 210);
		Product product2 = new Product(2, "Koffie", 200);
		OrderRow orderRow1 = new OrderRow(1, 1, 1, 2);
		OrderRow orderRow2 = new OrderRow(1, 1, 2, 4);
		
		orders.add(order1);
		products.add(product1);
		products.add(product2);
		orderRows.add(orderRow1);
		orderRows.add(orderRow2);
	}
	
	public void acceptOrder(int orderNr) {
		for(Order order: orders) {
			if(order.getOrderNr() == orderNr) {
				order.setStatus("geaccepteerd");
			}
		}
	}
	
	public ArrayList<Order> getOrders() {		
		return orders;
	}
	
	public void removeOrder(int orderNr) {
		
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public ArrayList<Product> getProducts(int tableNr) {
		ArrayList<Product> products = new ArrayList<Product>();
		
		for(OrderRow orderRow: orderRows) {
			if(orderRow.getTableNr() == tableNr) {
				Product product = searchProduct(orderRow.getProductNr());
				
				products.add(product);
			}
		}
		
		return products;
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
