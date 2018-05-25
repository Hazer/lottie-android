package com.airbnb.lottie.utils;


import android.os.Handler;
import android.os.Looper;

public class HandlerChoreographer {

  private static final long ONE_FRAME_MILLIS = 17;
  private static final HandlerChoreographer INSTANCE = new HandlerChoreographer();

  private Handler mHandler;

  public static HandlerChoreographer getInstance() {
    return INSTANCE;
  }

  private HandlerChoreographer() {
      mHandler = new Handler(Looper.getMainLooper());
  }

  public void postFrameCallback(FrameCallbackCompat callbackWrapper) {
      mHandler.postDelayed(callbackWrapper, 0);
  }

  public void postFrameCallbackDelayed(FrameCallbackCompat callbackWrapper, long delayMillis) {
      mHandler.postDelayed(callbackWrapper, delayMillis + ONE_FRAME_MILLIS);
  }

  public void removeFrameCallback(FrameCallbackCompat callbackWrapper) {
      mHandler.removeCallbacks(callbackWrapper);
  }

  public interface FrameCallback {
    void doFrame(long frameTimeNanos);
  }

  public static class FrameCallbackCompat implements Runnable {
    private final FrameCallback frameCallback;

    public FrameCallbackCompat(FrameCallback frameCallback) {
      this.frameCallback = frameCallback;
    }

    @Override public void run() {
      frameCallback.doFrame(System.nanoTime());
    }
  }
}