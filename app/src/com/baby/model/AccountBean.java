package com.baby.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class AccountBean {
	
	private static final String KEY_ID = "id";
    private static final String KEY_OAUTH_TOKEN = "oAuthToken";
    private static final String KEY_OAUTH_SECRET = "oAuthSecret";
    private static final String KEY_LISTS = "lists";
    private static final String KEY_PROFILE_IMAGE_URL = "profileImageUrl";

    
	
	private String mId;
	private String mOAuthToken;
	private String mOAuthSecret;
	private ArrayList<LaneBean> mLaneBeans;
	private Vector<List> mList;
	private boolean mShouldRefreshLists;
	private final Context mContext;
	private String mProfileImageUrl;
	
	public AccountBean(Context context,User user,String oAuthToken,
			String OAuthSecret,String profileImageUrl){
		mContext = context;
		mId = user.getmId();
		mOAuthToken = oAuthToken;
		mOAuthSecret = OAuthSecret;
		mProfileImageUrl = profileImageUrl;
		initCommon(null);
		
	}
	
	private void initCommon(ArrayList<String> displayedLanes){
		mShouldRefreshLists = true;
		if(mList == null){
			mList = new Vector<List>();
		}
		configureLaneDefinitions(displayedLanes);
	}
	
	private void configureLaneDefinitions(ArrayList<String> displayedLanes) {
		mLaneBeans.clear();
		mLaneBeans
			.add(new LaneBean());
		
		/*synchronized(mList){
			
		}*/
		
	}
	
	public String getAccountKey(){
		return null;
	}

	public String toString(){
		JSONObject object = new JSONObject();
		try{
			object.put(KEY_ID, mId);
            object.put(KEY_OAUTH_TOKEN, mOAuthToken);
            object.put(KEY_OAUTH_SECRET, mOAuthSecret);
            object.put(KEY_PROFILE_IMAGE_URL, mProfileImageUrl);
            
            if(mList.size()>0){
            	JSONArray listArray = new JSONArray();
            	for(List list: mList){
            		listArray.put(list.toString());
            	}
            	object.put(KEY_LISTS, listArray);
            }
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		return object.toString();
		
	}

}
