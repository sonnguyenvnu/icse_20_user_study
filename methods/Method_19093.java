/** 
 * The state update is completed if there are no new non-lazy state updates enqueued.
 * @return true if there are no more state updates to be processed immediately.
 */
private synchronized boolean isStateUpdateCompleted(StateUpdatesHolder localPendingStateUpdates){
  return localPendingStateUpdates.mNonLazyStateUpdates.equals(mPendingStateUpdates.mNonLazyStateUpdates);
}
