/** 
 * <p> Signals the main processing loop that a change in scheduling has been made - in order to interrupt any sleeping that may be occuring while waiting for the fire time to arrive. </p>
 * @param candidateNewNextFireTime the time (in millis) when the newly scheduled triggerwill fire.  If this method is being called do to some other even (rather than scheduling a trigger), the caller should pass zero (0).
 */
public void signalSchedulingChange(long candidateNewNextFireTime){
synchronized (sigLock) {
    signaled=true;
    signaledNextFireTime=candidateNewNextFireTime;
    sigLock.notifyAll();
  }
}
