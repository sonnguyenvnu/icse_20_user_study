@Override public synchronized int getMemoryUsage(){
  int bytes=0;
  if (mTempBitmap != null) {
    bytes+=mAnimatedDrawableUtil.getSizeOfBitmap(mTempBitmap);
  }
  bytes+=mAnimatedImage.getSizeInBytes();
  return bytes;
}
