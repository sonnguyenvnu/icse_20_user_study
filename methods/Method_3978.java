/** 
 * The final step of the layout where we save the information about views for animations, trigger animations and do any necessary cleanup.
 */
private void dispatchLayoutStep3(){
  mState.assertLayoutStep(State.STEP_ANIMATIONS);
  startInterceptRequestLayout();
  onEnterLayoutOrScroll();
  mState.mLayoutStep=State.STEP_START;
  if (mState.mRunSimpleAnimations) {
    for (int i=mChildHelper.getChildCount() - 1; i >= 0; i--) {
      ViewHolder holder=getChildViewHolderInt(mChildHelper.getChildAt(i));
      if (holder.shouldIgnore()) {
        continue;
      }
      long key=getChangedHolderKey(holder);
      final ItemHolderInfo animationInfo=mItemAnimator.recordPostLayoutInformation(mState,holder);
      ViewHolder oldChangeViewHolder=mViewInfoStore.getFromOldChangeHolders(key);
      if (oldChangeViewHolder != null && !oldChangeViewHolder.shouldIgnore()) {
        final boolean oldDisappearing=mViewInfoStore.isDisappearing(oldChangeViewHolder);
        final boolean newDisappearing=mViewInfoStore.isDisappearing(holder);
        if (oldDisappearing && oldChangeViewHolder == holder) {
          mViewInfoStore.addToPostLayout(holder,animationInfo);
        }
 else {
          final ItemHolderInfo preInfo=mViewInfoStore.popFromPreLayout(oldChangeViewHolder);
          mViewInfoStore.addToPostLayout(holder,animationInfo);
          ItemHolderInfo postInfo=mViewInfoStore.popFromPostLayout(holder);
          if (preInfo == null) {
            handleMissingPreInfoForChangeError(key,holder,oldChangeViewHolder);
          }
 else {
            animateChange(oldChangeViewHolder,holder,preInfo,postInfo,oldDisappearing,newDisappearing);
          }
        }
      }
 else {
        mViewInfoStore.addToPostLayout(holder,animationInfo);
      }
    }
    mViewInfoStore.process(mViewInfoProcessCallback);
  }
  mLayout.removeAndRecycleScrapInt(mRecycler);
  mState.mPreviousLayoutItemCount=mState.mItemCount;
  mDataSetHasChangedAfterLayout=false;
  mDispatchItemsChangedEvent=false;
  mState.mRunSimpleAnimations=false;
  mState.mRunPredictiveAnimations=false;
  mLayout.mRequestedSimpleAnimations=false;
  if (mRecycler.mChangedScrap != null) {
    mRecycler.mChangedScrap.clear();
  }
  if (mLayout.mPrefetchMaxObservedInInitialPrefetch) {
    mLayout.mPrefetchMaxCountObserved=0;
    mLayout.mPrefetchMaxObservedInInitialPrefetch=false;
    mRecycler.updateViewCacheSize();
  }
  mLayout.onLayoutCompleted(mState);
  onExitLayoutOrScroll();
  stopInterceptRequestLayout(false);
  mViewInfoStore.clear();
  if (didChildRangeChange(mMinMaxLayoutPositions[0],mMinMaxLayoutPositions[1])) {
    dispatchOnScrolled(0,0);
  }
  recoverFocusFromState();
  resetFocusInfo();
}
