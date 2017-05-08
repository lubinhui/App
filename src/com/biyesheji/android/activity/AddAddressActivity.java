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
import com.biyesheji.android.utils.InputUtil;
import com.biyesheji.android.utils.OutputUtil;

public class AddAddressActivity extends Activity {

	private TextView tvSave;
	private EditText edtAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_adddress);
		initView();
	}

	private void initView() {
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		tvSave = (TextView) findViewById(R.id.tvSave);
		tvSave.setOnClickListener(new OnClickListener() {
			
			private String address;

			@Override
			public void onClick(View v) {
				address = edtAddress.getEditableText().toString();
				if(TextUtils.isEmpty(address)){
					Toast.makeText(AddAddressActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
					return;
				}
				InputUtil<String> inputUtil = new InputUtil<String>();
				List<String> addressList = inputUtil.readListFromSdCard("address");
				if(addressList == null){
					addressList = new ArrayList<String>();	
				}
				
				addressList.add(address);
				OutputUtil<String> outputUtil = new OutputUtil<String>();
				outputUtil.writeListIntoSDcard("address", addressList);
				finish();
			}
		});
	}
}
