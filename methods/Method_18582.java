/** 
 * Try to raise the priority of  {@param threadId} to the priority of the calling thread
 * @return the original thread priority of the target thread.
 */
public static int tryInheritThreadPriorityFromCurrentThread(int threadId){
  return tryRaiseThreadPriority(threadId,Process.getThreadPriority(Process.myTid()));
}
