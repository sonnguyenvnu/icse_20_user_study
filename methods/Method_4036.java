/** 
 * Batches adapter updates that happen after calling this method and before calling {@link #endBatchedUpdates()}. For example, if you add multiple items in a loop and they are placed into consecutive indices, SortedList calls {@link Callback#onInserted(int,int)} only once with the proper item count. If an eventcannot be merged with the previous event, the previous event is dispatched to the callback instantly. <p> After running your data updates, you <b>must</b> call  {@link #endBatchedUpdates()}which will dispatch any deferred data change event to the current callback. <p> A sample implementation may look like this: <pre> mSortedList.beginBatchedUpdates(); try { mSortedList.add(item1) mSortedList.add(item2) mSortedList.remove(item3) ... } finally { mSortedList.endBatchedUpdates(); } </pre> <p> Instead of using this method to batch calls, you can use a Callback that extends {@link BatchedCallback}. In that case, you must make sure that you are manually calling {@link BatchedCallback#dispatchLastEvent()} right after you complete your data changes.Failing to do so may create data inconsistencies with the Callback. <p> If the current Callback is an instance of  {@link BatchedCallback}, calling this method has no effect.
 */
public void beginBatchedUpdates(){
  throwIfInMutationOperation();
  if (mCallback instanceof BatchedCallback) {
    return;
  }
  if (mBatchedCallback == null) {
    mBatchedCallback=new BatchedCallback(mCallback);
  }
  mCallback=mBatchedCallback;
}
