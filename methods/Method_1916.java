@Override public synchronized void onFrameRendered(int frameNumber,CloseableReference<Bitmap> bitmapReference,@BitmapAnimationBackend.FrameType int frameType){
  mBitmapSparseArray.put(frameNumber,CloseableReference.cloneOrNull(bitmapReference));
  if (mFrameCacheListener != null) {
    mFrameCacheListener.onFrameCached(this,frameNumber);
  }
}
