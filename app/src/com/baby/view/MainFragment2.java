package com.baby.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baby.R;
import com.baby.lib.pla.MultiColumnListView;
import com.baby.lib.pla.PLA_AbsListView.LayoutParams;
import com.baby.lib.pla.PLA_AdapterView;
import com.baby.lib.pla.PLA_AdapterView.OnItemClickListener;
import com.baby.lib.pla.PLA_ListView;
import com.baby.recorder.VideoShootActivity;

public class MainFragment2 extends Fragment {
	private Context mContext;
	private PLA_ListView mListView = null;
	// private PLA_AdapterView<BaseAdapter> mAdapterView = null;
	private MyAdapter mAdapter = null;
	private ArrayList<HashMap<String, String>> mapList = null;

	private View headView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.main2_fragment, container,
				false);
		headView = LayoutInflater.from(rootView.getContext()).inflate(
				R.layout.main2_header, null);
		mListView = (PLA_ListView) rootView.findViewById(R.id.main2_listview);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mContext = this.getActivity();
		MultiColumnListView.changeColumn(2);
		// MultiColumnListView.setPadding(15);
		// mAdapterView =
		// (PLA_AdapterView<BaseAdapter>)getActivity().findViewById(R.id.main2_listview);

		initView();
		initListener();

	}

	private void initListener() {
		Button photoBtn = (Button)headView.findViewById(R.id.photo_bt);
		Button videoBtn = (Button)headView.findViewById(R.id.video_bt);
		videoBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,VideoShootActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	private void initView() {

		/*
		 * if (headView != null) { mListView.removeHeaderView(headView);
		 * headView = LayoutInflater.from(mContext).inflate(
		 * R.layout.main2_header, null); }
		 */

		// initHead();
		// addHeaderView 必须在 setAdapter前面
		LinearLayout layout = (LinearLayout) headView
				.findViewById(R.id.main2_header_layout);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		mListView.addHeaderView(layout);

		mapList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 11; i++)
			mapList.add(new HashMap<String, String>());

		mAdapter = new MyAdapter(mContext, mapList);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(PLA_AdapterView<?> parent, View view,
					int position, long id) {
				MainActivity.deleteOtherPager();
				MainActivity.addMainPager(new FeedFragment());

				MainActivity.gotoNextPager();
			}
		});
	}

	private class MyAdapter extends BaseAdapter {

		private Context context;
		private ArrayList<HashMap<String, String>> mapList;
		private HashMap<String, String> map;

		public MyAdapter(Context context,
				ArrayList<HashMap<String, String>> mapList) {
			this.context = context;
			this.mapList = mapList;
		}

		@Override
		public int getCount() {
			return mapList.size();
		}

		@Override
		public Object getItem(int pos) {
			return mapList.get(pos);
		}

		@Override
		public long getItemId(int pos) {
			return pos;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.main2_list_item, null);
				ViewHolder holder = new ViewHolder();
				holder.main_layout = (LinearLayout) convertView
						.findViewById(R.id.main2_list_item_layout);

				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

				params.bottomMargin = 8;
				params.leftMargin = 4;
				params.rightMargin = 4;

				holder.main_layout.setLayoutParams(params);
				// mAdapterView.getChildAt(0).
			}

			return convertView;
		}

		class ViewHolder {
			LinearLayout main_layout;
		}
	}

	@Override
	public void onResume() {
		super.onResume();

	}
}
