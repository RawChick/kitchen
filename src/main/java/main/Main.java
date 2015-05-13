package main;

import presentation.KitchenGUI;
import businesslogic.OrderManager;

public class Main {

	public static void main(String[] args) {
		OrderManager manager = new OrderManager();
		KitchenGUI gui = new KitchenGUI(manager);
	}

}
