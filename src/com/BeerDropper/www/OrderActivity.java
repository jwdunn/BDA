package com.BeerDropper.www;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class OrderActivity extends Activity implements OnClickListener {

	ImageView imageCaptured;
	
	/* get data from previous activity*/
	String phoneNumber=null;
	String address=null;
	String userName=null;
	String userID=null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*Get user's information from last intent
		 * @author - gaspar obimba
		 * */
	      Bundle extras = getIntent().getExtras();  
	      if(extras !=null) {
	          userID = extras.getString("userID");
	          userName = extras.getString("userName");
	          phoneNumber=extras.getString("phoneNumber");
	          address=extras.getString("address");
	      }
		
		//Drop Off
		View dropoffbutton = findViewById(R.id.dropoff);
        dropoffbutton.setOnClickListener(this);

		//Navigation Button
		Button nav = (Button)findViewById(R.id.get_directions);
		nav.setOnClickListener(this);

		//Call Button
		Button call = (Button)findViewById(R.id.get_call);
		call.setOnClickListener(this);

	}

	//Executes when the camera button is clicked
		@Override
		public void onClick(View v) {

	    	switch (v.getId()) {
	    	case R.id.dropoff:
	    		Intent j = new Intent(this, DropOff.class);
	    		startActivity(j);
	    		break;
	    	case R.id.get_directions:
	    		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+address)); 
	    		startActivity(i);
	    		break;
	    	case R.id.get_call:
	    		Uri number = Uri.parse("tel:" + phoneNumber);
				Intent dial = new Intent(Intent.ACTION_DIAL, number);
				startActivity(dial);
				startActivity(Intent.createChooser(dial, "select dialer"));
    			//startActivity(new Intent(this,ManualSignActivity.class));

				break;	
	    	}

	};

}