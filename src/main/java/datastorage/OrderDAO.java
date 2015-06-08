package datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import domain.Product;
import domain.Order;

public class OrderDAO {
	public ArrayList<Order> retrieveOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();

		// First open a database connnection
		DatabaseConnection connection = new DatabaseConnection();
		if (connection.openConnection()) {
			// If a connection was successfully setup, execute the SELECT
			// statement.
			ResultSet resultset = connection
					.executeSQLSelectStatement("SELECT * FROM order WHERE status = 1 OR status = 2");

			if (resultset != null) {
				try {
					if (resultset.next()) {
						int orderIDFromDb = resultset.getInt("ID");
						int tableNrFromDb = resultset.getInt("table_number");
						
						/*String statusFromDb = resultset.getString("status");
						date dateFromDb = resultset.getDate("date_placed");*/
						
						/*int billIDFromDb = resultset.getLong("bill_ID");
						int staffIDFromDb = resultset.getTime("staff_ID");*/

						Order order = new Order(tableNrFromDb, orderIDFromDb, "geplaatst", new Date(01-01-2015));
						
						orders.add(order);
					}
				} catch (SQLException e) {
					System.out.println(e);
					Order order = null;
				}
			}
			// else an error occurred leave 'member' to null.

			// We had a database connection opened. Since we're finished,
			// we need to close it.
			connection.closeConnection();
		}

		return orders;
	}
}
