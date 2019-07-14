@Override public void onFrameDrawn(BitmapAnimationBackend backend,int frameNumber,@BitmapAnimationBackend.FrameType int frameType){
  increaseFrameTypeCount(frameType);
  FLog.d(TAG,"Frame: event=drawn, number=%d, type=%s, render_time=%d ms",frameNumber,getFrameTypeName(frameType),System.currentTimeMillis() - mLastFrameStart);
  logStatistics();
}
