@Override public synchronized boolean isClosed(){
  return !CloseableReference.isValid(mBufRef);
}
