package com.baby.model;

public class TimeLinePhoto {
	
	public static final int RECORD_TYPE_PHOTO = 1;
	public static final int RECORD_TYPE_VIDEO = 2;
	
	private String pid;
	private String type;
	private String photoURL;
	private String videoURL;
	private String photoMessage;
	private String likeNum;
	private String commentNum;
	private String photoTag;
	private String authorName;
	private String authorHeadURL;
	private String photoTime;
	
	public TimeLinePhoto(String pid, String type, String photoURL,
			String videoURL, String photoMessage, String likeNum,
			String commentNum, String photoTag, String authorName,
			String authorHeadURL, String photoTime) {
		super();
		this.pid = pid;
		this.type = type;
		this.photoURL = photoURL;
		this.videoURL = videoURL;
		this.photoMessage = photoMessage;
		this.likeNum = likeNum;
		this.commentNum = commentNum;
		this.photoTag = photoTag;
		this.authorName = authorName;
		this.authorHeadURL = authorHeadURL;
		this.photoTime = photoTime;
	}
	
	

}
