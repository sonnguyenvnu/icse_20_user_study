@Override public boolean prepareFrame(BitmapFrameCache bitmapFrameCache,AnimationBackend animationBackend,int frameNumber){
  int frameId=getUniqueId(animationBackend,frameNumber);
synchronized (mPendingFrameDecodeJobs) {
    if (mPendingFrameDecodeJobs.get(frameId) != null) {
      FLog.v(TAG,"Already scheduled decode job for frame %d",frameNumber);
      return true;
    }
    if (bitmapFrameCache.contains(frameNumber)) {
      FLog.v(TAG,"Frame %d is cached already.",frameNumber);
      return true;
    }
    Runnable frameDecodeRunnable=new FrameDecodeRunnable(animationBackend,bitmapFrameCache,frameNumber,frameId);
    mPendingFrameDecodeJobs.put(frameId,frameDecodeRunnable);
    mExecutorService.execute(frameDecodeRunnable);
  }
  return true;
}
