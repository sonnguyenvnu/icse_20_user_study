/** 
 * <p> Signals the main processing loop to pause at the next possible point. </p>
 */
void togglePause(boolean pause){
synchronized (sigLock) {
    paused=pause;
    if (paused) {
      signalSchedulingChange(0);
    }
 else {
      sigLock.notifyAll();
    }
  }
}
