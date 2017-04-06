package com.biyesheji.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.biyesheji.android.fragment.HomeFragment;
import com.biyesheji.android.fragment.MessageFragment;
import com.biyesheji.android.fragment.MineFragment;
import com.biyesheji.android.fragment.ShopcartFragment;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private Fragment[] mFragments;
	private int mIndex;
	
    FrameLayout mContent;
    RadioButton mRbHome;
    RadioButton mRbShop;
    RadioButton mRbMessage;
    RadioButton mRbMine;
    RadioGroup  mRgTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContent=(FrameLayout)findViewById(R.id.content);
        mRbHome=(RadioButton)findViewById(R.id.rbHome);
        mRbShop=(RadioButton)findViewById(R.id.rbShop);
        mRbMessage=(RadioButton)findViewById(R.id.rbMessage);
        mRbMine=(RadioButton)findViewById(R.id.rbMine);
        mRgTools=(RadioGroup)findViewById(R.id.rgTools);
        mRbHome.setOnClickListener(this);
        mRbShop.setOnClickListener(this);
        mRbMessage.setOnClickListener(this);
        mRbMine.setOnClickListener(this);
        initFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void initFragment() {
        //首页
        HomeFragment homeFragment =new HomeFragment();
        //购物车
        ShopcartFragment shopcartFragment =new ShopcartFragment();
        //消息
        MessageFragment messageFragment =new MessageFragment();
        //个人中心
        MineFragment mineFragment =new MineFragment();
        //添加到数组
        mFragments = new Fragment[]{homeFragment,shopcartFragment,messageFragment,mineFragment};
        //开启事务
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.content,homeFragment).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }



    private void setIndexSelected(int index) {
        if(mIndex==index){
            return;
        }
        FragmentManager    fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft              = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if(!mFragments[index].isAdded()){
            ft.add(R.id.content,mFragments[index]).show(mFragments[index]);
        }else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex=index;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                setIndexSelected(0);
                break;
            case R.id.rbShop:
                setIndexSelected(1);
                break;
            case R.id.rbMessage:
                setIndexSelected(2);
                break;
            case R.id.rbMine:
                setIndexSelected(3);
                break;
        }

    }
}
