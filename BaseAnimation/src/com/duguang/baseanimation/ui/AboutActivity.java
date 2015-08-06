package com.duguang.baseanimation.ui;

import com.duguang.baseanimation.R;
import com.duguang.baseanimation.R.layout;
import com.duguang.baseanimation.ui.base.BaseActivity;
import com.duguang.baseanimation.utils.ApkInfoTool;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 关于页面
 * @author duguang
 * 博客地址:http://blog.csdn.net/duguang77
 */
public class AboutActivity extends BaseActivity {
	
	private TextView textView_VersionName;
	@Override
	public void setView() {
		setContentView(R.layout.activity_about);
		
	}

	@Override
	public void initView() {
		textView_VersionName = (TextView) findViewById(R.id.textView_VersionName);
		textView_VersionName.setText("版本号:"+ApkInfoTool.getVersionName(this));
	
	}

	@Override
	public void setListener() {
		
	}
}
