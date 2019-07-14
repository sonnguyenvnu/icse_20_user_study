/** 
 * Wait until this crawling session finishes.
 */
public void waitUntilFinish(){
  while (!finished) {
synchronized (waitingLock) {
      if (config.isHaltOnError()) {
        Throwable t=getError();
        if (t != null && config.isHaltOnError()) {
          if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
          }
 else           if (t instanceof Error) {
            throw (Error)t;
          }
 else {
            throw new RuntimeException("error on monitor thread",t);
          }
        }
      }
      if (finished) {
        return;
      }
      try {
        waitingLock.wait();
      }
 catch (      InterruptedException e) {
        logger.error("Error occurred",e);
      }
    }
  }
}
