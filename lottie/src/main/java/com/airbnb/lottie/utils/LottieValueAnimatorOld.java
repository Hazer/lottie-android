package com.airbnb.lottie.utils;

public class LottieValueAnimatorOld extends BaseLottieValueAnimator implements HandlerChoreographer.FrameCallback {
  private final HandlerChoreographer.FrameCallbackCompat wrapper = new HandlerChoreographer.FrameCallbackCompat(this);

  @Override protected void postFrameCallback() {
    HandlerChoreographer.getInstance().postFrameCallback(wrapper);
  }

  @Override protected void removeFrameCallback() {
    HandlerChoreographer.getInstance().removeFrameCallback(wrapper);
  }
}
