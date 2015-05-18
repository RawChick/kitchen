package domain;

import java.util.*;

public class Order {
	private int tableNr;
	private int orderNr;
	private String status;
	private Date placed;
	
	public Order(int tableNr, int orderNr, String status, Date placed) {
		this.tableNr = tableNr;
		this.orderNr = orderNr;
		this.status = status;
		this.placed = placed;
	}
	
	public int getTableNr() {
		return tableNr;
	}
	
	public int getOrderNr() {
		return orderNr;
	}
	
	public Date getDatePlaced() {
		return placed;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
