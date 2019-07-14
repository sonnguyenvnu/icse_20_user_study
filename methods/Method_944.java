@Nullable @Override public synchronized CloseableReference<Bitmap> getFallbackFrame(int frameNumber){
  return convertToBitmapReferenceAndClose(CloseableReference.cloneOrNull(mLastRenderedItem));
}
