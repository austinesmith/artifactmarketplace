<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.Connection" import="java.sql.PreparedStatement" import="java.sql.SQLException" 
import="java.sql.ResultSet" import="java.util.*" import="edu.unomaha.aesmith.marketplace.Listing" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Artifact Marketplace</title>
</head>

<%! 
	public edu.unomaha.aesmith.marketplace.Listing[] getListings() {
	// request
	Connection myConnection = (Connection) getServletContext().getAttribute("DBConnect");
	PreparedStatement mySQL = null;
	try {
		mySQL = myConnection.prepareStatement("select * from listings");
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// get results
	ResultSet myResults = null;
	try {
		myResults = mySQL.executeQuery();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// parse results
	List<Listing> arrList = new ArrayList<Listing>();
	
	try {

		while(myResults.next()){
			arrList.add(new Listing(myResults.getInt("id"), myResults.getTimestamp("create"), myResults.getString("listingName"), myResults.getString("listingPrice"), myResults.getString("listingImgURL"), myResults.getString("listingDescription"), myResults.getString("listingSeller"), myResults.getString("listingEmail")));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println("Should prints....");
	
	// dispose resources
	try {
		// TODO check nulls
		mySQL.close();
		myResults.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NullPointerException e) {
		// TODO: fix this
		e.printStackTrace();
	}
	
	Listing[] returnVal = new Listing[arrList.size()];
	
	for (int i=0; i<arrList.size(); i++) {
		returnVal[i] = arrList.get(i);		
	}
	
	return returnVal;
}
%>

<body bgcolor="#34495E" text="#ffffff">
<!-- Page Information -->
<h1>Artifact Marketplace</h1>
This is a application for anyone to come and submit vintage items are artifacts they want to sell.  To buy an item, just click the buy button at the bottom of the listings and the item will be removed from the listings.  To post an item for sale just travel to the bottom of the page, fill in the details, and click post.  All listings remain on the queue until they are bought with the earliest items displayed first.

<!-- Available Items Listing -->
<br>

<br>
<hr>
<br>

<h3>Current Listings</h3>
<% Listing[] currentListings = getListings(); %>
<% for (int i=1; i<currentListings.length+1; i++){ %>
<%=i %>:
<%=currentListings[i-1].getCreated() %><br>
<b><%=currentListings[i-1].getListingName() %></b><br>
<%=currentListings[i-1].getListingPrice() %><br>
<%=currentListings[i-1].getListingDescription() %><br>
<%=currentListings[i-1].getListingSeller() %><br>
<%=currentListings[i-1].getListingEmail() %><br>
<form action="RemoveListing" method="post">
	<input type="hidden" value="<%=currentListings[i-1].getId() %>" name="id" />
	<input type="submit" value="Purchase" /><br><br>
</form>

<% } %>

<!-- Post New Item -->

<br>
<hr>
<h3>Add New Listing</h3>
<form action="AddListing" method="post">
	Item Name: <input type="text" name="itemName" />
	<br>Price: <input type="text" name="itemPrice" />
	<br>Description: <input type="text" name="description" />
	<br>Seller Name: <input type="text" name="sellerName" />
	<br>Seller Email: <input type="text" name="sellerEmail" />
	<br><input type="submit" value="Post"/>
</form>

</body>
</html>

