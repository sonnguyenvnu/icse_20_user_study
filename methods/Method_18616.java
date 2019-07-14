private void setMountContentInner(TransitionId transitionId,AnimationState animationState,OutputUnitsAffinityGroup<Object> newMountContentGroup){
  final OutputUnitsAffinityGroup<Object> mountContentGroup=animationState.mountContentGroup;
  if ((mountContentGroup == null && newMountContentGroup == null) || (mountContentGroup != null && mountContentGroup.equals(newMountContentGroup))) {
    return;
  }
  if (AnimationsDebug.ENABLED) {
    Log.d(AnimationsDebug.TAG,"Setting mount content for " + transitionId + " to " + newMountContentGroup);
  }
  final Map<AnimatedProperty,PropertyState> animatingProperties=animationState.propertyStates;
  if (animationState.mountContentGroup != null) {
    for (    AnimatedProperty animatedProperty : animatingProperties.keySet()) {
      resetProperty(animatedProperty,animationState.mountContentGroup);
    }
    recursivelySetChildClippingForGroup(animationState.mountContentGroup,true);
  }
  for (  PropertyState propertyState : animatingProperties.values()) {
    propertyState.animatedPropertyNode.setMountContentGroup(newMountContentGroup);
  }
  if (newMountContentGroup != null) {
    recursivelySetChildClippingForGroup(newMountContentGroup,false);
  }
  animationState.mountContentGroup=newMountContentGroup;
}
