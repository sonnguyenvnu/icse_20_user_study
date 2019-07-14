@Override public synchronized void onFramePrepared(int frameNumber,CloseableReference<Bitmap> bitmapReference,@BitmapAnimationBackend.FrameType int frameType){
  Preconditions.checkNotNull(bitmapReference);
  CloseableReference<CloseableImage> closableReference=null;
  try {
    closableReference=createImageReference(bitmapReference);
    if (closableReference == null) {
      return;
    }
    CloseableReference<CloseableImage> newReference=mAnimatedFrameCache.cache(frameNumber,closableReference);
    if (CloseableReference.isValid(newReference)) {
      CloseableReference<CloseableImage> oldReference=mPreparedPendingFrames.get(frameNumber);
      CloseableReference.closeSafely(oldReference);
      mPreparedPendingFrames.put(frameNumber,newReference);
      FLog.v(TAG,"cachePreparedFrame(%d) cached. Pending frames: %s",frameNumber,mPreparedPendingFrames);
    }
  }
  finally {
    CloseableReference.closeSafely(closableReference);
  }
}
