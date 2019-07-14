private synchronized void onCancellationImpl(){
  Preconditions.checkState(isClosed());
}
