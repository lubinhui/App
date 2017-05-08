package com.biyesheji.android.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.biyesheji.android.R;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.network.NetworkManager;
import com.biyesheji.android.utils.InputUtil;
import com.biyesheji.android.utils.MyUtils;
import com.biyesheji.android.utils.OutputUtil;

public class DingdanActivity extends Activity {
	private NetworkImageView  imageView;
	private TextView cartName;
	private TextView price;
	private String imgUrl;
	private String priceNum;
	private String info;
	private Button submit;
	
	private double wuyou = 0;
	private double yunfei = 0;
	private TextView productPrc;
	private TextView yunfei2;
	private TextView wuyou2;
	private List<ProductData> dingdanList;
	private TextView address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ding_dan);
		initView();
		initData();
	}

	private void initData() {
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			imgUrl = bundle.getString("imgurl");
			priceNum = bundle.getString("price");
			info = bundle.getString("info");
		}
		NetworkManager.getInstance().setImageUrl(imageView, imgUrl);
		cartName.setText(info);
		price.setText("￥"+priceNum);
		yunfei2.setText(yunfei+"");
		wuyou2.setText(wuyou+"");
		productPrc.setText(yunfei+wuyou+Double.parseDouble(priceNum)+"");
		InputUtil<ProductData> inputUtil = new InputUtil<ProductData>();
		dingdanList = inputUtil.readListFromSdCard("dingdan");
	}

	private void initView() {
		imageView = (NetworkImageView) findViewById(R.id.imageView);
		cartName = (TextView) findViewById(R.id.cartName);
		price = (TextView) findViewById(R.id.price);
		submit = (Button) findViewById(R.id.submit);
		productPrc = (TextView) findViewById(R.id.productPrc);
		yunfei2 = (TextView) findViewById(R.id.yunfei);
		wuyou2 = (TextView) findViewById(R.id.wuyou);
		address = (TextView) findViewById(R.id.address);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (dingdanList==null){
					dingdanList = new ArrayList<ProductData>();
				}
				ProductData data = new ProductData();
				data.setId(0);
				data.setImgUrl(imgUrl);
				data.setInfo(info);
				data.setPrice(Float.parseFloat(priceNum));
				dingdanList.add(data);
				OutputUtil<ProductData> outputUtil = new OutputUtil<ProductData>();
				boolean isSave = outputUtil.writeListIntoSDcard("dingdan", dingdanList);
				if(isSave)
				MyUtils.jumpActivity(DingdanActivity.this, DingDanSuccessActivity.class);
			}
		});
		address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				//MyUtils.jumpActivity(DingdanActivity.this, AddressActivity.class);
				Intent intent = new Intent();
				

				intent.setClass(DingdanActivity.this, AddressActivity.class);
				//if (bTop) {
					intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//}

				DingdanActivity.this.startActivityForResult(intent, 1);;
				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1&&resultCode==1){
			String string = data.getExtras().getString("address");
			address.setText(string);
		}
	}
}
