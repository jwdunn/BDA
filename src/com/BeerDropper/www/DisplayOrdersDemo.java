
package com.BeerDropper.www;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Creates a list of orders that are pulled from the server
 * This list is displayed on the screen when activity is launched
 */
public class DisplayOrdersDemo extends ListActivity {

	private static List<Order> orderList;
	private OrderAdapter mOrderAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fancy_list);

		//Load List from the local database at this point
		TestData t1=new TestData();
		
		mOrderAdapter=new OrderAdapter(t1.getTestData());
		setListAdapter(mOrderAdapter);
	}

	public void onUseSelectorAsBackground(View v) {
		getListView().setSelector(R.drawable.list_selector);
		getListView().setDrawSelectorOnTop(false);
	}
	/**
	 * An order adapter that helps load content of database and display as a list
	 * @author gasparobimba
	 *
	 */
	private class OrderAdapter extends BaseAdapter {

		private ArrayList<Order> orderList;
	
		public OrderAdapter(ArrayList<Order> orders){
			orderList=orders;
		}
		@Override
		public int getCount() {
			return orderList.size();
		}

		@Override
		public String getItem(int position) {
			return "Order "+orderList.get(position).getOrderID()+"\n"+orderList.get(position).getCustomer().getName();
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override

		public View getView(int position, View convertView, ViewGroup parent) {

			TextView result;

			if (convertView == null) {
				result = (TextView) getLayoutInflater().inflate(R.layout.text_item, parent, false);
				// Set the text color to black here in order to reuse the
				// test_item layout. The preferred way to do it is obviously to
				// set it in the XML possibly via a text appearance.
				// Here we are using a plain Color but keep in mind you can use
				// a ColorStateList if you want your text color to change
				// depending on the current state of the itemview.
				result.setTextColor(Color.BLACK);
			} else {
				result = (TextView) convertView;
			}

			final String order = getItem(position);

			result.setText(order);
			result.setBackgroundResource(R.drawable.list_item_selector_normal);

			return result;
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		AlertDialog.Builder welcome = new AlertDialog.Builder(this);
		//welcome.setIcon(R.drawable.beerdrooper);
		
		final int p=position; //position of order
		welcome.setTitle(getString(R.string.view_order_details));
		welcome.setMessage(orderList.get(position).toString());
		welcome.setPositiveButton("Delete?",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Update server database about order status
				//remove from local database
				//update adapter
			//	orderList.remove(p);
	           mOrderAdapter.notifyDataSetChanged();
			}	
		});
		welcome.setNegativeButton("Process", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast msg = Toast.makeText(DisplayOrdersDemo.this, "BUSTED, UNDER 21", Toast.LENGTH_LONG);

				msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);

				msg.show();
			}
		}).show();
	}
	/**
	 *   Inflate menu items in the order activity
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.order_menu, menu);
		return true;
	}	

	/**
	 * Initiates action based on a menu item selected
	 * 
	 * @param v view 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.completed_orders_label:   
			break;
		case R.id.deleted_orders_label: 
			break;
		case R.id.new_orders_label:
			break;
		}
		return true;
	}
	   
}
