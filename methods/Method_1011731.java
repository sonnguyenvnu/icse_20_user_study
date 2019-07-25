/** 
 * Cancels queue polling and discards tasks left, if any
 */
public void stop(){
  if (myTimerTask != null) {
    myTimerTask.cancel(false);
    myTimerTask=null;
    myTasks.clear();
  }
}
