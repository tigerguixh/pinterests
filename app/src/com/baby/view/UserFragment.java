package com.baby.view;

import java.util.ArrayList;

import com.baby.R;
import com.baby.widget.ChildViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UserFragment extends Fragment {

	private ChildViewPager mViewPager;
	private ViewPagerAdapter mAdapter;
	private MyPagerListener myListener;

	public static ArrayList<Fragment> pagerList = null;

	public static Fragment pageFragment1 = null;
	public static Fragment pageFragment2 = null;
	public static Fragment pageFragment3 = null;
	public static int page_position = 0;

	public LinearLayout userLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_user, null);

		mViewPager = (ChildViewPager) rootView
				.findViewById(R.id.user_viewpager);
		pagerList = new ArrayList<Fragment>();

		pageFragment1 = new UserLikesFragment();
		pageFragment2 = new UserRecordsFragment();
		pageFragment3 = new UserAboutFragment();

		pagerList.add(pageFragment1);

		pagerList.add(pageFragment2);

		pagerList.add(pageFragment3);

		mAdapter = new ViewPagerAdapter(getFragmentManager());
		mViewPager.setAdapter(mAdapter);

		mViewPager.setCurrentItem(page_position);

		myListener = new MyPagerListener();
		mViewPager.setOnPageChangeListener(myListener);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
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

	public class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public long getItemId(int position) {
			return position;
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
				fragment = pagerList.get(1);
			}
			if (fragment == null) {
				if (position == 0) {
					fragment = new UserLikesFragment();
				} else if (position == 1) {
					fragment = new UserRecordsFragment();
				} else if (position == 2) {
					fragment = new UserAboutFragment();
				}
			}
			return fragment;
		}
	}

	@Override
	public void onResume() {
		mViewPager.setCurrentItem(page_position);

		if (pagerList.size() < 3) {
			pagerList = new ArrayList<Fragment>();

			pageFragment1 = new UserLikesFragment();
			pageFragment2 = new UserRecordsFragment();
			pageFragment3 = new UserAboutFragment();

			pagerList.add(pageFragment1);

			pagerList.add(pageFragment2);

			pagerList.add(pageFragment3);
		}
		super.onResume();
	}

}
