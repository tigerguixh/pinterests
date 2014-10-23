package com.baby.recorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.ThumbnailUtils;
import android.provider.MediaStore.Video.Thumbnails;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * 
 * 
 * @author wei
 * 
 */
public class RecordManager {

	private MediaRecorder recorder;
	private SurfaceHolder holder;

	public static int cameraPosition = 0; // 0���� 1ǰ��

	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;

	public static int bestWidth = 0;
	public static int bestHeight = 0;

	public RecordManager(SurfaceHolder holder) {
		this.holder = holder;

	}

	public int getCamPosition() {
		return cameraPosition;
	}

	public void switchCam() {
		if (cameraPosition == 0) {
			cameraPosition = 1;
		} else {
			cameraPosition = 0;
		}

	}

	public Camera cameraStart(Camera camera) {
		try {

			if (cameraPosition == 0) {

				camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);// �򿪺�������ͷ
				Camera.Parameters camParams = camera.getParameters();
				camera.lock();
				camera.setDisplayOrientation(90);
				camParams.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

				/*
				 * List<Camera.Size> previewSizes =
				 * camParams.getSupportedPreviewSizes();
				 * if(previewSizes.size()>1){ Iterator<Camera.Size> cei =
				 * previewSizes.iterator(); while(cei.hasNext()){ Camera.Size
				 * aSize = cei.next();
				 * Log.e("CAMERA-Size",aSize.width+"  "+aSize.height);
				 * if(aSize.width>bestWidth&&aSize.width<=WIDTH){ bestWidth =
				 * aSize.width; bestHeight = aSize.height; } bestWidth =
				 * aSize.width; bestHeight = aSize.height;
				 * 
				 * } Log.e("CAMERA","2.1"); if(bestHeight!=0 && bestWidth!=0){
				 * camParams.setPictureSize(bestWidth,bestWidth);
				 * camParams.setPreviewSize(bestWidth,bestWidth);
				 * Log.e("CAMERA","2.2");
				 * 
				 * } }
				 */

				camParams.setPictureSize(WIDTH, HEIGHT);
				camParams.setPreviewSize(WIDTH, HEIGHT);
				camera.setParameters(camParams);

			} else {
				camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);// ��ǰ������ͷ
				Log.e("CAMERA", "1");
				Camera.Parameters camParams = camera.getParameters();
				camera.lock();
				camera.setDisplayOrientation(90);

				camParams.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

				camParams.setPictureSize(WIDTH, HEIGHT);
				camParams.setPreviewSize(WIDTH, HEIGHT);
				camera.setParameters(camParams);
			}
			Log.e("CAMERA", "2");
			camera.setPreviewDisplay(holder);
			camera.startPreview();

			// ��ʱ��ѡ�3.0֮ǰ��Ҫ
			// holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		} catch (Exception e) {
			Log.e("CAMERA", "3");
			Log.e("Error", e.toString());
			e.printStackTrace();
			return null;
		}
		return camera;
	}

	public void recorderStart(final Context context, final Camera camera) {
		if (recorder != null)
			return;
		if (camera != null) {
			camera.stopPreview();
			camera.unlock();
		}
		recorder = new MediaRecorder();

		recorder.setCamera(camera);

		try {
			// ������ʱ�ļ�
			/*
			 * File directory = new File(path).getParentFile(); if
			 * (!directory.exists()) { directory.mkdirs(); // throw new
			 * IOException("Path to file could not be created"); }
			 */

			// 1.��Ƶ����ƵԴ
			recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);// ¼��ԴΪ��˷�
			recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);// ��ƵԴ

			// 2.�����ʽ
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);// �����ʽΪmp4

			// 3.��Ƶ����Ƶ������
			recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263);// ��Ƶ����
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// ��Ƶ����

			// 4.��Ƶ����Ƶ������
			recorder.setVideoEncodingBitRate(3000000);
			recorder.setAudioEncodingBitRate(12200);

			// 5.��Ƶ������
			recorder.setAudioSamplingRate(8000);
			// 6.��Ƶͨ��
			recorder.setAudioChannels(1);

			// 7.��Ƶ֡����
			recorder.setVideoFrameRate(20);
			// 8.��Ƶ��С
			recorder.setVideoSize(WIDTH, HEIGHT);

			/*
			 * // 9.����ļ���С recorder.setMaxFileSize(2000000);
			 * 
			 * // 10.�����ʱ�� recorder.setMaxDuration(10000);// �������
			 */
			// 11.����ļ�
			// recorder.setOutputFile(path);

			recorder.setOrientationHint(90);

			recorder.setPreviewDisplay(holder.getSurface());
			recorder.prepare();

			recorder.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopRecording(Camera camera) {

		if (recorder != null) {
			try {
				recorder.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			recorder.release();
			recorder = null;
		}
		if (camera != null) {
			/*
			 * try { camera.reconnect(); } catch (IOException e) {
			 * e.printStackTrace(); }
			 */
			camera.release();
			camera = null;
		}
	}

	public void playVideo(Context context, VideoView videoView) {
		// MediaController mc = new MediaController(context);
		// videoView.setMediaController(mc);
		// videoView.setVideoPath(path);
		videoView.start();
	}

	public void stopVideo(VideoView videoView) {
		videoView.stopPlayback();
	}

	/**
	 * ��ȡ��Ƶ����ͼ
	 * 
	 * @param videoPath
	 * @param width
	 * @param height
	 * @param kind
	 * @return
	 */
	public Bitmap getVideoThumbnail(String videoPath) {
		int w = 800;
		int h = 800;
		Bitmap bitmap = null;
		bitmap = ThumbnailUtils.createVideoThumbnail(videoPath,
				Thumbnails.FULL_SCREEN_KIND);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, w, h,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	/**
	 * 
	 */
	public void BitmapToFile(Bitmap bitmap, String filePath) {
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 30;
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (bitmap.compress(format, quality, stream)) {
			// success
		}

	}

	private boolean recording = false;

	public boolean resumeRecording(String path,Camera camera) {

		try {
			Log.e("Recorder", "Starting");
			recording = true;
			if (camera != null) {
				camera.stopPreview();
				camera.unlock();
			}
			recorder = new MediaRecorder();

			recorder.setCamera(camera);
			recorder = new MediaRecorder();
			
			 recorder.setAudioSource(1);
			 recorder.setVideoSource(0);
			 recorder.setOutputFormat(2);
			 recorder.setAudioEncoder(3);
			 recorder.setVideoEncoder(1);
			 

			recorder.setOutputFile(path);

			recorder.prepare();

			recorder.start();
		} catch (IllegalStateException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void pauseRecording() {
		if (recorder != null && recording) {
			Log.e("Recorder", "Endding");
			recorder.stop();
			recorder.reset();
			recorder.release();
			recorder = null;
			recording = false;
		}
	}

}
