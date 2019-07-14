/** 
 * wait until the last activity is stopped
 * @param timeOutInMillis timeout for wait
 */
public void waitForAllActivitiesDestroy(int timeOutInMillis){
synchronized (activityStack) {
    long start=System.currentTimeMillis();
    long now=start;
    while (!activityStack.isEmpty() && start + timeOutInMillis > now) {
      try {
        activityStack.wait(start - now + timeOutInMillis);
      }
 catch (      InterruptedException ignored) {
      }
      now=System.currentTimeMillis();
    }
  }
}
