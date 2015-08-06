package com.duguang.baseanimation.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.duguang.baseanimation.R;
import com.duguang.baseanimation.ui.base.BaseActivity;
import com.duguang.baseanimation.ui.splash.SplashDemoActivity;
import com.umeng.fb.FeedbackAgent;
import com.umeng.socialize.bean.LIKESTATUS;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.view.ActionBarView;
import com.umeng.update.UmengUpdateAgent;

/**
 * 主页面
 * 
 * @author duguang 博客地址:http://blog.csdn.net/duguang77
 */
public class MainActivity extends BaseActivity implements OnItemClickListener {

	private static final String tag = "MainActivity";
	private ListView listView_animation;
	private ArrayAdapter<String> itemAdapter;
	private TextView tvHint;
	
	private String[] item = { "简单动画", "复杂动画","Splash引导动画" };

	@Override
	public void setView() {
		 FeedbackAgent agent = new FeedbackAgent(this);
		 agent.sync();
		UmengUpdateAgent.update(this);// 友盟更新
		UmengUpdateAgent.setUpdateOnlyWifi(false);// 在非Wifi情况下也检测版本更新
		getWindow().requestFeature(Window.FEATURE_PROGRESS); //去标题栏 
		setContentView(R.layout.activity_splash);
		init();
	}

	private void init() {
		
		UMSocialService controller = UMServiceFactory.getUMSocialService("com.umeng.like", RequestType.SOCIAL);
		controller.getEntity().getLikeStatus();
		controller.likeChange(this, new SocializeClientListener() {
		    @Override
		    public void onStart() {}

		    @Override
		    public void onComplete(int status, SocializeEntity entity) {
		        if (entity != null){
		            //获取当前的“喜欢”状态
		            LIKESTATUS likeStatus = entity.getLikeStatus();
		        }
		    }

		});
		
		//用于集成ActionBar 的ViewGroup ActionBar 将填充提供的ViewGroup(需要开发者自己在布局文件或代码中创建
		//，建议使用RelativeLayout)
		RelativeLayout parent = (RelativeLayout)findViewById(R.id.RelativeLayout);
		//创建ActionBar des参数是ActionBar的唯一标识，请确保不为空
		ActionBarView socializeActionBar = new ActionBarView(this,"111");

		LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		socializeActionBar.setLayoutParams(layoutParams);
		//添加ActionBar
		parent.addView(socializeActionBar);
	}

	@Override
	public void initView() {
		listView_animation = (ListView) findViewById(R.id.listView_animation);
		tvHint = (TextView) findViewById(R.id.tv_hint);
		itemAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, item);
		
		Animation ani = new AlphaAnimation(0f,1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE);
		ani.setRepeatCount(Animation.INFINITE);
		tvHint.startAnimation(ani);
	}

	@Override
	public void setListener() {
		listView_animation.setAdapter(itemAdapter);
		listView_animation.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i(tag, String.valueOf(position));
//		Animation anim = AnimationUtils.loadAnimation(MainActivity.this,
//				R.anim.out_translate_top);
//		listView_animation.startAnimation(anim);
		
		Intent intent = null;
		if (position == 0) {
			intent = new Intent(MainActivity.this, SimpleActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.in_translate_top, R.anim.out_translate_top);
		} else if (position == 1) {
			intent = new Intent(MainActivity.this, ComplexActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.block_move_right, R.anim.small_2_big);
		} else if (position == 2) {
			intent = new Intent(MainActivity.this, SplashDemoActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.small_2_big, R.anim.block_move_right);
		}
	}


}
