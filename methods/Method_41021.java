/** 
 * <p> Reports whether the <code>Scheduler</code> is paused. </p>
 */
public boolean isInStandbyMode(){
  return schedThread.isPaused();
}
