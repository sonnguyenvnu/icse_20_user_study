/** 
 * find out the number of thread deadlocks. WARNING: this is a time-consuming task
 * @return the number of deadlocked threads
 */
public static long deadlocks(){
  long[] deadlockIDs=ManagementFactory.getThreadMXBean().findDeadlockedThreads();
  if (deadlockIDs == null)   return 0;
  return deadlockIDs.length;
}
