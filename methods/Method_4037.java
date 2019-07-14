/** 
 * Ends the update transaction and dispatches any remaining event to the callback.
 */
public void endBatchedUpdates(){
  throwIfInMutationOperation();
  if (mCallback instanceof BatchedCallback) {
    ((BatchedCallback)mCallback).dispatchLastEvent();
  }
  if (mCallback == mBatchedCallback) {
    mCallback=mBatchedCallback.mWrappedCallback;
  }
}
