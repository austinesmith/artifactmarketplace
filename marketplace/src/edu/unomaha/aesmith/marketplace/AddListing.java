package edu.unomaha.aesmith.marketplace;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddListing
 */
@WebServlet("/AddListing")
public class AddListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection myConnection = (Connection) getServletContext().getAttribute("DBConnect");
		Statement stmnt = null;
		try {
			
			stmnt = myConnection.createStatement();
			StringBuilder sql = new StringBuilder("insert into listings (listingName, listingPrice, ") 
			.append("listingDescription, listingSeller, listingEmail) values ('")
			.append(request.getParameter("itemName"))
			.append("', '").append(request.getParameter("itemPrice"))
			.append("', '").append(request.getParameter("description"))
			.append("', '").append(request.getParameter("sellerName"))
			.append("', '").append(request.getParameter("sellerEmail"))
			.append("')");
			System.out.println(sql);
			stmnt.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// back to home page
		response.sendRedirect("ArtifactMarketplace.jsp");
		return;
	}
}
