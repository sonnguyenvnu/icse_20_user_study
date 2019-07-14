@Override public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber,int width,int height){
  try {
    return CloseableReference.cloneOrNull(mLastBitmapReference);
  }
  finally {
    closeAndResetLastBitmapReference();
  }
}
