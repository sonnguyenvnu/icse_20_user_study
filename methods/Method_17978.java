/** 
 * Dispatch OnExitedRange event to component which is still in the range, then clear the handler.
 */
private synchronized void clearWorkingRangeStatusHandler(){
  final LayoutState layoutState=isBestMainThreadLayout() ? mMainThreadLayoutState : mBackgroundLayoutState;
  if (layoutState != null) {
    layoutState.dispatchOnExitRangeIfNeeded(mWorkingRangeStatusHandler);
  }
  mWorkingRangeStatusHandler.clear();
}
