package com.baby.view.grid;

import com.baby.R;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterEmptyView extends FrameLayout {

	private static boolean CUSTOM_PADDING = false;
	private static final int STATE_COUNT = 3;
	public static final int STATE_EMPTY = 1;
	public static final int STATE_LOADING = 0;
	public static final int STATE_NONE = 2;
	private Button _actionBt;
	private SparseArray _actionStates;
	private View _container;
	private boolean _customMode;
	private ImageView _iconIv;
	private TextView _messageTv;
	private View _noInternet;
	private Button _refreshBt;
	private ImageView _spinner;
	private ObjectAnimator _spinnerAnim;
	private int _state = 2;
	private TextView _titleTv;
	private ViewGroup _wrapper;

	public AdapterEmptyView(Context context) {
		super(context);
		init();
	}

	public AdapterEmptyView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	public AdapterEmptyView(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		init();
	}

	private void destroyAnimation() {
		if (_spinnerAnim != null) {
			_spinnerAnim.cancel();
			_spinnerAnim.removeAllListeners();
			_spinnerAnim.removeAllUpdateListeners();
		}
		_spinnerAnim = null;
	}

	private void init() {
		View localView = LayoutInflater.from(getContext()).inflate(
				R.layout.list_empty, null);
		_spinner = ((ImageView) localView.findViewById(R.id.loading_pb));
		_iconIv = ((ImageView) localView.findViewById(R.id.icon_iv));
		_titleTv = ((TextView) localView.findViewById(R.id.title_tv));
		_messageTv = ((TextView) localView.findViewById(R.id.message_tv));
		_actionBt = ((Button) localView.findViewById(R.id.action_bt));
		_refreshBt = ((Button) localView.findViewById(R.id.refresh_bt));
		_wrapper = ((ViewGroup) localView.findViewById(R.id.empty_wrapper));
		_container = localView.findViewById(R.id.container);
		_noInternet = localView.findViewById(R.id.no_internet);
		_actionStates = new SparseArray(3);
		updateView();
		addView(localView);
	}

	private void makeAnimation() {
		if ((this._spinner == null) || (this._spinnerAnim != null))
			return;
		this._spinnerAnim = ObjectAnimator.ofFloat(this._spinner, "rotation",
				new float[] { 2.0F, 8.0F, 12.0F, 21.0F, 38.0F, 63.0F, 100.0F,
						149.0F, 205.0F, 255.0F, 292.0F, 318.0F, 336.0F, 348.0F,
						356.0F, 360.0F });
		this._spinnerAnim.setStartDelay(500L);
		this._spinnerAnim.setDuration(1000L);
		this._spinnerAnim.setRepeatCount(-1);
		this._spinnerAnim.setRepeatMode(1);
		this._spinnerAnim.addListener(new MyAnimationListener());
		updateView();
	}

	class MyAnimationListener implements Animator.AnimatorListener {
		public void onAnimationCancel(Animator paramAnimator) {
			_spinnerAnim.setCurrentPlayTime(0);
		}

		public void onAnimationEnd(Animator paramAnimator) {
		}

		public void onAnimationRepeat(Animator paramAnimator) {
			if (_spinner.getVisibility() != 0)
				_spinnerAnim.cancel();
		}

		public void onAnimationStart(Animator paramAnimator) {
		}
	}
	
	private void updateView()
	  {
	    switch (this._state)
	    {
	    default:
	    case 1:
	    case 0:
	    case 2:
	    }
	    while (true)
	    {
	     
	      if (this._spinnerAnim != null)
	        this._spinnerAnim.cancel();
	      this._spinner.setVisibility(View.GONE);
	      //hide animated view
	      _spinner.setPadding(_spinner.getWidth(), 0, 0, 0);
	      
	      this._container.setVisibility(0);
	      /*if (Device.hasInternet())
	      {
	        if (this._customMode)
	          clearDefaults();
	        this._wrapper.setVisibility(0);
	        this._noInternet.setVisibility(8);
	        continue;
	      }*/
	      if (this._customMode)
	        resetDefaults();
	      this._wrapper.setVisibility(8);
	      this._noInternet.setVisibility(0);
	      continue;
	    }
	  }
	
	 protected void clearDefaults()
	  {
	    setContainerBgColor(17170445);
	    if (CUSTOM_PADDING)
	    {
	      View localView = findViewById(R.id.empty_view_wrapper);
	      if (localView != null)
	      {
	        localView.setPadding(0, (int) 28.0F, 0, 0);
	        localView.forceLayout();
	      }
	    }
	  }
	 
	 protected void resetDefaults()
	  {
	    this._container.setBackgroundResource(2130837947);
	    if (CUSTOM_PADDING)
	    {
	      View localView = findViewById(2131231104);
	      if (localView != null)
	      {
	    	 int i = 0;
	        localView.setPadding(i, i, i, i);
	        localView.forceLayout();
	      }
	    }
	  }
	 
	 public void setContainerBgColor(int paramInt)
	  {
	    if (this._container != null){
	    	
	    }
	      //this._container.setBackgroundColor(Application.getResources().getColor(paramInt));
	  }

}
