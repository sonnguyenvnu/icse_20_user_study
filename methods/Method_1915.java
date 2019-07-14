@Override public synchronized boolean contains(int frameNumber){
  return CloseableReference.isValid(mBitmapSparseArray.get(frameNumber));
}
