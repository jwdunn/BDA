package com.BeerDropper.www;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
/**
 * modification of code from android's APIDEMO 
 * This class creates the main view for the signature screen
 * @author gasparobimba
 *
 */
public class SignatureView extends View implements OnTouchListener {

	private static final String TAG = "DrawView";

	List<Point> points = new ArrayList<Point>();
	Paint paint = new Paint();
	Random gen;
	int col_mode;
	Point point;


	public SignatureView(Context context) {
		super(context);

		/* set default colour to white*/
		col_mode = 0;

		setFocusable(true);
		setFocusableInTouchMode(true);	
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);
	}

	// used to clear the screen
	public void clearPoints () {
		points.clear();
		forceRedraw();
	}

	/**
	 * Force view to redraw. Without this points aren't cleared until next action
	 */
	public void forceRedraw() {
		invalidate();
	}

	// used to set drawing colour
	public void changeColour (int col_in) {
		col_mode = col_in;
	}

	@Override
	public void onDraw(Canvas canvas) {
		// for each point, draw on canvas
		for (Point point : points) {
			point.draw(canvas, paint);
			Log.d(TAG, "pointcount: "+points.size());
		}
	}

	public boolean onTouch(View view, MotionEvent event) {
		int new_col = 0;
		if (col_mode >= 0) {
			switch (col_mode) {
			case 0 : {
				new_col = Color.BLACK;//default signature color
				break;
			}
			case 1 : {
				new_col = Color.BLUE;
				break;
			}

			case 2 : {
				new_col = Color.GREEN;
				break;
			}

			case 3 : {
				new_col = Color.RED;
				break;
			}
			case 4 : {
				new_col = Color.BLACK;
				break;
			}
			}
		} else {
			gen = new Random();
			new_col = gen.nextInt(4);
		}

		if(event.getAction() == MotionEvent.ACTION_MOVE) {
			point = new FriendlyPoint(event.getX(), event.getY(), new_col, points.get(points.size() - 1));	
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {	
			point = new Point(event.getX(), event.getY(), new_col);
		} else {
			return false;
		}
		points.add(point);
		forceRedraw();
		Log.d(TAG, "point: " + point);
		return true;
	}
}