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
import com.biyesheji.android.model.DingdanModel;

public class DingdanDetailActivity extends Activity {
	private ListView mListView;
	private List<DingdanModel> mList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dingdan_detail);
		mListView=(ListView) findViewById(R.id.listview);
		mList=new ArrayList<DingdanModel>();
		initData();
	}
	private void initData() {
		
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			return null;
		}
		
	}

}
