/** 
 * This method will be called in  {@link #run()} to inform control that this task needs to run again 
 */
public final void running(){
  Status previousStatus=status;
  status=Status.RUNNING;
  if (tree.listeners != null && tree.listeners.size > 0)   tree.notifyStatusUpdated(this,previousStatus);
  if (control != null)   control.childRunning(this,this);
}
