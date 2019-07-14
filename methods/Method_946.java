@Override public synchronized int getSizeInBytes(){
  return getBitmapSizeBytes(mLastRenderedItem) + getPreparedPendingFramesSizeBytes();
}
