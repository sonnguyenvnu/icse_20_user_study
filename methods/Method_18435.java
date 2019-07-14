/** 
 * Creates and updates transitions for a new LayoutState. The steps are as follows: <p>1. Disappearing items: Update disappearing mount items that are no longer disappearing (e.g. because they came back). This means canceling the animation and cleaning up the corresponding ComponentHost. <p>2. New transitions: Use the transition manager to create new animations. <p>3. Update locked indices: Based on running/new animations, there are some mount items we want to make sure are not unmounted due to incremental mount and being outside of visibility bounds.
 */
private void updateTransitions(LayoutState layoutState,ComponentTree componentTree){
  if (!mIsDirty) {
    throw new RuntimeException("Should only process transitions on dirty mounts");
  }
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    String logTag=componentTree.getContext().getLogTag();
    if (logTag == null) {
      ComponentsSystrace.beginSection("MountState.updateTransitions");
    }
 else {
      ComponentsSystrace.beginSection("MountState.updateTransitions:" + logTag);
    }
  }
  try {
    final int componentTreeId=layoutState.getComponentTreeId();
    if (mLastMountedComponentTreeId != componentTreeId) {
      resetAnimationState();
      if (!mIsFirstMountOfComponentTree) {
        return;
      }
    }
    if (!mDisappearingMountItems.isEmpty()) {
      updateDisappearingMountItems(layoutState);
    }
    if (shouldAnimateTransitions(layoutState)) {
      collectAllTransitions(layoutState,componentTree);
      if (hasTransitionsToAnimate()) {
        createNewTransitions(layoutState,mRootTransition);
      }
    }
    mAnimationLockedIndices=null;
    if (!mAnimatingTransitionIds.isEmpty()) {
      regenerateAnimationLockedIndices(layoutState);
    }
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
}
