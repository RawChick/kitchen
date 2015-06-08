package businesslogic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import datastorage.EmployeeDAO;
import datastorage.OrderDAO;
import datastorage.ProductDAO;
import domain.Ingredient;
import domain.Order;
import domain.OrderRow;
import domain.Product;
import domain.ProductIngredients;

public class OrderManager {
	private ArrayList<Product> products;
	private ArrayList<Order> orders;
	private ArrayList<OrderRow> orderRows;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<ProductIngredients> productingredients;
	private OrderDAO orderDAO;
	private ProductDAO productDAO;
	private EmployeeDAO employeeDAO;
	
	public OrderManager() {
		products = new ArrayList<Product>();
		orders = new ArrayList<Order>();
		orderRows = new ArrayList<OrderRow>();
		ingredients = new ArrayList<Ingredient>();
		productingredients = new ArrayList<ProductIngredients>();
		
		employeeDAO = new EmployeeDAO();
		orderDAO = new OrderDAO();
		productDAO = new ProductDAO();
	}
	
	public void retrieveOrders() {
		orders = orderDAO.retrieveOrders();		
	}
	
	public void retrieveProducts() {
		products = productDAO.findProduct();		
	}
	
	public void acceptOrder(int orderNr) {
		for(Order order: orders) {
			if(order.getOrderNr() == orderNr) {
				order.setStatus("geaccepteerd");
				
				System.out.println("Ordernr: "+order.getOrderNr()+", status: "+order.getStatus());
			}
		}
	}
	
	public void readyOrder(int orderNr) {
		for(Order order: orders) {
			if(order.getOrderNr() == orderNr) {
				order.setStatus("gereed");
				
				System.out.println("Ordernr: "+order.getOrderNr()+", status: "+order.getStatus());
			}
		}
	}
	
	public ArrayList<Order> getOrders() {		
		return orders;
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
	
	public Product searchProduct(String productName) {
		Product product = null;
		
		for(Product p: products) {
			if(p.getName() == productName) {
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
	
	public boolean findEmployee(String employeeNumber) {
		boolean login = employeeDAO.findEmployee(employeeNumber);
		
		return login;
	}
}
