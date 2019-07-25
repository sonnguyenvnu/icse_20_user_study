/** 
 * Release the waiter.
 * @param resultStatus result status indicator
 */
private void release(ResultStatus resultStatus){
  stopListening(component);
  this.resultStatus.set(resultStatus);
  completionLatch.countDown();
}
