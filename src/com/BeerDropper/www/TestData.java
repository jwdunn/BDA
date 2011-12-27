package com.BeerDropper.www;

import java.util.ArrayList;

public class TestData {

	private ArrayList<ItemsOrdered> list1=new ArrayList<ItemsOrdered>();
	private ArrayList<ItemsOrdered> list2=new ArrayList<ItemsOrdered>();
	private ArrayList<Customer> cust=new ArrayList<Customer>();
	private ArrayList<Retailer> retailers=new ArrayList<Retailer>();
	private static Order order1,order2,order3,order4,order5,order6,order8,order7;

	public TestData(){



		//create items

		ItemsOrdered item0 = new ItemsOrdered("Jameson",4); list1.add(item0);
		ItemsOrdered item9 = new ItemsOrdered("Jameson",7);list2.add(item9);
		ItemsOrdered item1 = new ItemsOrdered("Feckin Irish Whiskey",3); list1.add(item1);
		ItemsOrdered item10 = new ItemsOrdered("Feckin Irish Whiskey",5);list2.add(item10);
		ItemsOrdered item2 = new ItemsOrdered("Seagrams 7",2); list1.add(item2);
		ItemsOrdered item11 = new ItemsOrdered("Seagrams 7",4);list2.add(item11);
		ItemsOrdered item3 = new ItemsOrdered("Seagrams VO",2); list1.add(item3);
		ItemsOrdered item12 = new ItemsOrdered("Seagrams VO",3);list2.add(item12);
		ItemsOrdered item4 = new ItemsOrdered("Canadian Club",1); list1.add(item4);
		ItemsOrdered item13 = new ItemsOrdered("Canadian Club",3);list2.add(item13);
		ItemsOrdered item5 = new ItemsOrdered("Jack Daniels",1); list1.add(item5);
		ItemsOrdered item14 = new ItemsOrdered("Jack Daniels",2);list2.add(item14);
		ItemsOrdered item6 = new ItemsOrdered("Gentlemans Jack",1); list1.add(item6);
		ItemsOrdered item15 = new ItemsOrdered("Gentlemans Jack",2);list2.add(item15);
		ItemsOrdered item7 = new ItemsOrdered("Makers Mark",1); list1.add(item7);
		ItemsOrdered item16 = new ItemsOrdered("Makers Mark",2);list2.add(item16);
		ItemsOrdered item8 = new ItemsOrdered("Knob Creek",1); list1.add(item8);
		ItemsOrdered item17 = new ItemsOrdered("Knob Creek",2);list2.add(item17);

		//create retailers
		Retailer retailer0 = new Retailer(10000,"charles St Liquors","Main Street",6175800,null);
		Retailer retailer1 = new Retailer(10001,"Liquor Land","South St.",6172167,null);
		Retailer retailer2 = new Retailer(10002,"Gordons","East St",6170729,null);
		Retailer retailer3 = new Retailer(10003,"Stephens","Central St",6177569,null);
		Retailer retailer4 = new Retailer(10004,"Downtown Wines and Spirits","West Ave,",6179511,null);
		Retailer retailer5 = new Retailer(10005,"MainStreet","North Cir",6174430,null);


		Customer customer0 = new Customer(20000,"John","",6171564,null);
		cust.add(customer0);
		Customer customer1 = new Customer(20001,"Gabriel","Main Street",6175131,null);
		cust.add(customer1);
		Customer customer2 = new Customer(20002,"Maxime","South St.",6178090,null);
		cust.add(customer2);
		Customer customer3 = new Customer(20003,"Alexandre","East St",6170502,null);
		cust.add(customer3);
		Customer customer4 = new Customer(20004,"Hugo","Central St",6177173,null);
		cust.add(customer4);
		Customer customer5 = new Customer(20005,"Noah","West Ave,",6170329,null);
		cust.add(customer5);
		Customer customer6 = new Customer(20006,"Thomas","North Cir",6174874,null);
		cust.add(customer6);
		Customer customer7 = new Customer(20007,"Antoine","",6175791,null);
		cust.add(customer7);
		Customer customer8 = new Customer(20008,"Louis","Main Street",6179308,null);
		cust.add(customer8);
		Customer customer9 = new Customer(20009,"Gaspar","South St.",6175602,null);
		cust.add(customer9);

		//customer 1 buys 3 items from retailer 1

		order1 =new Order(0, retailer0, customer1, list1, false);
		order2 =new Order(1, retailer0, customer3, list2, false);
		order3 =new Order(2, retailer0, customer2, list1, false);
		order4 =new Order(3, retailer0, customer4, list2, false);
		order5 =new Order(4, retailer0, customer5, list1, false);
		order6 =new Order(5, retailer0, customer6, list2, false);
		order7 =new Order(6, retailer0, customer9, list1, false);
		order8 =new Order(7, retailer0, customer8, list2, true);





	}	


	/**
	 * Scenario 1.
	 * @return arraylist of orders of retailer x
	 */

	public ArrayList<Order> getTestData() {
		ArrayList<Order> orders =new ArrayList<Order>();
		orders.add(order1);		
		orders.add(order2);		
		orders.add(order3);
		orders.add(order4);		
		orders.add(order5);		
		orders.add(order6);
		orders.add(order7);		
		orders.add(order8);
		return orders; 
	}
}