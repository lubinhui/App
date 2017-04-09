package com.biyesheji.android;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;

import com.biyesheji.android.network.NetworkManager;

public class CommandApplication extends Application {
    private static CommandApplication app;
    public Map<String,String> userMap;

    public static CommandApplication getInstance() {
        return app;
    }
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    	NetworkManager.getInstance().init(this);
    	userMap=new HashMap<String, String>();
    	app = this;
    	initMap();
    }
	private void initMap() {
		userMap.put("admin", "123456");
	}
}
