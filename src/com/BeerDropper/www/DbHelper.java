
package com.BeerDropper.www;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * This class creates a local database that will cache the current orders from the server
 * Data uploaded from the server will have all the Order information/details
 * *Order ID
 * *Name of customer,address,phone number
 * *Order Details/description
 * *Order status -received,pending,processing,delivered,cancelled,
 * 
 * @author gaspar obimba
 *
 */
public class DbHelper extends SQLiteOpenHelper {
	private static final String TAG = DbHelper.class.getSimpleName();

	public static final String DB_NAME = "beerdropper.db";
	public static final int DB_VERSION = 2;
	public static final String TABLE = "orders";
	public static final String C_ID = BaseColumns._ID; // Special for order id
	public static final String C_DISTRIBUTOR = "distributor_id";
	public static final String C_NAME = "user_name";
	public static final String C_ADDRESS = "user_address";
	public static final String C_DESCRIPTION = "order_descr";
	public static final String C_PHONE = "user_phone";
	public static final String C_STATUS = "order_status";
	Context context;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = String.format(
				"create table %s (%s int primary key, %s INT,%s TEXT, %s TEXT, %s INT,%s TEXT,%s TEXT)",
				TABLE, C_ID, C_DISTRIBUTOR,C_NAME, C_ADDRESS,C_PHONE, C_DESCRIPTION,C_STATUS);
		//sql = context.getString(R.string.sql);

		Log.d(TAG, "onCreate sql: "+sql);

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Typically you do ALTER TABLE... here
		db.execSQL("drop table if exists " + TABLE);
		Log.d(TAG, "onUpdate dropped table "+TABLE);
		this.onCreate(db);
	}

}

