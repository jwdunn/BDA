package com.BeerDropper.www;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.monoid.web.Resty;
import us.monoid.web.XMLResource;

import android.content.Context;
import android.util.Log;


public class OrderServerProxy  {

	Retailer curruser;// distributor or user currently using app
	NodeList nList;
	Context con;
	private final static String TAG =  OrderServerProxy.class.getSimpleName();;
	//Provider provide;

	public OrderServerProxy(){	
	}


	/**
	 *Gets new orders from the server.When a request to server is made: we need retailers id and tag="new"
	 * for new orders to be pulled from the webservice.
	 * Server receives info, checks whether the retailer has any new orders; if orders exist, 
	 * an xml response is returned with critical details about each order
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @author Gaspar Obimba
	 
	public ArrayList<Order> getNewOrders(String status) throws ParserConfigurationException, SAXException, IOException{
		
		ArrayList<Order> batch = new ArrayList<Order>();
		Resty r = new Resty();
		XMLResource usr1 = null;
	
		Log.i(TAG, "Loading new orders from server");
			try {
				usr1 = r.xml("http://beerdropper.com/find.xml?user="+curruser.getRetailerID()+"&status=new.xml");
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
		
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        String st = ""+usr1;
	        InputStream is = new ByteArrayInputStream(st.getBytes());
	        Document doc = dBuilder.parse(is);
	        doc.getDocumentElement().normalize();
	        nList = doc.getElementsByTagName("new");
			for(int k =0; k<nList.getLength(); k++){
		        Node nNode = nList.item(k);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Order order = new Order(k, "", null, null, "new");
					Element eElement = (Element) nNode;
					if(getTagValue("order-id", eElement)!=null){
						order.setId(Integer.parseInt(getTagValue("order-id", eElement)));
						Log.d(TAG,"order number" + order.getId());
						
						
						order.setCustomer(getTagValue("customer-id", eElement));
						order.setDetails(getTagValue("order-details", eElement));
						order.setDistributor(getTagValue("distributor-id", eElement));
						order.setOrderStatus(getTagValue("order-status", eElement));
						
						order.getTags();
						batch.add(order);
					}
				}
			}
		return batch; 
	}
	/**
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 
	public String getTagValue(String sTag, Element eElement) {
		NodeList list = eElement.getElementsByTagName(sTag);
		Node el = list.item(0);
		Log.d(TAG,"error on element");

		if (el != null) {
			NodeList nlList = el.getChildNodes();//get all children of the item node
			Node nValue = (Node) nlList.item(0);
			if (nValue != null){
				return nValue.getNodeValue();
			}
		}
		return null; 
	}
	
*/

	/**
	 * Gets a batch of items with unspecified department;
	 * @param batchsize - number of items needed
	 * @return - ArrayList of Items from random departments; 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 
	public ArrayList<Item> getBatch(int batchsize) throws ParserConfigurationException, SAXException, IOException{
		ArrayList<Item> batch = new ArrayList<Item>();
		Resty r = new Resty();
		XMLResource usr1 = null;
		if(provide.getCurUser().getID()==null){
			Log.i(TAG, "no user");
			try {
				usr1 = r.xml("http://vogueable.heroku.com/find.xml?dept="+1+"&batch="+batchsize+".xml");
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
		}
		else{
			Log.i(TAG, "User id " + provide.getCurUser().getID());
			try {
				usr1 = r.xml("http://vogueable.heroku.com/find.xml?user="+provide.getCurUser().getID()+"&batch="+batchsize+".xml");
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
		}
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        String st = ""+usr1;
        InputStream is = new ByteArrayInputStream(st.getBytes());
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
        nList = doc.getElementsByTagName("item");
		for(int k =0; k<nList.getLength(); k++){
	        Node nNode = nList.item(k);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Item it = new Item("it");
				Element eElement = (Element) nNode;
				it.setName(getTagValue("name", eElement));
				Log.d(TAG,"name" + it.getName());
				it.setImageFileString(getTagValue("img-url", eElement));
				it.setDescription(getTagValue("features", eElement));
				it.setLink(getTagValue("link-to-buy", eElement));
				it.setPrice(getTagValue("item-price", eElement));
				it.setBrand(getTagValue("brand", eElement));
				it.setID(getTagValue("id", eElement));
				it.getTags();
				batch.add(it);
			}
		}
		return batch; 
	}

	public ArrayList<Item> getBatch(int batchsize, ArrayList<String> depts) throws ParserConfigurationException, SAXException, IOException{
		Log.i(TAG, "calling getBatch with depts" + depts.size());
		if(depts.size()==0){
			return getBatch(batchsize);
		}
		else{
			ArrayList<Item> items = new ArrayList<Item>();
			for(String dept: depts){
				Log.i(TAG, "getting " +batchsize/depts.size() + " items for dept " + dept);
				items.addAll(getBatchbyDept(batchsize/depts.size(), dept));
			}
			return items;
		}
	}
*/
}

