package domain;

public class OrderRow {
	private int tableNr;
	private Order order;
	private Product product;
	private int amount;
	
	public OrderRow(int tableNr, Order order, Product product, int amount) {
		this.tableNr = tableNr;
		this.order = order;
		this.product = product;
		this.amount = amount;
	}
	
	public int getTableNr() {
		return tableNr;
	}
	
	public int getOrderNr() {
		return order.getOrderNr();
	}
	
	public int getProductNr() {
		return product.getProductNr();
	}
	
	public int getAmount() {
		return amount;
	}
}
