/** 
 * Marks this ProducerContext as cancelled. <p> This method does not call any callbacks. Instead, caller of this method is responsible for iterating over returned list and calling appropriate method on each callback object. {@see #callOnCancellationRequested}
 * @return list of callbacks if the value actually changes, null otherwise
 */
@Nullable public synchronized List<ProducerContextCallbacks> cancelNoCallbacks(){
  if (mIsCancelled) {
    return null;
  }
  mIsCancelled=true;
  return new ArrayList<>(mCallbacks);
}
