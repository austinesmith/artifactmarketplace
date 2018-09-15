package edu.unomaha.aesmith.marketplace;
//import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private Connection connection;
	
	public DBConnect (String DBURL, String DBuser, String DBpassword) throws SQLException, ClassNotFoundException {
		// TODO
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(DBURL, DBuser, DBpassword);
	}

	public Connection getConnection() {
		return this.connection;
	}
}
