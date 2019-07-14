@Override public synchronized void onCancellation(){
  if (mIsFinished) {
    return;
  }
  mIsFinished=true;
  try {
    onCancellationImpl();
  }
 catch (  Exception e) {
    onUnhandledException(e);
  }
}
