/** 
 * {@inheritDoc}
 */
@Override protected void onSeekTargetStep(int dx,int dy,RecyclerView.State state,Action action){
  if (getChildCount() == 0) {
    stop();
    return;
  }
  if (DEBUG && mTargetVector != null && ((mTargetVector.x * dx < 0 || mTargetVector.y * dy < 0))) {
    throw new IllegalStateException("Scroll happened in the opposite direction" + " of the target. Some calculations are wrong");
  }
  mInterimTargetDx=clampApplyScroll(mInterimTargetDx,dx);
  mInterimTargetDy=clampApplyScroll(mInterimTargetDy,dy);
  if (mInterimTargetDx == 0 && mInterimTargetDy == 0) {
    updateActionForInterimTarget(action);
  }
}
