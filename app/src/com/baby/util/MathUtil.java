package com.baby.util;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

public class MathUtil {
	public static int clamp(int paramInt1, int paramInt2, int paramInt3) {
		if (paramInt1 > paramInt3)
			return paramInt3;
		if (paramInt1 < paramInt2)
			return paramInt2;
		return paramInt1;
	}


	public static void rectFToRect(RectF paramRectF, Rect paramRect) {
		paramRect.left = Math.round(paramRectF.left);
		paramRect.top = Math.round(paramRectF.top);
		paramRect.right = Math.round(paramRectF.right);
		paramRect.bottom = Math.round(paramRectF.bottom);
	}
}
