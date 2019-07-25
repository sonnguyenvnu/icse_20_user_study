/** 
 * This method will be called in  {@link #run()} to inform control that this task has finished running with a success result 
 */
public final void success(){
  Status previousStatus=status;
  status=Status.SUCCEEDED;
  if (tree.listeners != null && tree.listeners.size > 0)   tree.notifyStatusUpdated(this,previousStatus);
  end();
  if (control != null)   control.childSuccess(this);
}
