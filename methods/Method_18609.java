/** 
 * Called to record the current/next content for a transition key.
 * @param currentLayoutOutputsGroup the current group of LayoutOutputs for this key, or null ifthe key is appearing
 * @param nextLayoutOutputsGroup the new group of LayoutOutput for this key, or null if the key isdisappearing
 */
private void recordLayoutOutputsGroupDiff(TransitionId transitionId,OutputUnitsAffinityGroup<LayoutOutput> currentLayoutOutputsGroup,OutputUnitsAffinityGroup<LayoutOutput> nextLayoutOutputsGroup){
  AnimationState animationState=mAnimationStates.get(transitionId);
  if (animationState == null) {
    animationState=new AnimationState();
    mAnimationStates.put(transitionId,animationState);
  }
  if (currentLayoutOutputsGroup == null && nextLayoutOutputsGroup == null) {
    throw new RuntimeException("Both current and next LayoutOutput groups were null!");
  }
  if (currentLayoutOutputsGroup == null && nextLayoutOutputsGroup != null) {
    animationState.changeType=ChangeType.APPEARED;
  }
 else   if (currentLayoutOutputsGroup != null && nextLayoutOutputsGroup != null) {
    animationState.changeType=ChangeType.CHANGED;
  }
 else {
    animationState.changeType=ChangeType.DISAPPEARED;
  }
  animationState.currentLayoutOutputsGroup=currentLayoutOutputsGroup;
  animationState.nextLayoutOutputsGroup=nextLayoutOutputsGroup;
  recordLastMountedValues(animationState);
  animationState.seenInLastTransition=true;
  if (AnimationsDebug.ENABLED) {
    Log.d(AnimationsDebug.TAG,"Saw transition id " + transitionId + " which is " + changeTypeToString(animationState.changeType));
  }
}
