package com.baby.model;

public class User {
	
	private final String mId;
	private final String mName;
	private final String mDescription;
	private String mCoverImageUrl;
	private String mLocation;
	private String mProfileImageUrlMini;
	private String mProfileImageUrlNormal;
	private String mProfileImageUrlBigger;
	private String mProfileImageUrlOriginal;
	private final int mStatusesCount;
	private final int mFriendsCount;
	private final int mFollowersCount;
	private int mListedCount;
	private boolean mFollowsCurrentUser;
	private boolean mCurrentUserFollows;
	
	

	public User(String mId, String mName, String mDescription,
			String mCoverImageUrl, String mLocation,
			String mProfileImageUrlMini, String mProfileImageUrlNormal,
			String mProfileImageUrlBigger, String mProfileImageUrlOriginal,
			int mStatusesCount, int mFriendsCount, int mFollowersCount,
			int mListedCount, boolean mFollowsCurrentUser,
			boolean mCurrentUserFollows) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mDescription = mDescription;
		this.mCoverImageUrl = mCoverImageUrl;
		this.mLocation = mLocation;
		this.mProfileImageUrlMini = mProfileImageUrlMini;
		this.mProfileImageUrlNormal = mProfileImageUrlNormal;
		this.mProfileImageUrlBigger = mProfileImageUrlBigger;
		this.mProfileImageUrlOriginal = mProfileImageUrlOriginal;
		this.mStatusesCount = mStatusesCount;
		this.mFriendsCount = mFriendsCount;
		this.mFollowersCount = mFollowersCount;
		this.mListedCount = mListedCount;
		this.mFollowsCurrentUser = mFollowsCurrentUser;
		this.mCurrentUserFollows = mCurrentUserFollows;
	}
	
	public String getmCoverImageUrl() {
		return mCoverImageUrl;
	}
	public void setmCoverImageUrl(String mCoverImageUrl) {
		this.mCoverImageUrl = mCoverImageUrl;
	}
	public String getmLocation() {
		return mLocation;
	}
	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}
	public String getmProfileImageUrlMini() {
		return mProfileImageUrlMini;
	}
	public void setmProfileImageUrlMini(String mProfileImageUrlMini) {
		this.mProfileImageUrlMini = mProfileImageUrlMini;
	}
	public String getmProfileImageUrlNormal() {
		return mProfileImageUrlNormal;
	}
	public void setmProfileImageUrlNormal(String mProfileImageUrlNormal) {
		this.mProfileImageUrlNormal = mProfileImageUrlNormal;
	}
	public String getmProfileImageUrlBigger() {
		return mProfileImageUrlBigger;
	}
	public void setmProfileImageUrlBigger(String mProfileImageUrlBigger) {
		this.mProfileImageUrlBigger = mProfileImageUrlBigger;
	}
	public String getmProfileImageUrlOriginal() {
		return mProfileImageUrlOriginal;
	}
	public void setmProfileImageUrlOriginal(String mProfileImageUrlOriginal) {
		this.mProfileImageUrlOriginal = mProfileImageUrlOriginal;
	}
	public int getmListedCount() {
		return mListedCount;
	}
	public void setmListedCount(int mListedCount) {
		this.mListedCount = mListedCount;
	}
	public boolean ismFollowsCurrentUser() {
		return mFollowsCurrentUser;
	}
	public void setmFollowsCurrentUser(boolean mFollowsCurrentUser) {
		this.mFollowsCurrentUser = mFollowsCurrentUser;
	}
	public boolean ismCurrentUserFollows() {
		return mCurrentUserFollows;
	}
	public void setmCurrentUserFollows(boolean mCurrentUserFollows) {
		this.mCurrentUserFollows = mCurrentUserFollows;
	}
	public String getmId() {
		return mId;
	}
	public String getmName() {
		return mName;
	}
	public String getmDescription() {
		return mDescription;
	}
	public int getmStatusesCount() {
		return mStatusesCount;
	}
	public int getmFriendsCount() {
		return mFriendsCount;
	}
	public int getmFollowersCount() {
		return mFollowersCount;
	}
	
	
	
}
