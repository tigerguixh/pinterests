package com.baby.view;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.baby.App;
import com.baby.R;

public class MainActivity extends FragmentActivity {

	private static ViewPager mViewPager;
	private static ViewPagerAdapter mAdapter;
	private MyPagerListener myListener;
	public static ArrayList<Fragment> pagerList = null;

	public static Fragment mainFragment = null;
	public static int page_position = 0;

	private Context context = null;

	public App getApp() {
		return (App) getApplication();
	}

	public static void addMainPager(Fragment fragment) {
		pagerList.add(fragment);
		mAdapter.notifyDataSetChanged();
	}

	// 改变page_position的操作全部在goto~()操作中
	public static void gotoNextPager() {
		page_position += 1;
		Log.e("goto", page_position+"");
		mViewPager.setCurrentItem(page_position);
		//mAdapter.notifyDataSetChanged();
	}

	public static void gotoPrePager() {
		page_position -= 1;
		Log.e("goto", page_position+"");
		mViewPager.setCurrentItem(page_position);
		//mAdapter.notifyDataSetChanged();
	}

	public static void deleteLastPager() {
		pagerList.remove(pagerList.size() - 1);
		mAdapter.notifyDataSetChanged();
	}

	// 如果使用下面的deleteOtherPager,就只能在gotoMainPager()前
	// 或者是在addMainPager()前
	public static void deleteOtherPager() {
		for (int i = pagerList.size() - 1; i > 0; i--)
			pagerList.remove(i);
		mAdapter.notifyDataSetChanged();
	}

	public static void gotoMainPager() {
		page_position = 0;
		Log.e("goto", page_position+"");
		mViewPager.setCurrentItem(0);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main0);
		
		context = this;

		mViewPager = (ViewPager) this.findViewById(R.id.main0_viewpager);
		pagerList = new ArrayList<Fragment>();

		mainFragment = new MainFragment();

		pagerList.add(mainFragment);

		mAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		// the first args boolean boolean reverseDrawingOrder,
		mViewPager.setPageTransformer(false, new DepthPageTransformer());

		mViewPager.setCurrentItem(page_position);

		myListener = new MyPagerListener();
		mViewPager.setOnPageChangeListener(myListener);

	}

	public class MyPagerListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {

			mViewPager.setCurrentItem(position);
			page_position = position;

		}

		@Override
		public void onPageScrolled(int position, float arg1, int arg2) {
			
		}

		@Override
		public void onPageScrollStateChanged(int position) {

		}

	}

	public class ViewPagerAdapter extends FragmentStatePagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public int getCount() {
			return pagerList.size();
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			if (position < pagerList.size()) {
				fragment = pagerList.get(position);
			} else {
				fragment = pagerList.get(0);
			}
			if (fragment == null) {
				if (position == 0) {
					fragment = new MainFragment();
				}
			}
			return fragment;
		}

		@Override
		public int getItemPosition(Object object) {
			Fragment fragment = (Fragment) object;
			int position = pagerList.indexOf(fragment);
			Log.e("Pos",position+"");
			Log.e("Page_Position",page_position+"");
			
			if (position==-1) {
				//说明 该fragment已经被删掉了
				return POSITION_NONE;
			} else {
				return POSITION_UNCHANGED;
			}
			// return PagerAdapter.POSITION_NONE;-2
			// return PagerAdapter.POSITION_UNCHANGED;-1
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onResume() {
		mViewPager.setCurrentItem(page_position);

		if (pagerList == null) {
			pagerList = new ArrayList<Fragment>();

			mainFragment = new MainFragment();
		}
		super.onResume();
	}

	public class DepthPageTransformer implements ViewPager.PageTransformer {

		private static final float MIN_SCALE = 0.85f;

		@Override
		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();

			if (position < -1) { // [-Infinity,-1)
				// This page is way off-screen to the left.
				view.setAlpha(0);

			} else if (position <= 0) { // [-1,0]
				// Fade the page out.
				view.setAlpha(1 - position);

				// Counteract the default slide transition
				view.setTranslationX(pageWidth * -position);

				// Scale the page down (between MIN_SCALE and 1)
				float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
						* (1 - Math.abs(position));
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

			} else if (position <= 1) { // (0,1]
				view.bringToFront();
				// Use the default slide transition when moving to the left page
				view.setAlpha(1);
				view.setTranslationX(0);
				view.setScaleX(1);
				view.setScaleY(1);

			} else { // (1,+Infinity]
				// This page is way off-screen to the right.
				view.setAlpha(0);
			}

		}

	}

	@Override
	public void onBackPressed() {
		if(MainActivity.page_position==1){
			mViewPager.setCurrentItem(0);
			return;
		}
		
		if (MainActivity.page_position > 0) {

			deleteLastPager();
			gotoPrePager();	
			
		} else {
			if (MainFragment.page_position != 1) {
				MainFragment.gotoMiddleView();
			} else {
				this.finish();
			}
		}

		return;
	}
}
