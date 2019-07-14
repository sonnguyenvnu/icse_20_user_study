@Nullable @Override public synchronized CloseableReference<Bitmap> getFallbackFrame(int frameNumber){
  return CloseableReference.cloneOrNull(mLastBitmapReference);
}
