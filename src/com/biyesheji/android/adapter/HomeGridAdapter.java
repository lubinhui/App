package com.biyesheji.android.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.biyesheji.android.R;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.network.NetworkManager;
import com.bumptech.glide.Glide;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;

/**
 * 首页ListView中GridView的适配器
 */
public class HomeGridAdapter extends UniversalAdapter<ProductData> {

	private int screenWidth;
	private DisplayMetrics dm;
	private Activity act;

	public HomeGridAdapter(Context context, List<ProductData> datas,
			int itemLayoutId) {
		super(context, datas, itemLayoutId);

		dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		screenWidth = dm.widthPixels;
		this.act=(Activity)context;
	}

	@Override
	public void updateItem(ViewHolder holder, ProductData data) {
		// 动态修改GridView布局的宽高，解决不显示的问题
		LinearLayout layout = (LinearLayout) holder
				.getView(R.id.item_home_gridlayout);
		AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) layout.getLayoutParams();
		layoutParams.width = screenWidth / 2 - (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, dm);
		layoutParams.height = (int)(screenWidth * 1.5 / 2);
		layout.setLayoutParams(layoutParams);

		NetworkImageView iv = (NetworkImageView) holder
				.getView(R.id.item_home_iv);
		NetworkManager.getInstance().setImageUrl(iv, data.getImgUrl());
		//Glide.with(act).load(data.getImgUrl()).into(iv);
		holder.setTextViewText(R.id.item_home_tv_info, data.getInfo());
		holder.setTextViewText(R.id.item_home_tv_price, "￥" + data.getPrice());
	}

}
