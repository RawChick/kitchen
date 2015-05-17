package presentation;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import businesslogic.OrderManager;

public class KitchenGUI extends JPanel {
	private OrderManager manager;
	private JFrame frame;
	
	private JSplitPane splitPane;
	private DefaultListModel orderListModel = new DefaultListModel();
	private JList orderList = new JList(orderListModel);
	
	private DefaultListModel mealListModel = new DefaultListModel();
	private JList mealList = new JList(mealListModel);
	private JButton acceptOrder;
	
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
		JComponent supply = new JPanel();
		JComponent mealManaging = new JPanel();
		
		createOrderOverview(orderOverview);
		createSupply(supply);
		createMealManaging(mealManaging);
		
		tabbedPane.addTab("Bestellingsoverzicht", orderOverview);
		tabbedPane.addTab("Voorraad", supply);
		tabbedPane.addTab("Gerechtenbeheer", mealManaging);
		
		add(tabbedPane);
	}
	
	public void createOrderOverview(JComponent orderOverview) {
		orderOverview.setLayout(new BorderLayout(10, 10));
		orderOverview.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel west = new JPanel();
		west.setLayout(new FlowLayout());
		
		acceptOrder = new JButton("Accepteren");
		
		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		orderList.setMinimumSize(minimumSize);
		mealList.setMinimumSize(minimumSize);
		
		orderList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		orderList.setLayoutOrientation(JList.VERTICAL);
		orderList.setVisibleRowCount(-1);
		orderList.addListSelectionListener(selectionListener);
		
		orderListModel.addElement("Item 1");
		orderListModel.addElement("Item 2");
		orderListModel.addElement("Item 3");
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, orderList, mealList);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(100);
		
		west.add(acceptOrder);
		
		orderOverview.add(west, BorderLayout.WEST);
		orderOverview.add(splitPane, BorderLayout.CENTER);
	}
	
	public void createSupply(JComponent supply) {
		
	}
	
	public void createMealManaging(JComponent mealManaging) {
		
	}
	
	ListSelectionListener selectionListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				mealListModel.clear();
				
				String currentOrder = (String) orderList.getSelectedValue();
				mealListModel.addElement(currentOrder);
			}
		}
	};
}
