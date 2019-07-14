/** 
 * Print info about all current threads. Includes name, status, isSuspended, isAtBreakpoint.
 */
public synchronized void printThreads(){
  if (!isPaused()) {
    return;
  }
  System.out.println("threads:");
  for (  ThreadReference t : vm().allThreads()) {
    printThread(t);
  }
}
