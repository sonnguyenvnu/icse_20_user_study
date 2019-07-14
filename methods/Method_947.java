@Override public synchronized void onFrameRendered(int frameNumber,CloseableReference<Bitmap> bitmapReference,@BitmapAnimationBackend.FrameType int frameType){
  Preconditions.checkNotNull(bitmapReference);
  removePreparedReference(frameNumber);
  CloseableReference<CloseableImage> closableReference=null;
  try {
    closableReference=createImageReference(bitmapReference);
    if (closableReference != null) {
      CloseableReference.closeSafely(mLastRenderedItem);
      mLastRenderedItem=mAnimatedFrameCache.cache(frameNumber,closableReference);
    }
  }
  finally {
    CloseableReference.closeSafely(closableReference);
  }
}
