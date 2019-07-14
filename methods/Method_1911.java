@Override public void onFrameDropped(BitmapAnimationBackend backend,int frameNumber){
  mDroppedFrameCount++;
  FLog.d(TAG,"Frame: event=dropped, number=%d, render_time=%d ms",frameNumber,System.currentTimeMillis() - mLastFrameStart);
  logStatistics();
}
