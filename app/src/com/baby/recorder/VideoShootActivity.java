package com.baby.recorder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.baby.R;
import com.baby.util.MP4ParserUtil;

public class VideoShootActivity extends Activity implements
		SurfaceHolder.Callback {

	private Context mContext;
	private SurfaceHolder holder;
	private Camera camera = null;

	public static boolean isRecording = false;
	private boolean hasFrontCamera = false;

	private CameraManager mCameraManager;
	private RecordManager mRecordManager;

	// private VideoUtil mVideoUtil;
	private ProgressDialog pd = null;

	private float mRecord_Time = 0;// 录制的时间
	private float mCurrentPosition = 0;// 当前录制的时间
	private static final int MAX_TIME = 10;// 最长录音时间

	private String workingPath = "/sdcard/Camily/VideoCache/";
	private ArrayList<String> videosPath = new ArrayList<String>();
	private int videoPart = 0;

	public static String video_path = "/sdcard/Camily/Video/video.3gp";
	public static String photo_path = "/sdcard/Camily/Video/photo.jpg";
	private File video_file;
	private File photo_file;

	final Handler handler = new Handler();
	Runnable longPressed = new Runnable() {

		@Override
		public void run() {
			// mRecordManager.recorderStart(mContext, camera);
			String tempPath = workingPath + "tempvideo" + videoPart + ".mp4";
			mRecordManager.resumeRecording(tempPath,camera);
			//progressStart();
			
			

		}

	};
	
	public void progressStart(){
		mRecord_Time = mCurrentPosition;
		while (isRecording) {
			// 大于最大录音时间则停止录音
			if (mRecord_Time >= MAX_TIME) {

				// mergebtn_layout.setVisibility(View.GONE);
				mRecordHandler.sendEmptyMessage(0);

			} else {
				try {
					// 每隔20毫秒更新界面显示
					Thread.sleep(20);
					mRecord_Time += 0.02;
					progressBar
					.setProgress((int) (mRecord_Time * 1000));

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	Runnable pressCancelled = new Runnable() {

		@Override
		public void run() {
			if (isRecording) {
				isRecording = false;
				mRecordManager.pauseRecording();

				mCurrentPosition = mRecord_Time;
				
				// mergebtn_layout.setVisibility(View.VISIBLE);
				/**
				 * 极为重要
				 */
				// 将上一个的视频添加入集合中，在创建下一个视频的路径
				videoPart++;
			}

		}

	};

	private PowerManager.WakeLock mWakeLock;
	private static final String TAG = VideoShootActivity.class.getSimpleName();
	private boolean prepareSetFocus = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videoshoot);

		mContext = this;
		/**
		 * 获取屏幕宽度和高度
		 */
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;

		this.mCameraManager = CameraManager.getInstance(this,
				this.mScreenWidth, this.mScreenHeight);
		this.mWakeLock = ((PowerManager) getSystemService("power"))
				.newWakeLock(10, TAG);
		this.mWakeLock.acquire();

		configureFile();
		configureView();
		startPreviewing();

	}

	private void startPreviewing() {
		progressBar.setMax((int) MAX_TIME * 1000);
		progressBar.setProgress(0);

		holder = surfaceView.getHolder();
		holder.addCallback(this);
		holder.setKeepScreenOn(true);

		/**
		 * 极为重要
		 */
		
		mRecordManager = new RecordManager(holder);
		camera = mRecordManager.cameraStart(camera);
		surfaceView.setVisibility(View.VISIBLE);

	}

	private void changePreviewOverlayHeight(FrameLayout paramFrameLayout) {

		LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams) paramFrameLayout
				.getLayoutParams();
		localLayoutParams.height = mScreenWidth;
		paramFrameLayout.setLayoutParams(localLayoutParams);
	}

	private void configureFile() {
		File dir = new File(workingPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// clear up
		if (dir.isDirectory()) {
			File files[] = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (!file.isDirectory()) {
					file.delete();
				}
			}
		}
	}

	private int mScreenHeight;
	private int mScreenWidth;

	FrameLayout frameLayout;
	ProgressBar progressBar;
	SurfaceView surfaceView;
	VideoView videoView;
	ImageView photoView;
	ImageView backImage;
	ImageView doneImage;

	private void configureView() {

		frameLayout = (FrameLayout) this
				.findViewById(R.id.videoshoot_camere_framelayout);
		progressBar = (ProgressBar) this
				.findViewById(R.id.videoshoot_progressbar);
		surfaceView = (SurfaceView) this
				.findViewById(R.id.videoshoot_surfaceview);
		videoView = (VideoView) this.findViewById(R.id.videoshoot_videoview);
		photoView = (ImageView) this.findViewById(R.id.videoshoot_photo_iv);
		backImage = (ImageView) this.findViewById(R.id.videoshoot_back);
		doneImage = (ImageView) this.findViewById(R.id.videoshoot_done);

		LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams) frameLayout
				.getLayoutParams();
		localLayoutParams.height = mScreenWidth;
		frameLayout.setLayoutParams(localLayoutParams);

		/*
		 * if (Camera.getNumberOfCameras() > 1) { hasFrontCamera = true;
		 * u3b_video3_switchbtn.setVisibility(View.VISIBLE); } else {
		 * hasFrontCamera = false;
		 * u3b_video3_switchbtn.setVisibility(View.GONE); }
		 */

		// setVisibility()
		// visible 0
		// invisible 4
		// gone 8

		videoView.setVisibility(View.GONE);
		photoView.setVisibility(View.GONE);
		backImage.setVisibility(View.VISIBLE);
		doneImage.setVisibility(View.GONE);

		frameLayout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				int i = MotionEventCompat.getActionMasked(event);
				switch (i) {
				default:
					return false;
				case MotionEvent.ACTION_DOWN:
					if (prepareSetFocus) {
						mCameraManager.setFocusArea(event.getX(), event.getY());
					}
					isRecording = true;
					Log.e("Boolean1",isRecording+"");
					handler.postDelayed(longPressed, 100);

					return true;
				case MotionEvent.ACTION_MOVE:
					return true;
				case MotionEvent.ACTION_UP:
					if (isRecording) {
						isRecording = false;
						Log.e("Boolean2",isRecording+"");
						handler.post(pressCancelled);
					}
					return true;
				case MotionEvent.ACTION_CANCEL:
					if (isRecording) {
						isRecording = false;
						handler.post(pressCancelled);
					}
					return true;
				case MotionEvent.ACTION_OUTSIDE:
					return true;
				}
			}
		});

		doneImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				videoPart = videoPart - 1;
				doneImage.setVisibility(View.GONE);

				mRecordManager.stopRecording(camera);
				isRecording = false;

				for (int i = 0; i <= videoPart; i++) {
					videosPath.add("tempvideo" + i + ".mp4");

				}

				photoView.setVisibility(View.INVISIBLE);
				saveVideoBitmap();

				videoView.setVisibility(View.GONE);
				surfaceView.setVisibility(View.GONE);

				progressBar.setMax((int) mRecord_Time * 1000);
				progressBar.setProgress(0);

				// 合并视频
				mergeVideos();

			}
		});

	}

	private void saveVideoBitmap() {
		String path = workingPath + videosPath.get(0);
		Log.e("TAG", path);
		Bitmap bitmap = mRecordManager.getVideoThumbnail(path);
		mRecordManager.BitmapToFile(bitmap, photo_path);
		photo_file = new File(photo_path);
		photoView.setImageURI(Uri.fromFile(photo_file));
		photoView.setVisibility(View.VISIBLE);
	}
	
	Handler mRecordHandler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0: // 录制时间超过10秒 或 录制结束
				if (isRecording == true) {

					mRecordManager.stopRecording(camera);
					isRecording = false;

					for (int i = 0; i <= videoPart; i++) {
						videosPath.add("tempvideo" + i + ".mp4");

					}

					photoView.setVisibility(View.INVISIBLE);
					saveVideoBitmap();

					videoView.setVisibility(View.GONE);
					surfaceView.setVisibility(View.GONE);

					progressBar
							.setMax((int) mRecord_Time * 1000);
					progressBar.setProgress(0);

					// 合并视频
					mergeVideos();

				}
				break;

			case 1:
				// 根据录音时间显示进度条
				progressBar
						.setProgress((int) (mRecord_Time * 1000));
				break;
			}
		}

	};
	
	/**
	 * 超给力的 MP4 视频合并
	 */
	public void mergeVideos() {
		new MP4ParserUtil(mContext, workingPath, videosPath, video_path);
		video_file = new File(video_path);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// 相机预览

		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onDestroy() {
		if (camera != null) {
			mRecordManager.stopRecording(camera);
		}
		super.onDestroy();
	}

}
