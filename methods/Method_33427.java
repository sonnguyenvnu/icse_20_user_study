/** 
 * This method is used to run a specified Runnable in the FX Application thread, it waits for the task to finish before returning to the main thread.
 * @param doRun This is the sepcifed task to be excuted by the FX Application thread
 * @return Nothing
 */
public static void runInFXAndWait(Runnable doRun){
  if (Platform.isFxApplicationThread()) {
    doRun.run();
    return;
  }
  final CountDownLatch doneLatch=new CountDownLatch(1);
  Platform.runLater(() -> {
    try {
      doRun.run();
    }
  finally {
      doneLatch.countDown();
    }
  }
);
  try {
    doneLatch.await();
  }
 catch (  InterruptedException e) {
    Thread.currentThread().interrupt();
  }
}
