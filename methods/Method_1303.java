/** 
 * Determine whether or not the queue is idle.
 * @return true if there is no work being executed and the work queue is empty, false otherwise.
 */
public boolean isIdle(){
  return mWorkQueue.isEmpty() && (mPendingWorkers.get() == 0);
}
