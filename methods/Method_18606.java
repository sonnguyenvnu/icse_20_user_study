void removeMountContent(TransitionId transitionId,@OutputUnitType int type){
  final AnimationState animationState=mAnimationStates.get(transitionId);
  if (animationState == null) {
    return;
  }
  final OutputUnitsAffinityGroup<Object> mountContentGroup=animationState.mountContentGroup;
  if (mountContentGroup == null || mountContentGroup.get(type) == null) {
    return;
  }
  OutputUnitsAffinityGroup<Object> updatedMountContentGroup;
  if (mountContentGroup.size() > 1) {
    updatedMountContentGroup=new OutputUnitsAffinityGroup<>(mountContentGroup);
    updatedMountContentGroup.replace(type,null);
  }
 else {
    updatedMountContentGroup=null;
  }
  setMountContentInner(transitionId,animationState,updatedMountContentGroup);
}
