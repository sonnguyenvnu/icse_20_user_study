private void restoreInitialStates(){
  for (  PropertyHandle propertyHandle : mInitialStatesToRestore.keySet()) {
    final float value=mInitialStatesToRestore.get(propertyHandle);
    final TransitionId transitionId=propertyHandle.getTransitionId();
    final AnimationState animationState=mAnimationStates.get(transitionId);
    if (animationState.mountContentGroup != null) {
      setPropertyValue(propertyHandle.getProperty(),value,animationState.mountContentGroup);
    }
  }
  mInitialStatesToRestore.clear();
}
