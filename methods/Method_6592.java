@Override protected void onHandleWork(Intent intent){
synchronized (sync) {
    if (!startingJob) {
      return;
    }
    countDownLatch=new CountDownLatch(1);
  }
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("started keep-alive job");
  }
  Utilities.globalQueue.postRunnable(finishJobByTimeoutRunnable,60 * 1000);
  try {
    countDownLatch.await();
  }
 catch (  Throwable ignore) {
  }
  Utilities.globalQueue.cancelRunnable(finishJobByTimeoutRunnable);
synchronized (sync) {
    countDownLatch=null;
  }
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("ended keep-alive job");
  }
}
