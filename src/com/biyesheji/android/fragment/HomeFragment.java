package com.biyesheji.android.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.android.volley.toolbox.NetworkImageView;
import com.biyesheji.android.R;
import com.biyesheji.android.adapter.HomeListAdapter;
import com.biyesheji.android.model.HomeFloorData;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.network.NetworkManager;
import com.viewpagerindicator.CirclePageIndicator;
import com.wiipu.mall.noscrollview.NoScrollListView;

/**
 * 首页Fragment
 */
public class HomeFragment extends Fragment {

	/**
	 * 广告自动循环切换的时间间隔
	 */
	private static final int AUTO_SCROLL_INTERVAL = 3000;
	private AutoScrollViewPager viewPager;
	private CirclePageIndicator indicator;
	private PagerAdapter pagerAdapter;
	private ArrayList<View> viewContainer;
	/**
	 * 广告图片的url数组
	 */
	private ArrayList<String> urls;
	/**
	 * 商品列表部分
	 */
	private NoScrollListView listView;
	private HomeListAdapter listAdapter;
	private ArrayList<HomeFloorData> floorDatas;
	/**
	 * 顶部搜索
	 */
	private EditText etSearch;
	private ImageButton ibSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		initView(view);
		return view;
	}

	/**
	 * 初始化视图
	 */
	private void initView(View view) {
		// 广告位部分
		viewPager = (AutoScrollViewPager) view
				.findViewById(R.id.home_viewPager);
		getViewImage();
		pagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((AutoScrollViewPager) container).removeView(viewContainer
						.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				((AutoScrollViewPager) container).addView(viewContainer
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
		viewPager.setAdapter(pagerAdapter);
		indicator = (CirclePageIndicator) view
				.findViewById(R.id.home_indicator);
		indicator.setViewPager(viewPager);
		viewPager.setInterval(AUTO_SCROLL_INTERVAL);
		viewPager.startAutoScroll();
		// 商品列表部分
		listView = (NoScrollListView) view.findViewById(R.id.home_lv);
		floorDatas = getListData();
		listAdapter = new HomeListAdapter(getActivity(), floorDatas,
				R.layout.item_home_lv);
		listView.setAdapter(listAdapter);
		listView.setDivider(null);
		// 手动给ListView内容设置了高度，导致页面进入不在顶端，通过给顶端控件设置焦点的方法使view显示在顶端
		viewPager.setFocusable(true);
		viewPager.setFocusableInTouchMode(true);
		viewPager.requestFocus();
		// 顶部搜索部分
		etSearch = (EditText) view.findViewById(R.id.home_top_et_search);
		ibSearch = (ImageButton) view.findViewById(R.id.home_top_ib_search);
		ibSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ////////////////////////////////////
				// /////////向服务器发送搜索请求///////////
				etSearch.getText().toString().trim();
				// ////////////////////////////////////
			}
		});
	}

	/**
	 * 获取广告图片列表
	 */
	private void getViewImage() {
		if (viewContainer == null) {
			viewContainer = new ArrayList<View>();
		}
		if (urls == null) {
			urls = new ArrayList<String>();
		}
		urls = getImageUrls();
		viewContainer.clear();
		for (String url : urls) {
			NetworkImageView iv = new NetworkImageView(getActivity());
			iv.setScaleType(ScaleType.FIT_XY);
			NetworkManager.getInstance().setImageUrl(iv, url);
			viewContainer.add(iv);
		}
	}

	/**
	 * 获取广告位的图片资源url数组
	 * 每次从服务器获得url数组先做永久性存储，获取时先从本地显示之前的缓存，等获取成功之后再调用notifyDataSetChanged()
	 * 
	 * @return url数组
	 */
	private ArrayList<String> getImageUrls() {
		ArrayList<String> list = new ArrayList<String>();
		// //////////////////////////////////////
		// ////////////////假数据/////////////////
		list.add("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
		list.add("http://c.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b503564d2638635e5dde7116e63.jpg");
		list.add("http://g.hiphotos.baidu.com/image/pic/item/cf1b9d16fdfaaf517578b38e8f5494eef01f7a63.jpg");
		list.add("http://f.hiphotos.baidu.com/image/pic/item/77094b36acaf2eddce917bd88e1001e93901939a.jpg");
		list.add("http://g.hiphotos.baidu.com/image/pic/item/f703738da97739124dd7b750fb198618367ae20a.jpg");
		// //////////////////////////////////////
		return list;
	}

	/**
	 * 获取楼层列表的数据
	 */
	private ArrayList<HomeFloorData> getListData() {
		ArrayList<HomeFloorData> list = new ArrayList<HomeFloorData>();
		// ///////////////////////////////////////
		// /////////////////假数据/////////////////
		String[] imgurls = new String[]{
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493571566748&di=e36077652517108964d00b84b3b3a06d&imgtype=0&src=http%3A%2F%2Fs1.sinaimg.cn%2Fmw690%2F0020IYLBzy6HDRANpRea0%26690"
				,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493571667578&di=6a23059b988bab80c86bad7952e60e76&imgtype=0&src=http%3A%2F%2Fpic45.nipic.com%2F20140810%2F9777035_165424741000_2.jpg"
				,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493571864707&di=3e38ebae5992180e4bbfbb682e68a705&imgtype=0&src=http%3A%2F%2Fcdn.tbstbs.cn%2F20160324%2F56f39029ef7b8.jpeg"
				,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493571910668&di=5afd4ac6c14837c77e80bfd58528f617&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140315%2F0036036751094744_b.jpg"
				,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493571945856&di=aaa8657f15fedb423ba4d6116c842af0&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2FwDBB2liRHJ5RroZIN0WImw%3D%3D%2F1411034058351340268.jpg"
				,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493571986636&di=777827c195a60223efbbde0cd2fb4448&imgtype=0&src=http%3A%2F%2Fwww.people.com.cn%2Fmediafile%2Fpic%2F20130804%2F66%2F12600987985333813594.jpg"
				,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493572018770&di=82691bb9a0c18add08d2ca0148d090b6&imgtype=0&src=http%3A%2F%2Fpic38.nipic.com%2F20140214%2F2531170_142600171000_2.jpg"
				
		};
		int[] ids = new int[]{0,1,2,3,4,5,6};
		String[] infos= new String[]{"靓丽女装","旅行箱包","ysl口红","经典男装","nike球鞋","内衣","手机数码"};
		float[] prices = new float[]{239,129,320,399,400,128,4999};
		String imgUrl = "http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg";
		ArrayList<ProductData> products;
		HomeFloorData floor;
		ProductData product;
		for (int j = 0; j < 2; j++) {
			products = new ArrayList<ProductData>();
			for (int i = 0; i < infos.length; i++) {
				product = new ProductData();
				product.setId(ids[i]);
				product.setImgUrl(imgurls[i]);
				product.setInfo(infos[i]);
				product.setPrice(prices[i]);
				products.add(product);
			}
			floor = new HomeFloorData();
			floor.setFloor(j + "F");
			floor.setProducts(products);
			list.add(floor);
		}
		// ///////////////////////////////////////
		return list;
	}

	private void initData(){
		
	}
	/**
	 * 通知ViewPager广告位数据源改变9
	 */
	public void notifyDataSetChanged() {
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
		if (indicator != null)
			indicator.notifyDataSetChanged();
	}

}
