package com.biyesheji.android.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biyesheji.android.R;
import com.biyesheji.android.activity.DingdanDetailActivity;
import com.biyesheji.android.activity.SettingActivity;
import com.biyesheji.android.activity.UserInfoActivity;
import com.biyesheji.android.utils.MyUtils;
import com.biyesheji.android.widght.CircleImageView;

public class MineFragment extends Fragment implements OnClickListener {

	private TextView setting;
	private RelativeLayout dingdan;
	private CircleImageView icon;
    public MineFragment() {
    	
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        setting=(TextView) view.findViewById(R.id.iv_setting);
        dingdan=(RelativeLayout) view.findViewById(R.id.rl_dingdan);
        icon=(CircleImageView) view.findViewById(R.id.icon);
        setting.setOnClickListener(this);
        dingdan.setOnClickListener(this);
        icon.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_setting:
			MyUtils.jumpActivity(getActivity(), SettingActivity.class);
			break;
		case R.id.rl_dingdan:
			MyUtils.jumpActivity(getActivity(), DingdanDetailActivity.class);
			break;
		case R.id.icon:
			MyUtils.jumpActivity(getActivity(), UserInfoActivity.class);
			break;
		default:
			break;
		}
	}
}
