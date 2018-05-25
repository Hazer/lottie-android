package com.airbnb.lottie.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Choreographer;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class LottieValueAnimator extends BaseLottieValueAnimator implements Choreographer.FrameCallback {

  @Override protected void postFrameCallback() {
    Choreographer.getInstance().postFrameCallback(this);
  }

  @Override protected void removeFrameCallback() {
    Choreographer.getInstance().removeFrameCallback(this);
  }
}