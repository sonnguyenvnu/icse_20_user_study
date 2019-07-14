/** 
 * Wrapper around layoutChildren() that handles animating changes caused by layout. Animations work on the assumption that there are five different kinds of items in play: PERSISTENT: items are visible before and after layout REMOVED: items were visible before layout and were removed by the app ADDED: items did not exist before layout and were added by the app DISAPPEARING: items exist in the data set before/after, but changed from visible to non-visible in the process of layout (they were moved off screen as a side-effect of other changes) APPEARING: items exist in the data set before/after, but changed from non-visible to visible in the process of layout (they were moved on screen as a side-effect of other changes) The overall approach figures out what items exist before/after layout and infers one of the five above states for each of the items. Then the animations are set up accordingly: PERSISTENT views are animated via {@link ItemAnimator#animatePersistence(ViewHolder,ItemHolderInfo,ItemHolderInfo)}DISAPPEARING views are animated via {@link ItemAnimator#animateDisappearance(ViewHolder,ItemHolderInfo,ItemHolderInfo)}APPEARING views are animated via {@link ItemAnimator#animateAppearance(ViewHolder,ItemHolderInfo,ItemHolderInfo)}and changed views are animated via {@link ItemAnimator#animateChange(ViewHolder,ViewHolder,ItemHolderInfo,ItemHolderInfo)}.
 */
void dispatchLayout(){
  if (mAdapter == null) {
    Log.e(TAG,"No adapter attached; skipping layout");
    return;
  }
  if (mLayout == null) {
    Log.e(TAG,"No layout manager attached; skipping layout");
    return;
  }
  mState.mIsMeasuring=false;
  if (mState.mLayoutStep == State.STEP_START) {
    dispatchLayoutStep1();
    mLayout.setExactMeasureSpecsFrom(this);
    dispatchLayoutStep2();
  }
 else   if (mAdapterHelper.hasUpdates() || mLayout.getWidth() != getWidth() || mLayout.getHeight() != getHeight()) {
    mLayout.setExactMeasureSpecsFrom(this);
    dispatchLayoutStep2();
  }
 else {
    mLayout.setExactMeasureSpecsFrom(this);
  }
  dispatchLayoutStep3();
}
