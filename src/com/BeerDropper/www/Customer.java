package com.BeerDropper.www;

import java.util.ArrayList;

/**
 * Customer class reperenting details about the customer placing an order
 * @author gasparobimba
 *
 */
public class Customer {

		private String name;
		private int customerID;
		private String address;
		private int phone;
		ArrayList<Order> orders;
		
		/**
		 * Constructor for the Customer
		 * @param name- name of the customer
		 * @param id - system id for the customer
		 * @param address- address of the customer
		 * @param phone - his phone number
		 * @param orders - a list of orders he has placed using beerdropper.com
		 */
		public Customer( int id,String name, String address, int phone, ArrayList<Order> orders) {
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
			this.customerID = id;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return customerID;
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

