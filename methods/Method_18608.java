/** 
 * After transitions have been setup with  {@link #setupTransitions}, returns whether the given key whether the given key is disappearing.
 */
boolean isDisappearing(TransitionId transitionId){
  final AnimationState animationState=mAnimationStates.get(transitionId);
  if (animationState == null) {
    return false;
  }
  return animationState.changeType == ChangeType.DISAPPEARED;
}
