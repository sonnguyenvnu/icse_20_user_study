/** 
 * Cancels the current load. This method should only be called when a load is in progress.
 */
public void cancelLoading(){
  currentTask.cancel(false);
}
