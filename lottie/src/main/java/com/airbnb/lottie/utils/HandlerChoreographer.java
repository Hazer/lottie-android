package com.airbnb.lottie.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.view.Choreographer;

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

  public void postFrameCallback(HandlerChoreographer.FrameCallbackHandler callbackWrapper) {
      mHandler.postDelayed(callbackWrapper.getRunnable(), 0);
  }

  public void postFrameCallbackDelayed(HandlerChoreographer.FrameCallbackHandler callbackWrapper, long delayMillis) {
      mHandler.postDelayed(callbackWrapper.getRunnable(), delayMillis + ONE_FRAME_MILLIS);
  }

  public void removeFrameCallback(HandlerChoreographer.FrameCallbackHandler callbackWrapper) {
      mHandler.removeCallbacks(callbackWrapper.getRunnable());
  }

  /**
   * This class provides a compatibility wrapper around the JellyBean FrameCallback with methods
   * to access cached wrappers for submitting a real FrameCallback to a Choreographer or a Runnable
   * to a Handler.
   */
  public interface FrameCallback {
    void doFrame(long frameTimeNanos);
  }

  /**
   * This class provides a compatibility wrapper around the JellyBean FrameCallback with methods
   * to access cached wrappers for submitting a real FrameCallback to a Choreographer or a Runnable
   * to a Handler.
   */
  public static class FrameCallbackHandler implements FrameCallback {

    private final FrameCallback frameCallback;

    public FrameCallbackHandler(FrameCallback frameCallback) {
      this.frameCallback = frameCallback;
    }

    private Runnable mRunnable;

    Runnable getRunnable() {
      if (mRunnable == null) {
        mRunnable = new Runnable() {
          @Override
          public void run() {
            doFrame(System.nanoTime());
          }
        };
      }
      return mRunnable;
    }

    @Override public void doFrame(long frameTimeNanos) {
      frameCallback.doFrame(frameTimeNanos);
    }
  }

  /**
   * This class provides a compatibility wrapper around the JellyBean FrameCallback with methods
   * to access cached wrappers for submitting a real FrameCallback to a Choreographer or a Runnable
   * to a Handler.
   */
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  public static class FrameCallbackWrapper implements Choreographer.FrameCallback {
    private final FrameCallback frameCallback;

    public FrameCallbackWrapper(FrameCallback frameCallback) {
      this.frameCallback = frameCallback;
    }

    public void doFrame(long frameTimeNanos) {
      frameCallback.doFrame(frameTimeNanos);
    }
  }
}
