/** 
 * ???
 * @return true????? ? false ?????? ?????timeoutMsecs???????????? timeoutMsecs ???
 */
public boolean acquire(){
  long timeout=timeoutMsecs;
  do {
    long expires=System.currentTimeMillis() + expireMsecs + 1;
    Long result=redis.setnx(lockName,expires);
    if (result != null && result == 1) {
      locked=true;
      return true;
    }
    Long currentValue=redis.get(lockName);
    if (currentValue != null && currentValue < System.currentTimeMillis()) {
      Long oldValue=redis.getSet(lockName,expires);
      if (oldValue != null && oldValue.equals(currentValue)) {
        locked=true;
        return true;
      }
    }
    if (timeout > 0) {
      timeout-=100;
      try {
        Thread.sleep(100);
      }
 catch (      InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
 while (timeout > 0);
  return false;
}
