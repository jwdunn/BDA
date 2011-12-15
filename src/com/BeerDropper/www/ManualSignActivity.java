package com.BeerDropper.www;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 * This is the main signature activity; creates a view that lets customer append signature to a delivery order
 * sends the image of signature to server- to be linked with user
 * @author gaspar obimba
 *
 */
public class ManualSignActivity extends Activity {
	private static final String TAG = "ManualSignature";
	SignatureView drawView;
	Bitmap mBitmap;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set full screen view
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// lock screen orientation (stops screen clearing when rotating phone)
		setRequestedOrientation(getResources().getConfiguration().orientation);

		//create a new view and enable caching so we can get signed image from this view
		drawView = new SignatureView(this);
		setContentView(drawView);
		drawView.setBackgroundColor(Color.WHITE);
		drawView.requestFocus();
		drawView.setDrawingCacheEnabled(true);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.signature_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {

		//clear screen
		case R.id.reset_label : {
			drawView.clearPoints();
			return true;
		}
		//change pen color

		case R.id.blue_label : {
			drawView.changeColour(1);
			return true;
		}

		case R.id.green_label: {
			drawView.changeColour(2);
			return true;
		}
		case R.id.red_label : {
			drawView.changeColour(3);
			return true;
		}

		case R.id.black_label : {
			drawView.changeColour(4);
			return true;
		}

		//save image TODO add image to server
		case R.id.save_label:{
			saveSignature();
			return true;
		}

		//no option selected
		default : {
			return true;
		}
		}
	}


	//
	/**
	 * save signature and append it to customer's order on server
	 * @author gaspar obimba
	 */
	public void saveSignature(){
		mBitmap = Bitmap.createBitmap(drawView.getDrawingCache());
		drawView.setDrawingCacheEnabled(false);
		if (mBitmap != null) {
			try {
				//TODO add server code here
				String path = Environment.getExternalStorageDirectory().toString();
				OutputStream fOut = null;
				File file = new File(path, "signature.jpg");
				fOut = new FileOutputStream(file);
				mBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
				fOut.flush();
				fOut.close();
				Log.e("ImagePath", "Image Path : " + MediaStore.Images.Media.insertImage( getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName()));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}