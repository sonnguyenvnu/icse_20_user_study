/** 
 * Called after all the change set operations (inserts, removes, etc.) in a batch have completed. Async variant, may be called off the main thread.
 */
public void notifyChangeSetCompleteAsync(boolean isDataChanged,ChangeSetCompleteCallback changeSetCompleteCallback){
  ComponentsSystrace.beginSection("notifyChangeSetCompleteAsync");
  try {
    if (SectionsDebug.ENABLED) {
      Log.d(SectionsDebug.TAG,"(" + hashCode() + ") notifyChangeSetCompleteAsync");
    }
    mHasAsyncOperations=true;
    assertSingleThreadForChangeSet();
    closeCurrentBatch(isDataChanged,changeSetCompleteCallback);
    if (ThreadUtils.isMainThread()) {
      applyReadyBatches();
      if (isDataChanged) {
        maybeUpdateRangeOrRemeasureForMutation();
      }
    }
 else {
      if (mIsMeasured.get()) {
        ChoreographerCompatImpl.getInstance().postFrameCallback(mApplyReadyBatchesCallback);
      }
    }
    clearThreadForChangeSet();
  }
  finally {
    ComponentsSystrace.endSection();
  }
}
