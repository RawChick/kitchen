package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import domain.Order;
import domain.Product;
import businesslogic.OrderManager;

public class KitchenGUI extends JPanel {
	private OrderManager manager;
	private JFrame frame;
	
	private JSplitPane splitPane;
	private DefaultListModel defaultOrderListModel = new DefaultListModel();
//	private DefaultListModel acceptedOrderListModel = new DefaultListModel();
	private JList defaultOrderList = new JList(defaultOrderListModel);
	
	private DefaultListModel mealListModel = new DefaultListModel();
	private JList mealList = new JList(mealListModel);
	private JButton acceptOrder;
	private JButton specificationMeal;
	private JButton ready;
	private JButton newMeal;
	private JButton changeMeal;
	private JButton deleteMeal;
	private DefaultListModel managingMealListModel = new DefaultListModel();
	private JList managingMealList = new JList(managingMealListModel);
	
	public KitchenGUI(OrderManager manager, JFrame frame) {
		this.manager = manager;
		this.frame = frame;
		
		createKitchenGUI();
	}
	
	public void createKitchenGUI() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent orderOverview = new JPanel();
		
		JComponent mealManaging = new JPanel();
		
		createOrderOverview(orderOverview);

		createMealManaging(mealManaging);
		
		tabbedPane.addTab("Bestellingsoverzicht", orderOverview);
		tabbedPane.addTab("Gerechtenbeheer", mealManaging);
		
		add(tabbedPane);
	}
	
	public void createOrderOverview(JComponent orderOverview) {
		ArrayList<Order> orders = manager.getOrders();
		
		orderOverview.setLayout(new BorderLayout(10, 10));
		orderOverview.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout(10, 10));
		
		acceptOrder = new JButton("Accepteren");
		acceptOrder.addActionListener(acceptActionListener);
		specificationMeal = new JButton("Gerechtspecificaties");
		specificationMeal.addActionListener(specificationActionListener);
	
		ready = new JButton("Gereed");
		ready.addActionListener(readyActionListener);
		setButtonEnabled();
		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		defaultOrderList.setMinimumSize(minimumSize);
		mealList.setMinimumSize(minimumSize);
		
		defaultOrderList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		defaultOrderList.setLayoutOrientation(JList.VERTICAL);
		defaultOrderList.setVisibleRowCount(-1);
		defaultOrderList.addListSelectionListener(selectionListener);
		
		
		for(Order order: orders) {
			if(order.getStatus().equals("geplaatst")) {
				defaultOrderListModel.addElement("Bestelnr: " + order.getOrderNr());
			}	//else if((manager.searchOrder(orderNr)).getStatus().equals("geaccepteerd")){
				//acceptedOrderListModel.addElement("Bestelnr: " + order.getOrderNr());
		//	}
		}
		
		
		
		south.add(acceptOrder, BorderLayout.WEST);
		south.add(specificationMeal, BorderLayout.EAST);
		south.add(ready, BorderLayout.CENTER);
			
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, defaultOrderList, mealList);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(100);
		
		orderOverview.add(south, BorderLayout.SOUTH);
		orderOverview.add(splitPane, BorderLayout.CENTER);
		
	}
	
	
	public void createMealManaging(JComponent mealManaging) {
		
		
		mealManaging.setLayout(new BorderLayout(10, 10));
		mealManaging.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
		 JScrollPane scrollableList = new JScrollPane(managingMealList);
		
			JPanel south = new JPanel();
			south.setLayout(new BorderLayout(10, 10));
			
			newMeal = new JButton("Nieuw");
			newMeal.addActionListener(newMealActionListener);
			changeMeal = new JButton("Wijzigen");
			changeMeal.addActionListener(changeMealActionListener);
			deleteMeal = new JButton("Verwijderen");
			deleteMeal.addActionListener(deleteMealActionListener);
		
			//Provide minimum sizes for the two components in the split pane
			Dimension minimumSize = new Dimension(100, 50);
			managingMealList.setMinimumSize(minimumSize);
			mealList.setMinimumSize(minimumSize);
			
			managingMealList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			managingMealList.setLayoutOrientation(JList.VERTICAL);
			managingMealList.setVisibleRowCount(-1);
			managingMealList.addListSelectionListener(selectionListener);
			
					
			
			south.add(newMeal, BorderLayout.CENTER);
			south.add(changeMeal, BorderLayout.WEST);
			south.add(deleteMeal, BorderLayout.EAST);
				
			
			
			mealManaging.add(south, BorderLayout.SOUTH);
			mealManaging.add(scrollableList, BorderLayout.CENTER);
	
	}
	
	public void setButtonEnabled() {
		acceptOrder.setEnabled(false);
		specificationMeal.setEnabled(false);
		ready.setEnabled(false);
		if(!defaultOrderList.isSelectionEmpty()) {
			String selectedOrder = (String) defaultOrderList.getSelectedValue();
			String nr = selectedOrder.substring(selectedOrder.lastIndexOf(' ') + 1);
			
			int orderNr = Integer.parseInt(nr);
			
			specificationMeal.setEnabled(true);
			if((manager.searchOrder(orderNr)).getStatus().equals("geplaatst")) {
				acceptOrder.setEnabled(true);
				
				if((manager.searchOrder(orderNr)).getStatus().equals("geaccepteerd")) {
					ready.setEnabled(true);
				}	
			}
		}
	}
	
	ListSelectionListener selectionListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				mealListModel.clear();
				
				String selectedOrder = (String) defaultOrderList.getSelectedValue();
				String nr = selectedOrder.substring(selectedOrder.lastIndexOf(' ') + 1);
				
				int orderNr = Integer.parseInt(nr);
				
				ArrayList<Product> products = manager.getProducts(orderNr);
				
				for(Product product: products) {
					mealListModel.addElement(product.getName());
				}
			}
			
			setButtonEnabled();
		}
	};
	
	ActionListener acceptActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!defaultOrderList.isSelectionEmpty()) {
				String selectedOrder = (String) defaultOrderList.getSelectedValue();
				String nr = selectedOrder.substring(selectedOrder.lastIndexOf(' ') + 1);
				
				int orderNr = Integer.parseInt(nr);
				System.out.println(orderNr);
				manager.acceptOrder(orderNr);
				
				setButtonEnabled();
			}
		}
	};
	
	ActionListener readyActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!defaultOrderList.isSelectionEmpty()) {
				String selectedOrder = (String) defaultOrderList.getSelectedValue();
				String nr = selectedOrder.substring(selectedOrder.lastIndexOf(' ') + 1);
				
				int orderNr = Integer.parseInt(nr);
				System.out.println(orderNr);
				manager.readyOrder(orderNr);
				
				setButtonEnabled();
			}
		}
	};

	ActionListener specificationActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!mealList.isSelectionEmpty()) {
				String selectedMeal = (String) mealList.getSelectedValue();
				String productName = selectedMeal.substring(selectedMeal.lastIndexOf(' ') + 1);
				
				specificationMenu(productName);
			}
		}
		
	};
	
	ActionListener newMealActionListener = new ActionListener(){
	public void actionPerformed(ActionEvent e){
		if(!mealList.isSelectionEmpty()) {
			String selectedMeal = (String) mealList.getSelectedValue();
			String productName = selectedMeal.substring(selectedMeal.lastIndexOf(' ') + 1);
			
			newMealMenu(productName);
		}
	}
	};
	
	ActionListener changeMealActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(!mealList.isSelectionEmpty()) {
				String selectedMeal = (String) mealList.getSelectedValue();
				String productName = selectedMeal.substring(selectedMeal.lastIndexOf(' ') + 1);
				
				changeMealMenu(productName);
			}
		}
		};
		
		ActionListener deleteMealActionListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
			};
	
	
	public void specificationMenu(String productName) {
Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.getContentPane().removeAll();
		frame.setTitle("Gerechtspecificaties");
				
		JPanel paneel = new SpecificationGUI(manager, frame, productName);
		
		frame.setContentPane(paneel);
		frame.setVisible(true);
		frame.validate();
		frame.repaint();
		
		
		
	}
	
	public void newMealMenu(String productName) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				frame.getContentPane().removeAll();
				frame.setTitle("Gerecht toevoegen");
						
				JPanel paneel = new SpecificationGUI(manager, frame, productName);
				
				frame.setContentPane(paneel);
				frame.setVisible(true);
				frame.validate();
				frame.repaint();
	
	
	}
	
	public void changeMealMenu(String productName) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				frame.getContentPane().removeAll();
				frame.setTitle("Gerecht wijzigen");
						
				JPanel paneel = new SpecificationGUI(manager, frame, productName);
				
				frame.setContentPane(paneel);
				frame.setVisible(true);
				frame.validate();
				frame.repaint();
	
				
	}
				
}
