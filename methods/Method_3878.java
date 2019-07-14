@Override protected void onSeekTargetStep(int dx,int dy,RecyclerView.State state,Action action){
  if (getChildCount() == 0) {
    stop();
    return;
  }
  mInterimTargetDx=clampApplyScroll(mInterimTargetDx,dx);
  mInterimTargetDy=clampApplyScroll(mInterimTargetDy,dy);
  if (mInterimTargetDx == 0 && mInterimTargetDy == 0) {
    updateActionForInterimTarget(action);
  }
}
