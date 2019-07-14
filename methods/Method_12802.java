/** 
 * execute a runnable on ui thread
 * @param runnable the runnable prepared to run
 * @param waitUtilDone if set true, the caller thread will wait until the specific runnable finished.
 */
public static void runOnUiThread(Runnable runnable,boolean waitUtilDone){
  if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
    runnable.run();
    return;
  }
  CountDownLatch countDownLatch=null;
  if (waitUtilDone) {
    countDownLatch=new CountDownLatch(1);
  }
  Pair<Runnable,CountDownLatch> pair=new Pair<>(runnable,countDownLatch);
  getHandler().obtainMessage(MESSAGE_RUN_ON_UITHREAD,pair).sendToTarget();
  if (waitUtilDone) {
    try {
      countDownLatch.await();
    }
 catch (    InterruptedException e) {
      Log.w(Constants.TAG,e);
    }
  }
}
