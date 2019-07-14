@Nullable @Override public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber){
  if (mLastFrameNumber == frameNumber) {
    return CloseableReference.cloneOrNull(mLastBitmapReference);
  }
  return null;
}
