@Override public synchronized boolean hasFailed(){
  return mDataSourceStatus == DataSourceStatus.FAILURE;
}
