private Transition mergeTransitions(boolean isPush){
  boolean overlap=enterTransition == null || exitTransition == null || allowTransitionOverlap(isPush);
  if (overlap) {
    return TransitionUtils.mergeTransitions(TransitionSet.ORDERING_TOGETHER,exitTransition,enterTransition,sharedElementTransition);
  }
 else {
    Transition staggered=TransitionUtils.mergeTransitions(TransitionSet.ORDERING_SEQUENTIAL,exitTransition,enterTransition);
    return TransitionUtils.mergeTransitions(TransitionSet.ORDERING_TOGETHER,staggered,sharedElementTransition);
  }
}
