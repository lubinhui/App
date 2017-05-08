package com.biyesheji.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.biyesheji.android.R;
import com.biyesheji.android.adapter.DingdanAdapter;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.model.UserModel;
import com.biyesheji.android.utils.InputUtil;

public class DingdanDetailActivity extends Activity {
	private ListView mListView;
	private List<ProductData> mList;
	private DingdanAdapter mDingdanAdapter;
	private List<ProductData> cartList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dingdan_detail);
		mListView=(ListView) findViewById(R.id.listview);
		initData();
	}
	private void initData() {
		InputUtil<ProductData> inputUtil = new InputUtil<ProductData>();
		InputUtil<UserModel> models = new InputUtil<UserModel>();
		mList = inputUtil.readListFromSdCard("dingdan");
		 UserModel userModel = models.readObjectFromSdCard("userModel");
		if(userModel!=null){
			cartList = inputUtil.readListFromSdCard(userModel.phone);
		}
		if (mList == null){
			mList = new ArrayList<ProductData>();
		}
		if(cartList == null){
			cartList = new ArrayList<ProductData>();
		}
		mList.addAll(cartList);
		mDingdanAdapter = new DingdanAdapter(this, mList);
		
		mListView.setAdapter(mDingdanAdapter);
	}

}
