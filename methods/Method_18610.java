private void recordLastMountedValues(AnimationState animationState){
  final LayoutOutput layoutOutput=animationState.nextLayoutOutputsGroup != null ? animationState.nextLayoutOutputsGroup.getMostSignificantUnit() : null;
  for (  AnimatedProperty property : animationState.propertyStates.keySet()) {
    final PropertyState propertyState=animationState.propertyStates.get(property);
    if (layoutOutput == null) {
      propertyState.lastMountedValue=null;
    }
 else {
      propertyState.lastMountedValue=property.get(layoutOutput);
    }
  }
}
