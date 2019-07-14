private void createNewTransitions(LayoutState newLayoutState,Transition rootTransition){
  prepareTransitionManager();
  mTransitionManager.setupTransitions(mLastMountedLayoutState,newLayoutState,rootTransition);
  final Map<TransitionId,?> nextTransitionIds=newLayoutState.getTransitionIdMapping();
  for (  TransitionId transitionId : nextTransitionIds.keySet()) {
    if (mTransitionManager.isAnimating(transitionId)) {
      mAnimatingTransitionIds.add(transitionId);
    }
  }
}
