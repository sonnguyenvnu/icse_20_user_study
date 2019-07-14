@Override public synchronized boolean hasResult(){
  return !isClosed() && (mFinishedDataSources == mDataSources.length);
}
