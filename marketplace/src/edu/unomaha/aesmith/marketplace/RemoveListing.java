package edu.unomaha.aesmith.marketplace;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveListing
 */
@WebServlet("/RemoveListing")
public class RemoveListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
		String idDelete = request.getParameter("id");
		
		Connection myConnection = (Connection) getServletContext().getAttribute("DBConnect");
		Statement stmnt = null;
		try {
			
			stmnt = myConnection.createStatement();
			StringBuilder sql = new StringBuilder("delete from Listings where id = ").append(idDelete);
			stmnt.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// back to home page
		response.sendRedirect("ArtifactMarketplace.jsp");
		return;
		
	}

}
