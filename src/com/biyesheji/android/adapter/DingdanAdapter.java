package com.biyesheji.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.biyesheji.android.R;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.network.NetworkManager;

public class DingdanAdapter extends BaseAdapter {

	private List<ProductData> mList;
	private Context mContext;
	public DingdanAdapter(Context context ,List<ProductData> list){
		this.mList = list;
		this.mContext = context;
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
		ViewHolder holder = null;
		if (convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.item_dingdan_list, null);
			holder.imageView = (NetworkImageView) convertView.findViewById(R.id.imageView);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		ProductData productData = mList.get(position);
		NetworkManager.getInstance().setImageUrl(holder.imageView, productData.getImgUrl());
		holder.name.setText(productData.getInfo());
		holder.price.setText(productData.getPrice()+"元");
		return convertView;
	}
	static class ViewHolder{
		NetworkImageView imageView;
		TextView name;
		TextView price;
	}

}
