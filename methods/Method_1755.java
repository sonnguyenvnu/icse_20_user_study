/** 
 * Blocks current thread until having finished initialization in Memory Index. Call only when you need memory index in cold start.
 */
@VisibleForTesting protected void awaitIndex(){
  try {
    mCountDownLatch.await();
  }
 catch (  InterruptedException e) {
    FLog.e(TAG,"Memory Index is not ready yet. ");
  }
}
