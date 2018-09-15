package edu.unomaha.aesmith.marketplace;
//import java.io.Serializable;
import java.sql.Timestamp;

public class Listing {
	
//	private static final long serialVersionUID = 8843983427634673489L;
	
	private int id;
	private Timestamp created;
	
	private String listingName;
	private String listingPrice;
	private String listingImgURL;
	private String listingDescription;
	private String listingSeller;
	private String listingEmail;

	public Listing(int id, Timestamp created, String name, String price, String img, String description, String seller, String email) {
		this(name, price, img, description, seller, email);
		this.id = id;
		this.created = created;
	}
	public Listing(String name, String price, String img, String description, String seller, String email) {
		this.listingName = name;
		this.listingPrice = price;
		this.listingImgURL = img;
		this.listingDescription = description;
		this.listingSeller = seller;
		this.listingEmail = email;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getListingName() {
		return listingName;
	}
	public void setListingName(String listingName) {
		this.listingName = listingName;
	}
	public String getListingPrice() {
		return listingPrice;
	}
	public void setListingPrice(String listingPrice) {
		this.listingPrice = listingPrice;
	}
	public String getListingImgURL() {
		return listingImgURL;
	}
	public void setListingImgURL(String listingImgURL) {
		this.listingImgURL = listingImgURL;
	}
	public String getListingDescription() {
		return listingDescription;
	}
	public void setListingDescription(String listingDescription) {
		this.listingDescription = listingDescription;
	}
	public String getListingSeller() {
		return listingSeller;
	}
	public void setListingSeller(String listingSeller) {
		this.listingSeller = listingSeller;
	}
	public String getListingEmail() {
		return listingEmail;
	}
	public void setListingEmail(String listingEmail) {
		this.listingEmail = listingEmail;
	}
	@Override
	public String toString() {
		return(this.listingName + ": " + this.listingDescription);
	}
}
