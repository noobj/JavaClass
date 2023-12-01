package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDemo {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// Step 1: Loading or registering ucanaccess
		// JDBC driver class
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {
			System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
		try {
			String msAccDB = "C:/Temp/Employee.accdb";
			String dbURL = "jdbc:ucanaccess://" + msAccDB;
			// Step 2.A: Create and get connection
			// using DriverManager class
			connection = DriverManager.getConnection(dbURL);
			// Step 2.B: Creating JDBC Statement
			statement = connection.createStatement();
			String insertSQL = "INSERT INTO EMPLOYEE (NAME, SALARY) " + "VALUES ('SIMON LI', 90000)";
			statement.executeUpdate(insertSQL);

			String updateSQL = "UPDATE EMPLOYEE SET SALARY = 120000 " + "WHERE NAME='SIMON LI'";
			statement.executeUpdate(updateSQL);

			resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Double salary = resultSet.getDouble(3);
				System.out.println("Employee #" + id + ": " + name + ", " + salary);
			}
			

			String deleteSQL = "DELETE FROM EMPLOYEE where NAME = 'SIMON LI'";
			statement.executeUpdate(deleteSQL);
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} finally {
			// Step 3: Closing database connection
			try {
				if (connection != null) {
					// cleanup resources, once
					// after processing
					resultSet.close();
					statement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}

	}

}
