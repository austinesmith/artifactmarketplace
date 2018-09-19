package edu.unomaha.aesmith.marketplace;
//import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// dispose
		Connection myConnection = (Connection) sce.getServletContext().getAttribute("DBConnect");
		try {
			myConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		java.sql.Driver mySqlDriver;
		try {
			mySqlDriver = DriverManager.getDriver("jdbc:mysql://localhost:3306/");
			DriverManager.deregisterDriver(mySqlDriver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// confirm
		System.out.println("DB Close Successful");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Get creds
		ServletContext myContext = sce.getServletContext();
		String DBURL = myContext.getInitParameter("DBURL");
		String DBuser = myContext.getInitParameter("DBuser");
		String DBpassword = myContext.getInitParameter("DBpassword");
	
		// DB connect
		DBConnect myConnectionManager = null;
		try {
			myConnectionManager = new DBConnect(DBURL, DBuser, DBpassword);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// add connect info to app 
		myContext.setAttribute("DBConnect", myConnectionManager.getConnection());
	}

}
