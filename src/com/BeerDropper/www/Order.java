package com.BeerDropper.www;

import java.util.ArrayList;

/**
 * (C) BeerDropper LLC
 * 
 * handles customer's online orders
 * @author gasparobimba
 *
 */
public class Order {

	private int orderID;
	private Retailer retailer;
	private Customer customer;
	private ArrayList<ItemsOrdered> ItemsOrdered;
	private double OrderTotal=0.00;
	private boolean orderStatus;//initially order hasnt been accepted
	/**
	 * Constructor
	 * @param id
	 * @param curruser
	 * @param customer
	 * @param detail
	 * @param orderStatus
	 */
	public Order(int id, Retailer curruser, Customer customer,
			ArrayList<ItemsOrdered> detail, boolean orderStatus) {
		super();
		this.orderID = id;
		this.retailer = curruser;
		this.customer = customer;
		this.ItemsOrdered = detail;
		this.orderStatus = orderStatus;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<ItemsOrdered> getItemsOrdered() {
		return ItemsOrdered;
	}

	public void setItems(ItemsOrdered item) {
		if (getOrderStatus())
			this.ItemsOrdered.add(item);
	}

	public double getOrderTotal() {
		for(ItemsOrdered item:this.getItemsOrdered()){
			this.OrderTotal=OrderTotal+item.getTotal();
		}
		return OrderTotal;
	}

	public boolean getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String toString(){
		return		//System.out.printf(format, args)
			"Cust Name: "+this.getCustomer().getName()+"\n"+
					"Address  : "+this.getCustomer().getAddress()+"\n"+
					 this.getItemsOrdered().toString()+"\n"+
					"Total    : $"+this.getOrderTotal();			

	}

}
