/** 
 * Subclasses should invoke this method to set the failure. <p> This method will return  {@code true} if the failure was successfully set, or{@code false} if the data source has already been set, failed or closed.<p> If the failure was successfully set, state of the data source will be set to {@link AbstractDataSource.DataSourceStatus#FAILURE}. <p> This will also notify the subscribers if the failure was successfully set. <p> Do NOT call this method from a synchronized block as it invokes external code of the subscribers.
 * @param throwable the failure cause to be set.
 * @return true if the failure was successfully set.
 */
protected boolean setFailure(Throwable throwable){
  boolean result=setFailureInternal(throwable);
  if (result) {
    notifyDataSubscribers();
  }
  return result;
}
