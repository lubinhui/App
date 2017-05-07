package com.biyesheji.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.biyesheji.android.R;
import com.biyesheji.android.adapter.DingdanAdapter;
import com.biyesheji.android.model.DingdanModel;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.utils.InputUtil;

public class DingdanDetailActivity extends Activity {
	private ListView mListView;
	private List<ProductData> mList;
	private DingdanAdapter mDingdanAdapter;
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
		mList = inputUtil.readListFromSdCard("dingdan");
		if (mList == null){
			mList = new ArrayList<ProductData>();
		}else{
			mDingdanAdapter = new DingdanAdapter(this, mList);
		}
		mListView.setAdapter(mDingdanAdapter);
	}

}
