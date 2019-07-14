@Override public void subscribe(final DataSubscriber<T> dataSubscriber,final Executor executor){
  Preconditions.checkNotNull(dataSubscriber);
  Preconditions.checkNotNull(executor);
  boolean shouldNotify;
synchronized (this) {
    if (mIsClosed) {
      return;
    }
    if (mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
      mSubscribers.add(Pair.create(dataSubscriber,executor));
    }
    shouldNotify=hasResult() || isFinished() || wasCancelled();
  }
  if (shouldNotify) {
    notifyDataSubscriber(dataSubscriber,executor,hasFailed(),wasCancelled());
  }
}
