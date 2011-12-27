package com.BeerDropper.www;

import java.util.ArrayList;
/**
 * (C) BeerDropper LLC
 * @author gasparobimba
 *
 */
public class Retailer {
	private String name;
	private int RetailerID;
	private String address;
	private int phone;
	private ArrayList<Order> orders=new ArrayList<Order>();
	
	/**
	 * Constructor
	 * @param name
	 * @param id
	 * @param address
	 * @param phone
	 * @param orders
	 */
	public Retailer(int id, String name,String address, int phone, ArrayList<Order> orders) {
		super();
		this.setName(name);
		this.setId(id);
		this.setAddress(address);
		this.setPhone(phone);
		this.orders=orders;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.RetailerID = id;
	}

	/**
	 * @return the id
	 */
	public int getRetailerID() {
		return RetailerID;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Order order) {
		this.orders.add(order);
	}

	/**
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	
}
