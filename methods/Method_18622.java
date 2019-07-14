private static void clearLayoutOutputs(AnimationState animationState){
  if (animationState.currentLayoutOutputsGroup != null) {
    animationState.currentLayoutOutputsGroup=null;
  }
  if (animationState.nextLayoutOutputsGroup != null) {
    animationState.nextLayoutOutputsGroup=null;
  }
}
