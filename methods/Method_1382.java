@Override public synchronized boolean isFinished(){
  return mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
}
