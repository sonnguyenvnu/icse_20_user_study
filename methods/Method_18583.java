/** 
 * Try to raise the priority of  {@param threadId} to {@param targetThreadPriority}.
 * @return the original thread priority of the target thread.
 */
public static int tryRaiseThreadPriority(int threadId,int targetThreadPriority){
  final int originalThreadPriority=Process.getThreadPriority(threadId);
  boolean success=false;
  while (!success && targetThreadPriority < originalThreadPriority) {
    try {
      Process.setThreadPriority(threadId,targetThreadPriority);
      success=true;
    }
 catch (    SecurityException e) {
      targetThreadPriority+=Process.THREAD_PRIORITY_LESS_FAVORABLE;
    }
  }
  return originalThreadPriority;
}
