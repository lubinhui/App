package com.biyesheji.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.biyesheji.android.R;

public class AddressAdapter extends BaseAdapter {
	private Context mContext;
	private List<String> mList;
	
	public AddressAdapter(Context context,List<String> list){
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.item_address_list, null);
			holder.address = (TextView) convertView.findViewById(R.id.address);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		String addressStr = mList.get(position);
		holder.address.setText(addressStr);
		return convertView;
	}
	static class ViewHolder{
		TextView address;
	}

}
