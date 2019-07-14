/** 
 * The first step of a layout where we; - process adapter updates - decide which animation should run - save information about current views - If necessary, run predictive layout and save its information
 */
private void dispatchLayoutStep1(){
  mState.assertLayoutStep(State.STEP_START);
  fillRemainingScrollValues(mState);
  mState.mIsMeasuring=false;
  startInterceptRequestLayout();
  mViewInfoStore.clear();
  onEnterLayoutOrScroll();
  processAdapterUpdatesAndSetAnimationFlags();
  saveFocusInfo();
  mState.mTrackOldChangeHolders=mState.mRunSimpleAnimations && mItemsChanged;
  mItemsAddedOrRemoved=mItemsChanged=false;
  mState.mInPreLayout=mState.mRunPredictiveAnimations;
  mState.mItemCount=mAdapter.getItemCount();
  findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
  if (mState.mRunSimpleAnimations) {
    int count=mChildHelper.getChildCount();
    for (int i=0; i < count; ++i) {
      final ViewHolder holder=getChildViewHolderInt(mChildHelper.getChildAt(i));
      if (holder.shouldIgnore() || (holder.isInvalid() && !mAdapter.hasStableIds())) {
        continue;
      }
      final ItemHolderInfo animationInfo=mItemAnimator.recordPreLayoutInformation(mState,holder,ItemAnimator.buildAdapterChangeFlagsForAnimations(holder),holder.getUnmodifiedPayloads());
      mViewInfoStore.addToPreLayout(holder,animationInfo);
      if (mState.mTrackOldChangeHolders && holder.isUpdated() && !holder.isRemoved() && !holder.shouldIgnore() && !holder.isInvalid()) {
        long key=getChangedHolderKey(holder);
        mViewInfoStore.addToOldChangeHolders(key,holder);
      }
    }
  }
  if (mState.mRunPredictiveAnimations) {
    saveOldPositions();
    final boolean didStructureChange=mState.mStructureChanged;
    mState.mStructureChanged=false;
    mLayout.onLayoutChildren(mRecycler,mState);
    mState.mStructureChanged=didStructureChange;
    for (int i=0; i < mChildHelper.getChildCount(); ++i) {
      final View child=mChildHelper.getChildAt(i);
      final ViewHolder viewHolder=getChildViewHolderInt(child);
      if (viewHolder.shouldIgnore()) {
        continue;
      }
      if (!mViewInfoStore.isInPreLayout(viewHolder)) {
        int flags=ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder);
        boolean wasHidden=viewHolder.hasAnyOfTheFlags(ViewHolder.FLAG_BOUNCED_FROM_HIDDEN_LIST);
        if (!wasHidden) {
          flags|=ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
        }
        final ItemHolderInfo animationInfo=mItemAnimator.recordPreLayoutInformation(mState,viewHolder,flags,viewHolder.getUnmodifiedPayloads());
        if (wasHidden) {
          recordAnimationInfoIfBouncedHiddenView(viewHolder,animationInfo);
        }
 else {
          mViewInfoStore.addToAppearedInPreLayoutHolders(viewHolder,animationInfo);
        }
      }
    }
    clearOldPositions();
  }
 else {
    clearOldPositions();
  }
  onExitLayoutOrScroll();
  stopInterceptRequestLayout(false);
  mState.mLayoutStep=State.STEP_LAYOUT;
}
