package com.biyesheji.android.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.biyesheji.android.R;
import com.biyesheji.android.activity.DingdanDetailActivity;
import com.biyesheji.android.activity.SettingActivity;
import com.biyesheji.android.activity.UserInfoActivity;
import com.biyesheji.android.utils.MyUtils;
import com.biyesheji.android.utils.PreferenceHelper;
import com.biyesheji.android.widght.CircleImageView;

public class MineFragment extends Fragment implements OnClickListener {

	private TextView setting;
	private RelativeLayout dingdan;
	private CircleImageView icon;
	private TextView username;
	private String userName;
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
        username=(TextView) view.findViewById(R.id.username);
        setting.setOnClickListener(this);
        dingdan.setOnClickListener(this);
        icon.setOnClickListener(this);
        userName = PreferenceHelper.readString(getActivity(), "userinfo", "username");
        if(!TextUtils.isEmpty(userName)){
        	username.setText(userName);
        }else{
        	username.setText("请登录");
        }
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
			if(!isLogin()){
				Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
				return;
			}
			MyUtils.jumpActivity(getActivity(), SettingActivity.class);
			break;
		case R.id.rl_dingdan:
			if(!isLogin()){
				Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
				return;
			}
			MyUtils.jumpActivity(getActivity(), DingdanDetailActivity.class);
			break;
		case R.id.icon:
			if(!isLogin()){
				Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
				return;
			}
			MyUtils.jumpActivity(getActivity(), UserInfoActivity.class);
			break;
		default:
			break;
		}
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		userName = PreferenceHelper.readString(getActivity(), "userinfo", "username");
		if(!TextUtils.isEmpty(userName)){
			username.setText(userName);
		}else{
			username.setText("请登录");
		}
	}
	private boolean isLogin(){
		userName = PreferenceHelper.readString(getActivity(), "userinfo", "username");
		if(TextUtils.isEmpty(userName)){
			Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
			return false;
		}else{
			return true;
		}
	}

}
