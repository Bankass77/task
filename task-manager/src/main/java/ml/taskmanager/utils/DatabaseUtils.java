package ml.taskmanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class DatabaseUtils {

	private static Properties properties;
	static {

		properties = new Properties();

		InputStream inputStream = DatabaseUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {

			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a connection to the database
	 *
	 * @return A connection to the database
	 * @throws SQLException if there is an error connecting to the database
	 */
	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"),
				properties.getProperty("db.password"));
	}

	/**
	 * Executes a query on the database and returns a result set.
	 * 
	 * @param query The SQL query to execute
	 * @return A result set containing the results of the query
	 * @throws SQLException if there is an error executing the query
	 */
	public static ResultSet executeQuery(String query) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			return resultSet;

		} catch (SQLException e) {
			CloseResultSet(resultSet);
			CloseStatement(statement);
			closeConnection(connection);
			throw e;
		}
	}

	private static void closeConnection(Connection connection) {

		if (connection != null) {

			try {
				connection.close();
			} catch (SQLException e) {

			}
		}

	}

	private static void CloseStatement(Statement statement) {

		if (statement != null) {

			try {

				statement.close();
			} catch (SQLException ex) {

			}
		}

	}

	/**
	 * Closes a result set.
	 * 
	 * @param rs The result set to close
	 */
	private static void CloseResultSet(ResultSet resultSet) {

		if (resultSet != null) {

			try {
				resultSet.close();
			} catch (SQLException e) {

			}
		}

	}

}
