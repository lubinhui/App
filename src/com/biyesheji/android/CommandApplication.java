package com.biyesheji.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.util.Log;

import com.biyesheji.android.model.UserModel;
import com.biyesheji.android.network.NetworkManager;
import com.biyesheji.android.utils.OutputUtil;

public class CommandApplication extends Application {
    private static CommandApplication app;
    //public Map<String,String> userMap;
    public List<UserModel> userList;
    public UserModel userModel ;
    public static CommandApplication getInstance() {
        return app;
    }
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    	NetworkManager.getInstance().init(this);
    	//userMap=new HashMap<String, String>();
    	userModel = new UserModel();
    	userList = new ArrayList<UserModel>();
    	app = this;
    	initMap();
    }
	private void initMap() {
		//userMap.put("admin", "123456");
		userModel.phone="18516106618";
		userModel.passWord="123456";
		userModel.userName="大白菜";
		userList.add(userModel);
		OutputUtil<UserModel> output = new OutputUtil<UserModel>();
		boolean listIntoSDcard = output.writeListIntoSDcard("userList", userList);
		Log.e("lbh", listIntoSDcard+"");
		
	}
}
