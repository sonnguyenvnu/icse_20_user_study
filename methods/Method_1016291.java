/** 
 * This method will be called in  {@link #run()} to inform control that this task has finished running with a failure result 
 */
public final void fail(){
  Status previousStatus=status;
  status=Status.FAILED;
  if (tree.listeners != null && tree.listeners.size > 0)   tree.notifyStatusUpdated(this,previousStatus);
  end();
  if (control != null)   control.childFail(this);
}
