/** 
 * The second layout step where we do the actual layout of the views for the final state. This step might be run multiple times if necessary (e.g. measure).
 */
private void dispatchLayoutStep2(){
  startInterceptRequestLayout();
  onEnterLayoutOrScroll();
  mState.assertLayoutStep(State.STEP_LAYOUT | State.STEP_ANIMATIONS);
  mAdapterHelper.consumeUpdatesInOnePass();
  mState.mItemCount=mAdapter.getItemCount();
  mState.mDeletedInvisibleItemCountSincePreviousLayout=0;
  mState.mInPreLayout=false;
  mLayout.onLayoutChildren(mRecycler,mState);
  mState.mStructureChanged=false;
  mPendingSavedState=null;
  mState.mRunSimpleAnimations=mState.mRunSimpleAnimations && mItemAnimator != null;
  mState.mLayoutStep=State.STEP_ANIMATIONS;
  onExitLayoutOrScroll();
  stopInterceptRequestLayout(false);
}
