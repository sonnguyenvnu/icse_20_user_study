@Override public synchronized boolean contains(int frameNumber){
  return frameNumber == mLastFrameNumber && CloseableReference.isValid(mLastBitmapReference);
}
