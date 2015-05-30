package businesslogic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import datastorage.OrderDAO;
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
	
	public OrderManager() {
		products = new ArrayList<Product>();
		orders = new ArrayList<Order>();
		orderRows = new ArrayList<OrderRow>();
		ingredients = new ArrayList<Ingredient>();
		productingredients = new ArrayList<ProductIngredients>();
		
		fillTestData();
	}
	
	public void fillTestData() {
		Date date1 = new Date(2015, 05, 18);
		Order order1 = new Order(1, 1, "geplaatst", date1);
		Product product1 = new Product(1, "Bier", 210, new Time(00,25,00));
		Product product2 = new Product(2, "Koffie", 200, new Time(01,15,00));
		OrderRow orderRow1 = new OrderRow(1, order1, product1, 2);
		OrderRow orderRow2 = new OrderRow(1, order1, product2, 4);
		
		Order order2 = new Order(2, 2, "geplaatst", date1);
		Product product3 = new Product(3, "Espresso", 200, new Time(00,05,00));
		Product product4 = new Product(4, "LatteMachiato", 240, new Time(00,45,00));
		OrderRow orderRow3 = new OrderRow(2, order2, product3, 2);
		OrderRow orderRow4 = new OrderRow(2, order2, product4, 4);
		OrderRow orderRow5 = new OrderRow(2, order2, product1, 3);
		
		Ingredient ingredient1 = new Ingredient(1, "tomaat");
		Ingredient ingredient2 = new Ingredient(2, "sla");
		Ingredient ingredient3 = new Ingredient(3, "kaas");
		Ingredient ingredient4 = new Ingredient(4, "komkommer");
		Ingredient ingredient5 = new Ingredient(5, "ei");
		Ingredient ingredient6 = new Ingredient(6, "worst");
		Ingredient ingredient7 = new Ingredient(7, "ham");
		Ingredient ingredient8 = new Ingredient(8, "aardappel");
		Ingredient ingredient9 = new Ingredient(9, "erwten");
		Ingredient ingredient10 = new Ingredient(10, "bloemkool");
		
		ProductIngredients productingredients1 = new ProductIngredients(2,product2, ingredient1, 4, "plakjes");
		ProductIngredients productingredients2 = new ProductIngredients(1, product1, ingredient2, 400, "gram");
		ProductIngredients productingredients3 = new ProductIngredients(3, product3, ingredient3, 6, "plakjes");
		ProductIngredients productingredients4 = new ProductIngredients(4, product4, ingredient4, 2, "plakjes");
		ProductIngredients productingredients5 = new ProductIngredients(2, product2, ingredient5, 500, "gram");
		ProductIngredients productingredients6 = new ProductIngredients(1, product1, ingredient6, 7, "plakjes");
		ProductIngredients productingredients7 = new ProductIngredients(3, product3, ingredient7, 300, "gram");
		ProductIngredients productingredients8 = new ProductIngredients(4, product4, ingredient8, 40, "gram");
		ProductIngredients productingredients9 = new ProductIngredients(1, product1, ingredient9, 48, "gram");
		ProductIngredients productingredients10 = new ProductIngredients(2, product2, ingredient10, 36, "gram");
		ProductIngredients productingredients11 = new ProductIngredients(3, product3, ingredient4, 8, "plakjes");
		ProductIngredients productingredients12 = new ProductIngredients(4, product4, ingredient2, 5, "plakjes");
		ProductIngredients productingredients13 = new ProductIngredients(2, product2, ingredient1, 1, "plakje");
		
		
		
		
		
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
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		ingredients.add(ingredient3);
		ingredients.add(ingredient4);
		ingredients.add(ingredient5);
		ingredients.add(ingredient6);
		ingredients.add(ingredient7);
		ingredients.add(ingredient8);
		ingredients.add(ingredient9);
		ingredients.add(ingredient10);
		productingredients.add(productingredients1);
		productingredients.add(productingredients2);
		productingredients.add(productingredients3);
		productingredients.add(productingredients4);
		productingredients.add(productingredients5);
		productingredients.add(productingredients6);
		productingredients.add(productingredients7);
		productingredients.add(productingredients8);
		productingredients.add(productingredients9);
		productingredients.add(productingredients10);
		productingredients.add(productingredients11);
		productingredients.add(productingredients12);
		productingredients.add(productingredients13);
		
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
}
