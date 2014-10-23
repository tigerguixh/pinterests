package com.baby.view;

import java.util.ArrayList;

import com.baby.R;
import com.baby.widget.ChildViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainFragment extends Fragment{

	private static ChildViewPager mViewPager;
	private ViewPagerAdapter mAdapter;
	private MyPagerListener myListener;
	public static ArrayList<Fragment> pagerList = null;

	public static Fragment pageFragment1 = null;
	public static Fragment pageFragment2 = null;
	public static Fragment pageFragment3 = null;
	public static int page_position = 1;

	public ImageView imageNotif;
	public ImageView imageLogo;
	public ImageView imageUser;
	public ImageView imageSearch;
	
	public static void gotoMiddleView(){
		mViewPager.setCurrentItem(1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_main, container,
				false);

		// pay attention to this 3 widgets, look at rootView!
		imageLogo = (ImageView) rootView.findViewById(R.id.main_header_logo);
		imageUser = (ImageView) rootView.findViewById(R.id.main_header_user);
		imageSearch = (ImageView) rootView.findViewById(R.id.main_header_search);

		mViewPager = (ChildViewPager) rootView.findViewById(R.id.main_viewpager);
		pagerList = new ArrayList<Fragment>();

		pageFragment1 = new MainFragment1();
		pageFragment2 = new MainFragment2();
		pageFragment3 = new MainFragment3();

		pagerList.add(pageFragment1);

		pagerList.add(pageFragment2);

		pagerList.add(pageFragment3);

		mAdapter = new ViewPagerAdapter(getFragmentManager());
		mViewPager.setAdapter(mAdapter);
		

		mViewPager.setCurrentItem(page_position);

		myListener = new MyPagerListener();
		mViewPager.setOnPageChangeListener(myListener);

		imageUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (page_position != 0) {
					page_position = 0;
					mViewPager.setCurrentItem(page_position);
				}
			}
		});
		imageLogo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (page_position != 1) {
					page_position = 1;
					mViewPager.setCurrentItem(page_position);
				}
			}
		});
		imageSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (page_position != 2) {
					page_position = 2;
					mViewPager.setCurrentItem(page_position);
				}
			}
		});

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}
	

	public class MyPagerListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {

			mViewPager.setCurrentItem(position);
			page_position = position;
			switch (position) {
			case 0:

				imageUser.setImageResource(R.drawable.ic_tab_me_selected);
				imageLogo.setImageResource(R.drawable.app_logo);
				imageSearch.setImageResource(R.drawable.ic_tab_search);
				break;
			case 1:

				imageUser.setImageResource(R.drawable.ic_tab_me);
				imageLogo.setImageResource(R.drawable.app_logo_selected);
				imageSearch.setImageResource(R.drawable.ic_tab_search);
				break;
			case 2:

				imageUser.setImageResource(R.drawable.ic_tab_me);
				imageLogo.setImageResource(R.drawable.app_logo);
				imageSearch.setImageResource(R.drawable.ic_tab_search_selected);
				break;
			}

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
					fragment = new MainFragment1();
				} else if (position == 1) {
					fragment = new MainFragment2();
				} else if (position == 2) {
					fragment = new MainFragment3();
				}
			}
			return fragment;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onResume() {
		mViewPager.setCurrentItem(page_position);

		if (pagerList.size() < 3) {
			pagerList = new ArrayList<Fragment>();

			pageFragment1 = new MainFragment1();
			pageFragment2 = new MainFragment2();
			pageFragment3 = new MainFragment3();

			pagerList.add(pageFragment1);

			pagerList.add(pageFragment2);

			pagerList.add(pageFragment3);
		}
		super.onResume();
	}

	
	
}
