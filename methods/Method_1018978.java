/** 
 * Release this component. <p> An attempt is made to perform an orderly shutdown, so any tasks in the queue are given a chance to complete cleanly within the specified timeout period. <p> Consequently there may be a short delay before this method returns. <p> If there are no tasks waiting, this method will return immediately.
 * @param timeout timeout, milliseconds
 */
public void release(long timeout){
  shutdownExecutor(timeout);
}
