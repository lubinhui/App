package com.biyesheji.android.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.biyesheji.android.R;
import com.biyesheji.android.activity.HomeDetailActivity;
import com.biyesheji.android.model.HomeFloorData;
import com.biyesheji.android.model.ProductData;
import com.biyesheji.android.utils.MyUtils;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.wiipu.mall.noscrollview.NoScrollGridView;

/**
 * 首页ListView的适配器
 */
public class HomeListAdapter extends UniversalAdapter<HomeFloorData> {

	Map<Integer,ArrayList<String>> map = new HashMap<Integer,ArrayList<String>>();
	ArrayList<String> slist1 = new ArrayList<String>();
	ArrayList<String> slist2 = new ArrayList<String>();
	ArrayList<String> slist3 = new ArrayList<String>();
	ArrayList<String> slist4 = new ArrayList<String>();
	ArrayList<String> slist5 = new ArrayList<String>();
	ArrayList<String> slist6 = new ArrayList<String>();
	ArrayList<String> slist7 = new ArrayList<String>();
	Bundle bundle = new Bundle();
	public HomeListAdapter(Context context, List<HomeFloorData> datas,
			int itemLayoutId) {
		super(context, datas, itemLayoutId);
		slist1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574066440&di=85ac8734282cd21c68ab8fcd05c0c198&imgtype=0&src=http%3A%2F%2Fwww.2298.com%2FUploadStorage%2F108615%2Ff3e8a43342b577c7f4291b6ceb058910.jpg");
		slist1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574066440&di=c42da356e4fc46e083923c975fb05468&imgtype=0&src=http%3A%2F%2Fpic33.nipic.com%2F20130912%2F5066297_112721039000_2.jpg");
		slist2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494168942&di=923e20b27d8b4769011b98e9accf8b70&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e97757a4553c0000012e7e88b9e8.jpg");
		slist2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574224151&di=52fbd812dbbbacbb002cc3f4b7f0a3a6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013a2c57a4554a0000018c1bf33dc2.jpg");
		slist3.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574324297&di=ab79007b5a7f9823756b31f71363652c&imgtype=0&src=http%3A%2F%2Fn1image.hjfile.cn%2Fmh%2F2016%2F12%2F21%2F601a42742c550370221344db9e796a7b.jpg");
		slist3.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574396618&di=791ee5ac6860c3cee82dc2abe809c74f&imgtype=0&src=http%3A%2F%2Fimg.alicdn.com%2Ftfscom%2FTB2XJt2lFXXXXbPXpXXXXXXXXXX_%2521%25210-dgshop.jpg_320x320xz.jpg");
		slist4.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574448688&di=f7771e55c3721f0c1295d14fd1f9ec5f&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fladyproduct%2F1501%2F23%2Fc6%2F2262114_1422024376823.jpg");
		slist4.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574448687&di=a6bebe864d797af5f83e21ca8e3cc4fb&imgtype=0&src=http%3A%2F%2Fart.cfw.cn%2Fupload%2Fart_pic%2F2014%2F09%2F13%2F906d521f-3e55-4ff8-b434-00776461217c.jpg");
		slist4.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574448687&di=c2bb524058b60751e7fa3ba2a15d522d&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fladyproduct%2F1501%2F19%2Fc4%2F2041889_1421639512339.jpg");
		slist5.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574588737&di=4a9028d1f70b31c1a674a5340a46253a&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F8326cffc1e178a82d8d4eba7f603738da877e8c9.jpg");
		slist5.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574588737&di=32ddaea84357482384f9301ca8b272c4&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01795e5710a20132f8758c9bba60be.jpg%40900w_1l_2o_100sh.jpg");
		slist6.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574671799&di=7275a69a93922ddb55b5339894be8eb4&imgtype=0&src=http%3A%2F%2Fupload.0745news.cn%2F2017%2F0406%2F1491442909757.png");
		slist6.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574671797&di=17c6e84294e1a10d60b44f2b6536be8f&imgtype=0&src=http%3A%2F%2Fpic.jschina.com.cn%2F0%2F13%2F31%2F01%2F13310189_697433.jpg");
		slist7.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574790405&di=8f978ae3d252ead5583aad2b0aaa921e&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F16%2F65%2F33%2F95F58PICwum_1024.jpg");
		slist7.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493574790405&di=ae860dd7e9bc940ea7b221da8135a0ee&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F16%2F66%2F67%2F12958PICIWa_1024.jpg");
		map.put(0, slist1);
		map.put(1, slist2);
		map.put(2, slist3);
		map.put(3, slist4);
		map.put(4, slist5);
		map.put(5, slist6);
		map.put(6, slist7);
	}
	@Override
	public void updateItem(ViewHolder holder, HomeFloorData data) {
		((TextView) holder.getView(R.id.item_home_tv_floor)).setText(data.getFloor());
		if (data.getProducts() != null) {
			NoScrollGridView gridView = (NoScrollGridView) holder
					.getView(R.id.item_home_grid);
			HomeGridAdapter adapter = new HomeGridAdapter(context,
					data.getProducts(), R.layout.item_home_grid);
			gridView.setAdapter(adapter);
			// 设置商品的点击事件
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					ProductData product = (ProductData) parent.getItemAtPosition(position);
					String info=product.getInfo();
					float price = product.getPrice();
					ArrayList<String> lists = map.get(position);
					bundle.putStringArrayList("key", lists);
					bundle.putString("info", info);
					bundle.putFloat("price", price);
					MyUtils.jumpActivity(context, HomeDetailActivity.class,bundle,false);
				}
			});
		}
	}

}