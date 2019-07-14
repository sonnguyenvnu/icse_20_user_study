/** 
 * Called after all the change set operations (inserts, removes, etc.) in a batch have completed.
 */
@UiThread public void notifyChangeSetComplete(boolean isDataChanged,ChangeSetCompleteCallback changeSetCompleteCallback){
  ComponentsSystrace.beginSection("notifyChangeSetComplete");
  try {
    if (SectionsDebug.ENABLED) {
      Log.d(SectionsDebug.TAG,"(" + hashCode() + ") notifyChangeSetComplete");
    }
    ThreadUtils.assertMainThread();
    if (mHasAsyncOperations) {
      throw new RuntimeException("Trying to do a sync notifyChangeSetComplete when using asynchronous mutations!");
    }
    changeSetCompleteCallback.onDataBound();
    mDataRenderedCallbacks.addLast(changeSetCompleteCallback);
    maybeDispatchDataRendered();
    if (isDataChanged) {
      maybeUpdateRangeOrRemeasureForMutation();
    }
  }
  finally {
    ComponentsSystrace.endSection();
  }
}
