package com.biyesheji.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.biyesheji.android.MainActivity;
import com.biyesheji.android.R;
import com.biyesheji.android.utils.MyUtils;

public class DingDanSuccessActivity extends Activity {
	private TextView backHome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dingdan_success);
		initView();
	}
	public void initView(){
		backHome = (TextView) findViewById(R.id.backHome);
		backHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyUtils.jumpActivity(DingDanSuccessActivity.this, MainActivity.class);
			}
		});
	}
}
