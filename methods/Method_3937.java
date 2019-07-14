/** 
 * Helper method reflect data changes to the state. <p> Adapter changes during a scroll may trigger a crash because scroll assumes no data change but data actually changed. <p> This method consumes all deferred changes to avoid that case.
 */
void consumePendingUpdateOperations(){
  if (!mFirstLayoutComplete || mDataSetHasChangedAfterLayout) {
    TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
    dispatchLayout();
    TraceCompat.endSection();
    return;
  }
  if (!mAdapterHelper.hasPendingUpdates()) {
    return;
  }
  if (mAdapterHelper.hasAnyUpdateTypes(AdapterHelper.UpdateOp.UPDATE) && !mAdapterHelper.hasAnyUpdateTypes(AdapterHelper.UpdateOp.ADD | AdapterHelper.UpdateOp.REMOVE | AdapterHelper.UpdateOp.MOVE)) {
    TraceCompat.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
    startInterceptRequestLayout();
    onEnterLayoutOrScroll();
    mAdapterHelper.preProcess();
    if (!mLayoutWasDefered) {
      if (hasUpdatedView()) {
        dispatchLayout();
      }
 else {
        mAdapterHelper.consumePostponedUpdates();
      }
    }
    stopInterceptRequestLayout(true);
    onExitLayoutOrScroll();
    TraceCompat.endSection();
  }
 else   if (mAdapterHelper.hasPendingUpdates()) {
    TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
    dispatchLayout();
    TraceCompat.endSection();
  }
}
