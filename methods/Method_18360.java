private void maybeRemoveAnimatingMountContent(TransitionId transitionId){
  if (mTransitionManager == null || transitionId == null) {
    return;
  }
  mTransitionManager.setMountContent(transitionId,null);
}
