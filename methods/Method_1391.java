private synchronized boolean setProgressInternal(float progress){
  if (mIsClosed || mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
    return false;
  }
 else   if (progress < mProgress) {
    return false;
  }
 else {
    mProgress=progress;
    return true;
  }
}
