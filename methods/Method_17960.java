private int getInitialAnimatedLithoViewDimension(int currentAnimatedDimension,boolean hasNewComponentTree,@Nullable Transition.RootBoundsTransition rootBoundsTransition,AnimatedProperty property){
  if (rootBoundsTransition == null) {
    return -1;
  }
  if (!mHasMounted && rootBoundsTransition.appearTransition != null) {
    return (int)Transition.getRootAppearFromValue(rootBoundsTransition.appearTransition,mMainThreadLayoutState,property);
  }
  if (mHasMounted && !hasNewComponentTree) {
    return currentAnimatedDimension;
  }
  return -1;
}
