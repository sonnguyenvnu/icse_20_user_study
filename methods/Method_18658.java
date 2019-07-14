/** 
 * Dispatch onExitRange if the status of the component is in the range. This method should only be called when releasing a ComponentTree, thus no status update needed.
 */
void dispatchOnExitedRangeIfNeeded(WorkingRangeStatusHandler statusHandler){
  if (mWorkingRanges == null) {
    return;
  }
  for (  String key : mWorkingRanges.keySet()) {
    final RangeTuple rangeTuple=mWorkingRanges.get(key);
    for (    Component component : rangeTuple.mComponents) {
      if (statusHandler.isInRange(rangeTuple.mName,component)) {
        component.dispatchOnExitedRange(rangeTuple.mName);
      }
    }
  }
}
