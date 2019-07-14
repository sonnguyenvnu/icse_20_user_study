/** 
 * Blocks until the condition is opened or until  {@code timeout} milliseconds have passed.
 * @param timeout The maximum time to wait in milliseconds.
 * @return True if the condition was opened, false if the call returns because of the timeout.
 * @throws InterruptedException If the thread is interrupted.
 */
public synchronized boolean block(long timeout) throws InterruptedException {
  long now=android.os.SystemClock.elapsedRealtime();
  long end=now + timeout;
  while (!isOpen && now < end) {
    wait(end - now);
    now=android.os.SystemClock.elapsedRealtime();
  }
  return isOpen;
}
