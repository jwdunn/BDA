package com.BeerDropper.www;
/**
 * Class representing the details of the ordered items
 * @author gasparobimba
 *
 */
public class ItemsOrdered {
	
	private String item_name;
	int quantity;
	private final double UNIT_PRICE=9.99;

	public ItemsOrdered(String product_name, int quantity) {
		//super();
		this.item_name = product_name;
		this.quantity = quantity;
	
	}

	public String getProduct_name() {
		return item_name;
	}

	public void setProduct_name(String product_name) {
		this.item_name = product_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal(){
		return getQuantity()*UNIT_PRICE;
	}

	public String toString(){
		return this.quantity+"of "+this.getProduct_name()+"\t@"+UNIT_PRICE+"\n";
	}
	
	
}
