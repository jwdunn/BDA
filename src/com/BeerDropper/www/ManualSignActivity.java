/*
 * BeerDropper(c)
 */

package com.BeerDropper.www;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

/**
 * Main activity that lets a customer append their signature to a delivery during a drop off.
 * The signature is added to the customers record and send to the server for storage 
 * 	
 * @author gasparobimba
 *
 */
public class ManualSignActivity extends GraphicsActivity
								implements ColorPickerDialog.OnColorChangedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(0xFFFF0000);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(5);
	}

	private Paint   mPaint;
	private Path    mPath;
	private Bitmap  mBitmap;

	public void colorChanged(int color) {
		mPaint.setColor(color);
	}

	public class MyView extends View {

		private static final float MINP = 0.25f;
		private static final float MAXP = 0.75f;


		private Canvas  mCanvas;
		private Paint   mBitmapPaint;

		public MyView(Context c) {
			super(c);

			mPath = new Path();
			mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			super.onSizeChanged(w, h, oldw, oldh);
			mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBitmap);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(0xFFAAAAAA);

			canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

			canvas.drawPath(mPath, mPaint);
		}

		private float mX, mY;
		private static final float TOUCH_TOLERANCE = 1;

		private void touch_start(float x, float y) {
			mPath.reset();
			mPath.moveTo(x, y);
			mX = x;
			mY = y;
		}
		private void touch_move(float x, float y) {
			float dx = Math.abs(x - mX);
			float dy = Math.abs(y - mY);
			if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
				mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
				mX = x;
				mY = y;
			}
		}
		private void touch_up() {
			mPath.lineTo(mX, mY);
			// commit the path to our offscreen
			mCanvas.drawPath(mPath, mPaint);
			// kill this so we don't double draw
			mPath.reset();
		}


		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
			float y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touch_start(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				touch_move(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				touch_up();
				invalidate();
				break;
			}
			return true;
		}
	}

	private static final int COLOR_MENU_ID = Menu.FIRST;
	private static final int ERASE_MENU_ID = Menu.FIRST + 1;
	private static final int SAVE_MENU_ID = Menu.FIRST  + 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, COLOR_MENU_ID, 0, "Color").setShortcut('3', 'c');
		menu.add(0, ERASE_MENU_ID, 0, "Reset").setShortcut('5', 'z');
		menu.add(0, SAVE_MENU_ID, 0, "Verify").setShortcut('5', 'z');
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mPaint.setXfermode(null);
		mPaint.setAlpha(0xFF);

		switch (item.getItemId()) {

		case COLOR_MENU_ID:
			new ColorPickerDialog(this, this, mPaint.getColor()).show();
			return true;

		case ERASE_MENU_ID:
			mPath.reset();
		//	this.invalidate();
			clearSign();
			return true;

		case SAVE_MENU_ID:
			if (mBitmap != null) {
				try {
					String path = Environment.getExternalStorageDirectory().toString();
					OutputStream fOut = null;
					File file = new File(path, "screentest.jpg");
					fOut = new FileOutputStream(file);
					mBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
					//For now we store image locally.
					
					//INSERT CODE to send image to server
					fOut.flush();
					fOut.close();
					Log.e("ImagePath", "Image Path : " + MediaStore.Images.Media.insertImage( getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName()));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * method to clear bitMap
	 */
	public void clearSign(){

			mBitmap = null;
			mPath = null;
			mBitmap = Bitmap.createBitmap(480, 480, Bitmap.Config.ARGB_8888);
			mPath = new Path();

		}
}