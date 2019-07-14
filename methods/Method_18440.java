/** 
 * @return whether we should animate transitions if we have any when mounting the new LayoutState.
 */
private boolean shouldAnimateTransitions(LayoutState newLayoutState){
  return mIsDirty && (mLastMountedComponentTreeId == newLayoutState.getComponentTreeId() || mIsFirstMountOfComponentTree);
}
