@Override public long getWaitingInterval(){
  if (LOCAL_LOGV) {
    Timber.v("Next int: " + sDefaultRetryScheme[mRetriedTimes]);
  }
  return sDefaultRetryScheme[mRetriedTimes];
}
