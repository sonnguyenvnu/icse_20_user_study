/** 
 * Iterate the map to check if a component is entered or exited the range, and dispatch event to the component to trigger its delegate method.
 */
void checkWorkingRangeAndDispatch(int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex,WorkingRangeStatusHandler statusHandler){
  if (mWorkingRanges == null) {
    return;
  }
  for (  String key : mWorkingRanges.keySet()) {
    final RangeTuple rangeTuple=mWorkingRanges.get(key);
    for (    Component component : rangeTuple.mComponents) {
      if (!statusHandler.isInRange(rangeTuple.mName,component) && isEnteringRange(rangeTuple.mWorkingRange,position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex)) {
        component.dispatchOnEnteredRange(rangeTuple.mName);
        statusHandler.setEnteredRangeStatus(rangeTuple.mName,component);
      }
 else       if (statusHandler.isInRange(rangeTuple.mName,component) && isExitingRange(rangeTuple.mWorkingRange,position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex)) {
        component.dispatchOnExitedRange(rangeTuple.mName);
        statusHandler.setExitedRangeStatus(rangeTuple.mName,component);
      }
    }
  }
}
