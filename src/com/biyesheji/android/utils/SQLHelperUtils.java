package com.biyesheji.android.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库管理类
 * @author 002387
 *
 */
public class SQLHelperUtils extends SQLiteOpenHelper {
	
	private static final String name = "count"; //数据库名称  
	private static final int version = 1; //数据库版本  
	
	public SQLHelperUtils(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, username varchar(20), password varchar(20))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
