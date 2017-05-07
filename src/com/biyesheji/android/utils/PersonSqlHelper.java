package com.biyesheji.android.utils;
  
import android.content.Context;  
import android.content.ContentValues;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteOpenHelper;  
import android.database.sqlite.SQLiteDatabase;  
  
public class PersonSqlHelper extends SQLiteOpenHelper {  
    private static final String DATABASE_NAME="personList.db";//数据库名称  
    private static final int SCHEMA_VERSION=1;//版本号,则是升级之后的,升级方法请看onUpgrade方法里面的判断  
      
    public PersonSqlHelper(Context context) {//构造函数,接收上下文作为参数,直接调用的父类的构造函数  
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);  
    }  
      
    @Override  
    public void onCreate(SQLiteDatabase db) {//创建的是一个午餐订餐的列表,id,菜名,地址等等  
        db.execSQL("CREATE TABLE person (personid integer primary key autoincrement, username varchar(20), password varchar(20));");  
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
    	if (oldVersion==1 && newVersion==2) {//升级判断,如果再升级就要再加两个判断,从1到3,从2到3  
            //db.execSQL("ALTER TABLE restaurants ADD phone TEXT;");  
        }  
    }  
  
    public Cursor getAll(String where, String orderBy) {//返回表中的数据,where是调用时候传进来的搜索内容,orderby是设置中传进来的列表排序类型  
        StringBuilder buf=new StringBuilder("SELECT personid, username, password FROM person");  
          
        if (where!=null) {  
            buf.append(" WHERE ");  
            buf.append(where);  
        }  
          
        if (orderBy!=null) {  
            buf.append(" ORDER BY ");  
            buf.append(orderBy);  
        }  
          
        return(getReadableDatabase().rawQuery(buf.toString(), null));  
    }  
      
    public Cursor getByUsername(String username) {//根据点击事件获取id,查询数据库  
        String[] args={username};  
  
        return(getReadableDatabase()  
                        .rawQuery("SELECT password FROM person WHERE username=?",  
                                            args));  
    }  
      
    //插入新的一列
    public void insert(String username, String password) {  
        ContentValues cv=new ContentValues();  
                      
        cv.put("username", username);  
        cv.put("password", password);  
          
        getWritableDatabase().insert("person", "lalal", cv);
    }  
    
    public void add(String username, String password) {
    	getWritableDatabase().execSQL("insert into person (username,password) values (?,?)",
                new Object[] { username, password });
    	getWritableDatabase().close();
    }

      
    public void update(String username, String password) {  
        ContentValues cv=new ContentValues();  
        String[] args={password};  
          
        cv.put("password", password);  
          
        getWritableDatabase().update("person", cv, "username=?", args);  
    }  
      
    public String getUserName(Cursor c) {  
        return(c.getString(1));  
    }  
      
    public String getPassword(Cursor c) {  
        return(c.getString(2));  
    }  
         
}  
