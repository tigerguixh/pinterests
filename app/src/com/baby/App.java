package com.baby;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.baby.model.AccountBean;
import com.baby.util.LazyImageLoader;

public class App extends Application {

	private static String mAppVersionName;

	public static String getAppVersionName() {
		return mAppVersionName;
	}

	private ArrayList<AccountBean> mAccounts;
	private Integer mCurrentAccountIndex;
	
	private SharedPreferences mPreferences;

	public AccountBean getCurrentAccount() {
		return mCurrentAccountIndex != null ? mAccounts
				.get(mCurrentAccountIndex) : null;
	}

	public AccountBean getAccountByKey(String accountKey) {
		for (AccountBean account : mAccounts) {
			if (account.getAccountKey().equals(accountKey)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			mAppVersionName = packageInfo.versionName;
			
			List<ApplicationInfo> apps = packageManager
					.getInstalledApplications(PackageManager.GET_META_DATA);
			
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		mPreferences.edit().putInt(SharedPreferencesConstants.VERSION, 
				Constant.SHARED_PREFERENCES_VERSION);
		
		mAccounts = new ArrayList<AccountBean>();
		
	}
	
	public boolean isOnline(){
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnectedOrConnecting();
	}
	
	public void restartApp(Activity currentActivity){
		Intent intent = getBaseContext().getPackageManager()
				.getLaunchIntentForPackage(getBaseContext().getPackageName());
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NO_ANIMATION
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		currentActivity.overridePendingTransition(0, 0);
		currentActivity.startActivity(intent);
	}
	
	private LazyImageLoader mProfileImageLoader, mPreviewImageLoader;
	
	public LazyImageLoader getPreviewImageLoader(){
		if(mPreviewImageLoader == null){
			final int preview_image_width = getResources()
					.getDimensionPixelSize(R.dimen.image_preview_width);
			final int preview_image_height = getResources()
					.getDimensionPixelSize(R.dimen.image_preview_height);
			mPreviewImageLoader = new LazyImageLoader(this,
                    Constant.DIR_NAME_CACHED_THUMBNAILS, R.drawable.white,
                    preview_image_width, preview_image_height, 30);
		}
		return mPreviewImageLoader;
	}
	
	public LazyImageLoader getProfileImageLoader(){
		if(mProfileImageLoader == null){
			final int profile_image_size = getResources()
                    .getDimensionPixelSize(R.dimen.avatar_width_height_large);
            mProfileImageLoader = new LazyImageLoader(this,
                    Constant.DIR_NAME_PROFILE_IMAGES,
                    R.drawable.ic_contact_picture, profile_image_size,
                    profile_image_size, 60);
		}
		return mProfileImageLoader;
	}
	
	
	
	
	
	
	
	
	
	

}
