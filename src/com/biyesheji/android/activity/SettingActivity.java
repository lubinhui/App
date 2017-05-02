package com.biyesheji.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biyesheji.android.R;
import com.biyesheji.android.utils.MyUtils;
import com.biyesheji.android.utils.PreferenceHelper;

public class SettingActivity extends Activity implements OnClickListener {
	private RelativeLayout userInfo;
	private RelativeLayout userSafe;
	private TextView logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}

	private void initView() {
		 userInfo=(RelativeLayout) findViewById(R.id.userinfo);
		 userSafe=(RelativeLayout) findViewById(R.id.usersafe);
		 logout =(TextView) findViewById(R.id.logout);
		 userInfo.setOnClickListener(this);
		 userSafe.setOnClickListener(this);
		 logout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.userinfo:
			MyUtils.jumpActivity(this, UserInfoActivity.class);
			break;
		case R.id.usersafe:
			
			break;
		case R.id.logout:
			MyUtils.jumpActivity(this, LoginActivity.class);
			//PreferenceHelper.clean(this, "userinfo");
			PreferenceHelper.clean(this, "userModel");
			finish();
			break;

		default:
			break;
		}
	}

}
