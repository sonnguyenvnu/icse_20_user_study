@Override public void prepareFrames(BitmapFramePreparer bitmapFramePreparer,BitmapFrameCache bitmapFrameCache,AnimationBackend animationBackend,int lastDrawnFrameNumber){
  for (int i=1; i <= mFramesToPrepare; i++) {
    int nextFrameNumber=(lastDrawnFrameNumber + i) % animationBackend.getFrameCount();
    if (FLog.isLoggable(FLog.VERBOSE)) {
      FLog.v(TAG,"Preparing frame %d, last drawn: %d",nextFrameNumber,lastDrawnFrameNumber);
    }
    if (!bitmapFramePreparer.prepareFrame(bitmapFrameCache,animationBackend,nextFrameNumber)) {
      return;
    }
  }
}
