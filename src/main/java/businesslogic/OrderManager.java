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
		OrderRow orderRow1 = new OrderRow(1, order1, product1, 2);
		OrderRow orderRow2 = new OrderRow(1, order1, product2, 4);
		
		Order order2 = new Order(2, 2, "geplaatst", date1);
		Product product3 = new Product(3, "Espresso", 200);
		Product product4 = new Product(4, "Latte Machiato", 240);
		OrderRow orderRow3 = new OrderRow(2, order2, product3, 2);
		OrderRow orderRow4 = new OrderRow(2, order2, product4, 4);
		OrderRow orderRow5 = new OrderRow(2, order2, product1, 3);
		
		orders.add(order1);
		orders.add(order2);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		orderRows.add(orderRow1);
		orderRows.add(orderRow2);
		orderRows.add(orderRow3);
		orderRows.add(orderRow4);
		orderRows.add(orderRow5);
	}
	
	public void acceptOrder(int orderNr) {
		for(Order order: orders) {
			if(order.getOrderNr() == orderNr) {
				order.setStatus("geaccepteerd");
				
				System.out.println("Ordernr: "+order.getOrderNr()+", status: "+order.getStatus());
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
	
	public ArrayList<Product> getProducts(int orderNr) {
		ArrayList<Product> products = new ArrayList<Product>();
		
		for(OrderRow orderRow: orderRows) {
			if(orderRow.getOrderNr() == orderNr) {
				Product product = searchProduct(orderRow.getProductNr());
				
				products.add(product);
			}
		}
		
		return products;
	}
	
	public Order getOrders(int tableNr) {
		Order order = null;
		
		for(OrderRow orderRow: orderRows) {
			if(orderRow.getTableNr() == tableNr) {
				order = searchOrder(orderRow.getOrderNr());
			}
		}
		
		return order;
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
	
	public Order searchOrder(int orderNr) {
		Order order = null;
		
		for(Order o: orders) {
			if(o.getOrderNr() == orderNr) {
				order = o;
			}
		}
		
		return order;
	}
}
