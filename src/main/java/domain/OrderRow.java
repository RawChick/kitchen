package domain;

public class OrderRow {
	private int tableNr;
	private int orderNr;
	private int productNr;
	private int amount;
	
	public OrderRow(int tableNr, int orderNr, int productNr, int amount) {
		this.tableNr = tableNr;
		this.orderNr = orderNr;
		this.productNr = productNr;
		this.amount = amount;
	}
	
	public int getTableNr() {
		return tableNr;
	}
	
	public int getOrderNr() {
		return orderNr;
	}
	
	public int getProductNr() {
		return productNr;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int newAmount) {
		
	}
}
