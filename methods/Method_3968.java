/** 
 * Consumes adapter updates and calculates which type of animations we want to run. Called in onMeasure and dispatchLayout. <p> This method may process only the pre-layout state of updates or all of them.
 */
private void processAdapterUpdatesAndSetAnimationFlags(){
  if (mDataSetHasChangedAfterLayout) {
    mAdapterHelper.reset();
    if (mDispatchItemsChangedEvent) {
      mLayout.onItemsChanged(this);
    }
  }
  if (predictiveItemAnimationsEnabled()) {
    mAdapterHelper.preProcess();
  }
 else {
    mAdapterHelper.consumeUpdatesInOnePass();
  }
  boolean animationTypeSupported=mItemsAddedOrRemoved || mItemsChanged;
  mState.mRunSimpleAnimations=mFirstLayoutComplete && mItemAnimator != null && (mDataSetHasChangedAfterLayout || animationTypeSupported || mLayout.mRequestedSimpleAnimations) && (!mDataSetHasChangedAfterLayout || mAdapter.hasStableIds());
  mState.mRunPredictiveAnimations=mState.mRunSimpleAnimations && animationTypeSupported && !mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled();
}
