/** 
 * ?????????
 * @throws InterruptedException
 */
public void await() throws InterruptedException {
synchronized (notify) {
    while (counter.get() > 0) {
      notify.wait();
    }
    if (notifyListen != null) {
      notifyListen.notifyListen();
    }
  }
}
