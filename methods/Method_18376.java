/** 
 * Determine whether to apply disappear animation to the given  {@link MountItem}
 */
private boolean isItemDisappearing(LayoutState layoutState,int index){
  if (!shouldAnimateTransitions(layoutState) || !hasTransitionsToAnimate()) {
    return false;
  }
  if (mTransitionManager == null || mLastMountedLayoutState == null) {
    return false;
  }
  final LayoutOutput layoutOutput=mLastMountedLayoutState.getMountableOutputAt(index);
  final TransitionId transitionId=layoutOutput.getTransitionId();
  if (transitionId == null) {
    return false;
  }
  return mTransitionManager.isDisappearing(transitionId);
}
