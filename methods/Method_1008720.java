/** 
 * This method is called by the task manager when this task is cancelled.
 */
final void cancel(String reason){
  assert reason != null;
  this.reason.compareAndSet(null,reason);
  onCancelled();
}
