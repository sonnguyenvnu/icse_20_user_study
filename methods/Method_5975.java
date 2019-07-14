/** 
 * Stops the clock. Does nothing if the clock is already stopped.
 */
public void stop(){
  if (started) {
    resetPosition(getPositionUs());
    started=false;
  }
}
