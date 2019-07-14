/** 
 * Gets the queued time in milliseconds for the currently running job. <p> The result is only valid if called from  {@link JobRunnable#run}.
 */
public synchronized long getQueuedTime(){
  return mJobStartTime - mJobSubmitTime;
}
