private synchronized boolean wasCancelled(){
  return isClosed() && !isFinished();
}
