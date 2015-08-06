package com.duguang.baseanimation.ui;

import android.view.Window;

import com.duguang.baseanimation.R;
import com.duguang.baseanimation.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {

	@Override
	public void setView() {
		getWindow().requestFeature(Window.FEATURE_PROGRESS); //去标题栏 
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}

	
	
}
