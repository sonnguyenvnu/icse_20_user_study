/** 
 * After transitions have been setup with  {@link #setupTransitions}, returns whether the given key will be/is animating.
 */
boolean isAnimating(TransitionId transitionId){
  return mAnimationStates.contains(transitionId);
}
