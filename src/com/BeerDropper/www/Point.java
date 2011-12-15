package com.BeerDropper.www;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Point {
	public final float x, y;
	public final int col;
	public final String TAG="Point.java";
	
	public Point(final float x, final float y, final int col) {
		this.x = x;
		this.y = y;
		this.col = col;
	}
	
	public void draw(final Canvas canvas, final Paint paint) {
		paint.setColor(col);
		canvas.drawCircle(x, y, 1, paint);
		Log.d(TAG,"drawing circle of radius 1" );
		
	}
	
	@Override
	public String toString() {
		return x + ", " + y + ", " + col;
	}
}
