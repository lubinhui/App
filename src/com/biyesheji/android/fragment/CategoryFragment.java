package com.biyesheji.android.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.biyesheji.android.R;
import com.biyesheji.android.adapter.CategoryLeftListAdapter;
import com.biyesheji.android.adapter.CategoryRightGridAdapter;
import com.biyesheji.android.model.CategoryData;
import com.biyesheji.android.model.SecondCategoryData;

/**
 * 商品分类Fragment
 */
public class CategoryFragment extends Fragment {

	/**
	 * 顶部搜索相关
	 */
	private EditText etSearch;
	private ImageButton ibSearch;
	/**
	 * 左边列表相关
	 */
	private ListView listView;
	private ArrayList<CategoryData> listCategorys;
	private CategoryLeftListAdapter listAdapter;
	private int selectedPosition;
	private String[] names=new String[]{"女装","箱包","美妆","男装","鞋靴","内衣配饰","手机数码","家电","食品"};
	/**
	 * 右边网格有关
	 */
	private GridView gridView;
	private ArrayList<SecondCategoryData> gridCategorys;
	private CategoryRightGridAdapter gridAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_category, container,
				false);
		initView(view);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		// 解决列表选中状态的item在按home键之后，重新进入时为非选中状态的问题
		View v = listView.getChildAt(selectedPosition
				- listView.getFirstVisiblePosition());
		if (v != null) {
			v.setSelected(true);
		}
	}

	/**
	 * 初始化视图
	 * 
	 * @param view
	 *            父视图
	 */
	private void initView(View view) {
		// 搜索相关
		etSearch = (EditText) view.findViewById(R.id.top_et_search);
		ibSearch = (ImageButton) view.findViewById(R.id.top_ib_search);
		ibSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ////////////////////////////////////
				// /////////向服务器发送搜索请求///////////
				etSearch.getText().toString().trim();
				// ////////////////////////////////////
			}
		});
		// 列表相关
		listView = (ListView) view.findViewById(R.id.category_listView);
		listCategorys = new ArrayList<CategoryData>();
		listAdapter = new CategoryLeftListAdapter(getActivity(), listCategorys,
				R.layout.item_category_left_lv);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				view.setSelected(true);
				if (selectedPosition != position) {
					selectedPosition = position;
					refreshSecondCategorys(((CategoryData) parent
							.getItemAtPosition(position)).getId());
				}
			}
		});
		// 解决列表选中状态的item出屏后重新出现时为非选中状态的问题
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (selectedPosition >= view.getFirstVisiblePosition()
						|| selectedPosition <= view.getLastVisiblePosition()) {
					View v = view.getChildAt(selectedPosition
							- view.getFirstVisiblePosition());
					if (v != null) {
						v.setSelected(true);
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});
		// 获取一级分类数据
		getCategoryData();
		// 网格相关
		gridView = (GridView) view.findViewById(R.id.category_gridView);
		gridCategorys = new ArrayList<SecondCategoryData>();
		gridAdapter = new CategoryRightGridAdapter(getActivity(),
				gridCategorys, R.layout.item_category_right_grid);
		gridView.setAdapter(gridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*startActivity(new Intent(getActivity(),
						ProductDetailsSecondLayerActivity.class));*/
			}
		});
	}

	/**
	 * 从服务器获取一级分类数据
	 */
	private void getCategoryData() {
		ArrayList<CategoryData> list = new ArrayList<CategoryData>();
		// //////////////////////////////////////
		// //////////////假数据///////////////////
		for (int i = 0; i < names.length; i++) {
			CategoryData data = new CategoryData();
			data.setId(i);
			data.setName(names[i]);
			list.add(data);
		}
		// //////////////////////////////////////
		listCategorys.clear();
		listCategorys.addAll(list);
		listAdapter.notifyDataSetChanged();
		// 默认二级分类的一级分类id为一级分类列表的第一项
		listView.post(new Runnable() {

			@Override
			public void run() {
				if (listView.getChildAt(0) != null) {
					selectedPosition = 0;
					listView.getChildAt(0).setSelected(true);
					refreshSecondCategorys(listCategorys.get(0).getId());
				}
			}
		});
	}

	/**
	 * 从服务器获取二级分类列表数据
	 * 
	 * @param categoryId
	 *            上级分类列表的id
	 */
	public void refreshSecondCategorys(int categoryId) {
		//SecondCategoryData data = new SecondCategoryData();
		ArrayList<SecondCategoryData> list = new ArrayList<SecondCategoryData>();
		Map<Integer,ArrayList<SecondCategoryData>> map = new HashMap<Integer, ArrayList<SecondCategoryData>>();
		initData(map);
		if(categoryId<2){
			 list = map.get(categoryId);
		}else{
			list = map.get(0);
		}
		// ////////////////////////////////////
		// //////////////假数据/////////////////
		/*for (int i = 0; i < 10; i++) {
			SecondCategoryData data = new SecondCategoryData();
			data.setId(i);
			data.setName("分类" + i);
			data.setImgUrl("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
			list.add(data);
			Log.e("lbh", "++++++"+categoryId);
		}*/
		// ////////////////////////////////////
		gridCategorys.clear();
		gridCategorys.addAll(list);
		gridAdapter.notifyDataSetChanged();
	}
	
	public void initData(Map<Integer,ArrayList<SecondCategoryData>> map){
		//Map<Integer,ArrayList<SecondCategoryData>> map = new HashMap<Integer, ArrayList<SecondCategoryData>>();
		SecondCategoryData data = new SecondCategoryData();
		SecondCategoryData data2 = new SecondCategoryData();
		SecondCategoryData data3 = new SecondCategoryData();
		SecondCategoryData data4 = new SecondCategoryData();
		SecondCategoryData data5 = new SecondCategoryData();
		ArrayList<SecondCategoryData> list = new ArrayList<SecondCategoryData>();
		data.setId(1);
		data.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494267920253&di=164cc0fed0b7ca328e8afc80d43d73b2&imgtype=0&src=http%3A%2F%2Fimg.11665.com%2Fimg01_p%2Fi1%2F19045039283764381%2FT12_5wFcBbXXXXXXXX_%2521%25210-item_pic.jpg");
		data.setName("长袖");
		
		data2.setId(2);
		data2.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494267920252&di=ed83a427f65863479255cd7b3049d404&imgtype=0&src=http%3A%2F%2Fimg10.360buyimg.com%2Fimgzone%2Fjfs%2Ft4%2F291%2F4996989716%2F496110%2F863aaf03%2F537e12a6N60f6cad9.jpg");
		data2.setName("短袖");
		data3.setId(3);
		data3.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494268116093&di=fc4d4144d3e5e33ea2eea77f3428cd88&imgtype=0&src=http%3A%2F%2Fs1.sinaimg.cn%2Fmw690%2F004lgKSyty6XxuxJuVO20%26690");
		data3.setName("大衣");
		data4.setId(4);
		data4.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494268195492&di=b29443957d610ad10febbd598ad168f6&imgtype=0&src=http%3A%2F%2Fa.vpimg4.com%2Fupload%2Fmerchandise%2F62705%2Fjascci-J40513BA-4.jpg");
		data4.setName("裤子");
		data5.setId(5);
		data5.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494268252337&di=ae0366b9687f5c0c8f488197667f495f&imgtype=0&src=http%3A%2F%2Fwww.2298.com%2FUploadStorage%2Fgallery%2Fimg1%2F53573%2F201307%2F5f92400a2e874a049d2815e2c40da6d3.jpg");
		data5.setName("帽子");
		list.add(data);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		list.add(data5);
		
		SecondCategoryData data6 = new SecondCategoryData();
		SecondCategoryData data7 = new SecondCategoryData();
		SecondCategoryData data8 = new SecondCategoryData();
		SecondCategoryData data9 = new SecondCategoryData();
		SecondCategoryData data10 = new SecondCategoryData();
		ArrayList<SecondCategoryData> list2 = new ArrayList<SecondCategoryData>();
		data6.setId(1);
		data6.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494269783396&di=a7be971d7d8ded0942ce6b650b11b1c2&imgtype=0&src=http%3A%2F%2Fimg1.tbcdn.cn%2Ftfscom%2Fi4%2FTB1bPRjNXXXXXcJaXXXXXXXXXXX_%2521%25210-item_pic.jpg");
		data6.setName("男装长袖");
		
		data7.setId(2);
		data7.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494269830292&di=c13a7bb7afeb5b29fd5271b61211243f&imgtype=0&src=http%3A%2F%2Fa.vpimg3.com%2Fupload%2Fmerchandise%2F385786%2FZDN-0101401613-1.jpg");
		data7.setName("男装短袖");
		data8.setId(3);
		data8.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494864587&di=19eef504ccaa18427e8f57662b2be183&imgtype=jpg&er=1&src=http%3A%2F%2Fimg007.hc360.cn%2Fy1%2FM00%2FC6%2FC3%2FwKhQc1R-ItiEdBfzAAAAAAnFFx4513.jpg");
		data8.setName("男装大衣");
		data9.setId(4);
		data9.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494269912570&di=e9b56573c98991c865d6a9be449ac581&imgtype=0&src=http%3A%2F%2Fa.vpimg4.com%2Fupload%2Fmerchandise%2F121814%2FIT-B1XPTWC6275XXBKX-1.jpg");
		data9.setName("男装裤子");
		data10.setId(5);
		data10.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494269941657&di=be91e35b5334b998fc860edbb15d24a3&imgtype=0&src=http%3A%2F%2Fd6.yihaodianimg.com%2FN04%2FM02%2F63%2F37%2FCgQDrVM9KkiAGM-mAAOdq4-uh6E26600.jpg");
		data10.setName("男装帽子");
		list2.add(data6);
		list2.add(data7);
		list2.add(data8);
		list2.add(data9);
		list2.add(data10);
		map.put(0, list);
		map.put(1, list2);
	}

}
