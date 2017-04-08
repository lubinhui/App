package com.biyesheji.android.activity;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.biyesheji.android.CommandApplication;
import com.biyesheji.android.MainActivity;
import com.biyesheji.android.R;
import com.biyesheji.android.utils.MyUtils;
import com.biyesheji.android.utils.PreferenceHelper;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText et_username;
	private EditText et_password;
	private TextView tv_login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		et_username=(EditText) findViewById(R.id.et_username);
		et_password=(EditText) findViewById(R.id.et_password);
		tv_login=(TextView) findViewById(R.id.tv_login);
		tv_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_login:
			String username=et_username.getEditableText().toString().trim();
			String password=et_password.getEditableText().toString().trim();
			if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
				Toast.makeText(this, "账号密码不能为空。。", Toast.LENGTH_LONG).show();
				return;
			}
			HashMap<String,String> map=(HashMap<String, String>) CommandApplication.getInstance().userMap;
			String pwd = map.get(username);
			if(TextUtils.isEmpty(pwd)){
				Toast.makeText(this, "输入的账号或密码错误", Toast.LENGTH_LONG).show();
				return;
			}
			//bundle.putString("username", username);
			//bundle.putBoolean("islogin", true);
			MyUtils.jumpActivity(this, MainActivity.class);
			PreferenceHelper.write(this, "userinfo", "username", username);
			finish();
		default:
			break;
		}
	}

}
