package com.biyesheji.android.activity;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.biyesheji.android.CommandApplication;
import com.biyesheji.android.MainActivity;
import com.biyesheji.android.R;
import com.biyesheji.android.model.Global;
import com.biyesheji.android.model.UserModel;
import com.biyesheji.android.utils.InputUtil;
import com.biyesheji.android.utils.MyUtils;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText et_username;
	private EditText et_password;
	private TextView tv_login;
	private TextView regist;
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
		regist = (TextView) findViewById(R.id.regist);
		tv_login.setOnClickListener(this);
		regist.setOnClickListener(this);
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
			//HashMap<String,String> map=(HashMap<String, String>) CommandApplication.getInstance().userMap;
			//String pwd = map.get(username);
			/*if(TextUtils.isEmpty(pwd)){
				Toast.makeText(this, "输入的账号或密码错误", Toast.LENGTH_LONG).show();
				return;
			}*/
			//bundle.putString("username", username);
			//bundle.putBoolean("islogin", true);
			InputUtil<UserModel> input = new InputUtil<UserModel>();
			List<UserModel> userList = input.readListFromSdCard("userList");
			for (UserModel userModel : userList) {
				if(userModel.userName.equals(username)||userModel.phone.equals(username)){
					if(userModel.passWord.equals(password)){
						MyUtils.jumpActivity(this, MainActivity.class);
						Global.isLogin=true;
						finish();
					}else{
						Toast.makeText(this, "密码不对", Toast.LENGTH_SHORT).show();
					}
					return;
					
				}
				Toast.makeText(this, "该用户不存在", Toast.LENGTH_SHORT).show();
				return;
			}
			
			//PreferenceHelper.write(this, "userinfo", "username", username);
			break;
		case R.id.regist:
			MyUtils.jumpActivity(this, RegisterActivity.class);
			break;
		default:
			break;
		}
	}

}
