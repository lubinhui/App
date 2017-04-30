package com.biyesheji.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.biyesheji.android.R;
import com.biyesheji.android.network.NetworkManager;

public class HomeDetailActivity extends Activity implements OnClickListener{

	private PagerAdapter pagerAdapter;
	private ViewPager viewPager;
	private ArrayList<View> viewContainer;
	private ArrayList<String> urls;
	private ArrayList<String> alists;
	private TextView name;
	private TextView price;
	private String info;
	private float priceNum;
	private TextView addCart;
	private TextView buy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_detail);
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
		alists = bundle.getStringArrayList("key");
		info = bundle.getString("info");
		priceNum = bundle.getFloat("price");
		}
		initView();
	}
	
	private void initView(){
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		name = (TextView) findViewById(R.id.name);
		price = (TextView) findViewById(R.id.price);
		addCart = (TextView) findViewById(R.id.addCart);
		buy = (TextView) findViewById(R.id.buybuybuy);
		getViewImage();
		pagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((ViewPager) container).removeView(viewContainer
						.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				((ViewPager) container).addView(viewContainer
						.get(position));
				return viewContainer.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return viewContainer.size();
			}
		};
		addCart.setOnClickListener(this);
		buy.setOnClickListener(this);
		viewPager.setAdapter(pagerAdapter);
		name.setText(info);
		price.setText("￥"+String.valueOf(priceNum));
	}
	private void getViewImage() {
		if (viewContainer == null) {
			viewContainer = new ArrayList<View>();
		}
		if (urls == null) {
			urls = new ArrayList<String>();
		}
		urls = alists;
		viewContainer.clear();
		for (String url : urls) {
			NetworkImageView iv = new NetworkImageView(this);
			iv.setScaleType(ScaleType.FIT_XY);
			NetworkManager.getInstance().setImageUrl(iv, url);
			viewContainer.add(iv);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addCart:
			
			break;
			
		case R.id.buybuybuy:
			
			break;

		default:
			break;
		}
	}
}
