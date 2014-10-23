package com.baby.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.baby.R;
import com.baby.model.TimeLinePhoto;

public class TimeLineAdapter extends BaseAdapter{
	
	private Context context;
	
	private List<TimeLinePhoto> photos;
	
	public TimeLineAdapter(Context context,List<TimeLinePhoto> photos){
		this.context = context;
		this.photos = photos;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photos.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return photos.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
	
		View view = convertView;
		ViewHolder holder;
		if(view == null
				|| (holder=(ViewHolder)view.getTag()).pos!= pos){
			view  = LayoutInflater.from(context).inflate(R.layout.main1_fragment_item, null);
			
			holder = new ViewHolder();

			holder.photoImage = (ImageView)view.findViewById(R.id.main1_fragment_item_imageview);
			holder.photoText =(TextView)view.findViewById(R.id.main1_fragment_item_phototext);
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder)view.getTag();
		}
		
		
		return view;
	}
	
	static class ViewHolder{
		int pos = -1;
		SurfaceView surface;
		ImageView photoImage;
		ProgressBar progressBar;
		ImageView photoType;
		ImageView start;
		
		TextView photoText;
		TextView likeNum;
		TextView commentNum;
		TextView photoTag;
	
	}

}
