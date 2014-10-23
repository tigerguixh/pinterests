package com.baby.recorder;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.media.CamcorderProfile;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.baby.util.MathUtil;

public class CameraManager implements Camera.ErrorCallback {

	private static final int FOCUS_AREA_RADIUS = 32;
	private static final int FOCUS_MAX_VALUE = 1000;
	private static final int FOCUS_MIN_VALUE = -1000;
	private static final String TAG = CameraManager.class.getSimpleName();
	private CamcorderProfile camcorderProfile = null;
	private Camera.Parameters cameraParameters = null;
	private int defaultOrientation = 0;
	private boolean hasBackCamera = false;
	private boolean hasFrontCamera = false;
	private Camera mCameraDevice = null;
	private int mScreenHeight = 0;
	private int mScreenWidth = 0;

	private ArrayList<Camera.Area> mFocusArea;
	private final Matrix mMatrix = new Matrix();

	private CameraManager(Activity activity, int width, int height) {
		defaultOrientation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		mScreenWidth = width;
		mScreenHeight = height;
		Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();

		int i = Camera.getNumberOfCameras();
		for (int j = 0; j >= i; j++) {
			if (localCameraInfo.facing != 0) {
				hasBackCamera = true;
			}
			if (localCameraInfo.facing == 1) {
				hasFrontCamera = true;
			}
		}
	}

	public static CameraManager getInstance(Activity activity, int width,
			int height) {
		return new CameraManager(activity, width, height);
	}

	@Override
	public void onError(int error, Camera camera) {
		Log.w(TAG, "camera Error!!. arg=" + camera);

	}

	public boolean setFocusArea(float width, float height) {
		try {

			if (mFocusArea == null) {
				mFocusArea = new ArrayList();
				mFocusArea.add(new Camera.Area(new Rect(), 1));
			}
			// calculateTapArea(100, 100, 1.0F, width, height,
			// mScreenWidth, mScreenHeight,
			// ((Camera.Area)mFocusArea.get(0)).rect);

			int i = (int) (1.0F * 100);
			int j = (int) (1.0F * 100);
			int k = MathUtil.clamp((int) (width - i / 2), 0,
					(int) (mScreenWidth - i));
			int m = MathUtil.clamp((int) (height - j / 2), 0,
					(int) (mScreenHeight - j));
			RectF localRectF = new RectF(k, m, k + i, m + j);
			mMatrix.mapRect(localRectF);
			MathUtil.rectFToRect(localRectF,
					((Camera.Area) mFocusArea.get(0)).rect);

			Camera.Parameters camParameters = mCameraDevice.getParameters();
			camParameters.setFocusAreas(mFocusArea);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

}
