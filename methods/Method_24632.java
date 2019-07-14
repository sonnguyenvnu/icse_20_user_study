/** 
 * Check whether the debugger is currently busy. i.e. running (not suspended).
 * @return true if the debugger is currently running and not suspended.
 */
public synchronized boolean isBusy(){
  return isStarted() && !isPaused();
}
