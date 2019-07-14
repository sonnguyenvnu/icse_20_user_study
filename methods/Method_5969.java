/** 
 * Blocks until the task is allowed to proceed.
 * @param priority The priority of the task.
 * @throws InterruptedException If the thread is interrupted.
 */
public void proceed(int priority) throws InterruptedException {
synchronized (lock) {
    while (highestPriority != priority) {
      lock.wait();
    }
  }
}
