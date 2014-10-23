package com.baby.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baby.R;
import com.baby.adapter.TimeLineAdapter;
import com.baby.model.TimeLinePhoto;
import com.baby.widget.TimeLineListView;
import com.baby.widget.TimeLineListView.OnPositionChangedListener;

public class MainFragment1 extends Fragment implements OnTouchListener,
		OnPositionChangedListener {

	private Context mContext;

	private TimeLineListView timelineListView;
	private TimeLineAdapter timelineAdapter;

	private List<TimeLinePhoto> photos = new ArrayList<TimeLinePhoto>();

	private FrameLayout timebarLayout;

	private TextView timeshow;

	private View headView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.main1_fragment, null, false);
		timelineListView = (TimeLineListView) rootView
				.findViewById(R.id.timeline_listview);
		timebarLayout = (FrameLayout) rootView
				.findViewById(R.id.time_scrollbar);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mContext = this.getActivity();


	}

	private void initView() {
		headView = LayoutInflater.from(mContext).inflate(
				R.layout.main1_user_home, null);
		timelineListView.addHeaderView(headView);

		initHeadView();

		initAdapter();
		timelineListView.setOnPositionChangedListener(this);

	}
	
	@Override
	public void onResume() {
		
		initView();
		
		super.onResume();
		
		
	}

	private void initHeadView() {
		RelativeLayout profileLayout = (RelativeLayout) headView
				.findViewById(R.id.main1_user_home_profilelayout);
		LinearLayout settingsLayout = (LinearLayout) headView
				.findViewById(R.id.main1_user_home_settings_layout);
		ImageView profileImage = (ImageView) headView
				.findViewById(R.id.main1_user_home_profile_image);
		TextView profileName = (TextView) headView
				.findViewById(R.id.main1_user_home_profile_name);
		TextView profileStats = (TextView) headView
				.findViewById(R.id.main1_user_home_profile_stats);
		RelativeLayout followingLayout = (RelativeLayout) headView
				.findViewById(R.id.main1_user_home_following);
		RelativeLayout followerLayout = (RelativeLayout) headView
				.findViewById(R.id.main1_user_home_follower);
		RelativeLayout messageLayout = (RelativeLayout) headView
				.findViewById(R.id.main1_user_home_message);
		TextView following = (TextView) headView.findViewById(R.id.following);
		TextView follower = (TextView) headView.findViewById(R.id.follower);
		TextView message = (TextView) headView.findViewById(R.id.message);

		MyOnClickListener myListener = new MyOnClickListener();
		profileLayout.setOnClickListener(myListener);
		settingsLayout.setOnClickListener(myListener);
		profileImage.setOnClickListener(myListener);
		followingLayout.setOnClickListener(myListener);
		followerLayout.setOnClickListener(myListener);
		messageLayout.setOnClickListener(myListener);

	}

	public class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.main1_user_home_profilelayout:
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new UserFragment());
				MainActivity.gotoNextPager();
				break;
			case R.id.main1_user_home_profile_image:
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new UserFragment());
				MainActivity.gotoNextPager();
				break;
			case R.id.main1_user_home_settings_layout:
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new UserFragment());
				MainActivity.gotoNextPager();
				break;
			case R.id.main1_user_home_following:
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new UserFragment());
				MainActivity.gotoNextPager();
				break;
			case R.id.main1_user_home_follower:
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new UserFragment());
				MainActivity.gotoNextPager();
				break;
			case R.id.main1_user_home_message:
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new UserFragment());
				MainActivity.gotoNextPager();
				break;
			default:
				break;

			}

		}

	}

	private void initAdapter() {
		initData();
		timelineAdapter = new TimeLineAdapter(mContext, photos);
		timelineListView.setAdapter(timelineAdapter);

	}

	// 模拟数据
	private void initData() {
		/*
		 * (String pid, String type, String photoURL, String videoURL, String
		 * photoMessage, String likeNum, String commentNum, String photoTag,
		 * String authorName, String authorHeadURL, String photoTime)
		 */

		photos.add(new TimeLinePhoto("pid", "type", "photoURL", "videoURL",
				"photoMessage", "likeNum", "commentNum", "authorHeadURL",
				"authorName", "authorHeadURL", "photoTime"));
		photos.add(new TimeLinePhoto("pid", "type", "photoURL", "videoURL",
				"photoMessage", "likeNum", "commentNum", "authorHeadURL",
				"authorName", "authorHeadURL", "photoTime"));
		photos.add(new TimeLinePhoto("pid", "type", "photoURL", "videoURL",
				"photoMessage", "likeNum", "commentNum", "authorHeadURL",
				"authorName", "authorHeadURL", "photoTime"));

	}

	@Override
	public void onPositionChanged(TimeLineListView listView, int position,
			View scrollBarPanel) {
		TextView dateStr = (TextView) getActivity().findViewById(
				R.id.clock_digital_time);
		dateStr.setText("2014年1月1日");
		TimeLinePhoto photo = photos.get(position);
		int hour = (int) (Math.random() * 12);
		int minute = (int) (Math.random() * 12);
		RotateAnimation[] rotateAnims = computeAni(minute, hour);
		ImageView minuteImage = (ImageView) getActivity().findViewById(
				R.id.clock_face_minute);
		minuteImage.setImageResource(R.drawable.clock_minute_rotatable);
		minuteImage.startAnimation(rotateAnims[0]);

		ImageView hourImage = (ImageView) getActivity().findViewById(
				R.id.clock_face_minute);
		hourImage.setImageResource(R.drawable.clock_hour_rotatable);
		hourImage.startAnimation(rotateAnims[1]);

	}

	@Override
	public void onScollPositionChanged(View scrollBarPanel, int top) {
		MarginLayoutParams layoutParams = (MarginLayoutParams) timebarLayout
				.getLayoutParams();
		layoutParams.setMargins(0, top, 0, 0);
		timebarLayout.setLayoutParams(layoutParams);

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	private float[] lastTime = { 0f, 0f };

	private float[] computMinAndHour(int currentMinute, int currentHour) {
		float minuteRadian = 6f * currentMinute;

		float hourRadian = 360f / 12f * currentHour;

		float[] rtn = new float[2];
		rtn[0] = minuteRadian;
		rtn[1] = hourRadian;
		return rtn;
	}

	private RotateAnimation[] computeAni(int min, int hour) {

		RotateAnimation[] rtnAni = new RotateAnimation[2];
		float[] timef = computMinAndHour(min, hour);
		// AnimationSet as = new AnimationSet(true);
		// 创建RotateAnimation对象
		// 0--图片从哪开始旋转
		// 360--图片旋转多少度
		// Animation.RELATIVE_TO_PARENT, 0f,// 定义图片旋转X轴的类型和坐标
		// Animation.RELATIVE_TO_PARENT, 0f);// 定义图片旋转Y轴的类型和坐标
		RotateAnimation ra = new RotateAnimation(lastTime[0], timef[0],
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		ra.setFillAfter(true);
		ra.setFillBefore(true);
		// 设置动画的执行时间
		ra.setDuration(800);
		// 将RotateAnimation对象添加到AnimationSet
		// as.addAnimation(ra);
		// 将动画使用到ImageView
		rtnAni[0] = ra;

		lastTime[0] = timef[0];

		// AnimationSet as2 = new AnimationSet(true);
		// 创建RotateAnimation对象
		// 0--图片从哪开始旋转
		// 360--图片旋转多少度
		// Animation.RELATIVE_TO_PARENT, 0f,// 定义图片旋转X轴的类型和坐标
		// Animation.RELATIVE_TO_PARENT, 0f);// 定义图片旋转Y轴的类型和坐标
		RotateAnimation ra2 = new RotateAnimation(lastTime[1], timef[1],
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		// 设置动画的执行时间
		ra2.setFillAfter(true);
		ra2.setFillBefore(true);
		ra2.setDuration(800);
		// 将RotateAnimation对象添加到AnimationSet
		// as2.addAnimation(ra2);
		// 将动画使用到ImageView
		rtnAni[1] = ra2;
		lastTime[1] = timef[1];
		return rtnAni;
	}
}
