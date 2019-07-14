@Nullable @Override public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber){
  return convertToBitmapReferenceAndClose(mAnimatedFrameCache.get(frameNumber));
}
