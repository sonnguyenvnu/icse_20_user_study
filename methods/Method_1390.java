private synchronized boolean setFailureInternal(Throwable throwable){
  if (mIsClosed || mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
    return false;
  }
 else {
    mDataSourceStatus=DataSourceStatus.FAILURE;
    mFailureThrowable=throwable;
    return true;
  }
}
