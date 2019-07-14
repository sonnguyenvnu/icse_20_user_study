private void notifyDataSubscribers(){
  final boolean isFailure=hasFailed();
  final boolean isCancellation=wasCancelled();
  for (  Pair<DataSubscriber<T>,Executor> pair : mSubscribers) {
    notifyDataSubscriber(pair.first,pair.second,isFailure,isCancellation);
  }
}
