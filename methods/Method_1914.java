@Nullable @Override public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber){
  return CloseableReference.cloneOrNull(mBitmapSparseArray.get(frameNumber));
}
