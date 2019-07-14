/** 
 * Check if the any child components stored in  {@link LayoutState} have entered/exited the workingrange, and dispatch the event to trigger the corresponding registered methods.
 */
public synchronized void checkWorkingRangeAndDispatch(int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex){
  LayoutState layoutState=isBestMainThreadLayout() ? mMainThreadLayoutState : mBackgroundLayoutState;
  if (layoutState != null) {
    layoutState.checkWorkingRangeAndDispatch(position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex,mWorkingRangeStatusHandler);
  }
}
