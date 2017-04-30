package com.biyesheji.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.biyesheji.android.R;
import com.biyesheji.android.model.UserModel;
import com.biyesheji.android.utils.InputUtil;
import com.biyesheji.android.utils.OutputUtil;

public class RegisterActivity extends Activity implements OnClickListener {

	private EditText nickName;
	private EditText phone;
	private EditText passWord;
	private EditText againPassWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}

	private void initView() {
		nickName = (EditText) findViewById(R.id.nickName);
		phone = (EditText) findViewById(R.id.phone);
		passWord = (EditText) findViewById(R.id.password);
		againPassWord = (EditText) findViewById(R.id.againPassword);
		TextView register = (TextView) findViewById(R.id.register);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register:
			String nichNamestr=nickName.getEditableText().toString();
			String phonestr=phone.getEditableText().toString();
			String passWostrrd=passWord.getEditableText().toString();
			String agaistrnPassWord=againPassWord.getEditableText().toString();
			if(TextUtils.isEmpty(nichNamestr)){
				Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if(TextUtils.isEmpty(phonestr)&&phonestr.length()!=11){
				Toast.makeText(this, "手机号不能为空或者手机号格式不正确", Toast.LENGTH_SHORT).show();
				return;
			}
			if(TextUtils.isEmpty(passWostrrd)){
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if(TextUtils.isEmpty(agaistrnPassWord)){
				Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
				return;
			}
			if(!passWostrrd.equals(agaistrnPassWord)){
				Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
				return;
			}
			UserModel userModel = new UserModel();
			userModel.userName=nichNamestr;
			userModel.passWord=passWostrrd;
			userModel.phone=phonestr;
			InputUtil<UserModel> userList = new InputUtil<UserModel>();
			List<UserModel> users = userList.readListFromSdCard("userList");
			if(users==null){
				users = new ArrayList<UserModel>();
			}
			users.add(userModel);
			OutputUtil<UserModel> outPut = new OutputUtil<UserModel>();
			outPut.writeListIntoSDcard("userList", users);
			Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
			finish();
			break;

		default:
			break;
		}
	}
}
