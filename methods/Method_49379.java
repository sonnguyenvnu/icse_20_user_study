public static boolean waitForCompletion(Thread[] threads,int maxWaitMillis,int sleepPeriodMillis){
  long endTime=System.currentTimeMillis() + maxWaitMillis;
  while (oneAlive(threads)) {
    long currentTime=System.currentTimeMillis();
    if (currentTime >= endTime)     return false;
    try {
      Thread.sleep(Math.min(sleepPeriodMillis,endTime - currentTime));
    }
 catch (    InterruptedException e) {
      System.err.println("Interrupted while waiting for completion of threads!");
    }
  }
  return true;
}
