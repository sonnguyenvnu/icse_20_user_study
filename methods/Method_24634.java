/** 
 * Resume all other threads except the one given as parameter. Useful e.g. to just keep the thread suspended a breakpoint occurred in.
 * @param t the thread not to resume
 */
protected void resumeOtherThreads(ThreadReference t){
  if (!isStarted()) {
    return;
  }
  for (  ThreadReference other : vm().allThreads()) {
    if (!other.equals(t) && other.isSuspended()) {
      other.resume();
    }
  }
}
