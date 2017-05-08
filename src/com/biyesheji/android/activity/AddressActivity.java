package com.biyesheji.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.biyesheji.android.R;
import com.biyesheji.android.adapter.AddressAdapter;
import com.biyesheji.android.utils.InputUtil;
import com.biyesheji.android.utils.MyUtils;

public class AddressActivity extends Activity {

	private TextView addAddress;
	private List<String> list = new ArrayList<String>();
	private ListView listView;
	private AddressAdapter mAddressAdapter;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_add);
		initView();
		initData();
	}

	private void initData() {
		InputUtil<String> address = new InputUtil<String>();
		List<String> addressList = address.readListFromSdCard("address");
		if(addressList != null){
			list = addressList;
		}
		mAddressAdapter = new AddressAdapter(this, list);
		listView.setAdapter(mAddressAdapter);
	}

	private void initView() {
		addAddress = (TextView) findViewById(R.id.addAddress);
		listView = (ListView) findViewById(R.id.listView);
		
		
		addAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyUtils.jumpActivity(AddressActivity.this, AddAddressActivity.class);
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				bundle.putString("address", list.get(position));
				getIntent().putExtras(bundle);
				AddressActivity.this.setResult(1,getIntent());
				finish();
				
			}
		});
	}
	@Override
	protected void onResume() {
		super.onResume();
		InputUtil<String> inputUtil = new InputUtil<String>();
		if(list != null&&list.size()>0){
			list.clear();
			list = inputUtil.readListFromSdCard("address");
			mAddressAdapter = new AddressAdapter(this, list);
			listView.setAdapter(mAddressAdapter);
		    mAddressAdapter.notifyDataSetChanged();
		}
	    
		
	}
}
