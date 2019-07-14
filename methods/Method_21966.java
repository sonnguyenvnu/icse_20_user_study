/** 
 * ??????.
 */
public void notifyWaitingTaskComplete(){
synchronized (completedWait) {
    completedWait.notifyAll();
  }
}
