package com.BeerDropper.www;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Order extends Activity implements OnClickListener {

	ImageView imageCaptured;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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
	    		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=Boston+MA")); 
	    		startActivity(i);
	    		break;
	    	case R.id.get_call:
	    		Uri number = Uri.parse("tel:" + "6172493645");
				Intent dial = new Intent(Intent.ACTION_DIAL, number);
				startActivity(dial);
				startActivity(Intent.createChooser(dial, "select dialer"));
				break;	
	    	}
		
	};

}




