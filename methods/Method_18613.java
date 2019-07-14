private void createAnimationsForTransitionUnitAllKeys(TransitionUnit transition,ArrayList<AnimationBinding> outList){
  for (  TransitionId transitionId : mAnimationStates.ids()) {
    final AnimationState animationState=mAnimationStates.get(transitionId);
    if (!animationState.seenInLastTransition) {
      continue;
    }
    createAnimationsForTransitionUnit(transition,transitionId,outList);
  }
}
