@Override public void run(){
  myDelegateExecuted=false;
  try {
    final boolean lockGranted;
    if (myTimeoutMillis == -1) {
      myLock.lock();
      lockGranted=true;
    }
 else {
      lockGranted=myLock.tryLock(myTimeoutMillis,TimeUnit.MILLISECONDS);
    }
    if (lockGranted) {
      try {
        myDelegate.run();
        myDelegateExecuted=true;
      }
  finally {
        myLock.unlock();
      }
    }
  }
 catch (  InterruptedException ex) {
    Thread.currentThread().interrupt();
    Logger.getLogger(ModelAccess.class).error("Interrupted while trying to lock",ex);
  }
}
