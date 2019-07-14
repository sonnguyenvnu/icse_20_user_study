/** 
 * Collect transitions from layout time, mount time and from state updates.
 * @param layoutState that is going to be mounted.
 */
void collectAllTransitions(LayoutState layoutState,ComponentTree componentTree){
  assertMainThread();
  if (mTransitionsHasBeenCollected) {
    return;
  }
  final ArrayList<Transition> allTransitions=new ArrayList<>();
  if (layoutState.getTransitions() != null) {
    allTransitions.addAll(layoutState.getTransitions());
  }
  componentTree.applyPreviousRenderData(layoutState);
  collectMountTimeTransitions(layoutState,allTransitions);
  componentTree.consumeStateUpdateTransitions(allTransitions,layoutState.mRootComponentName);
  Transition.RootBoundsTransition rootWidthTransition=new Transition.RootBoundsTransition();
  Transition.RootBoundsTransition rootHeightTransition=new Transition.RootBoundsTransition();
  final TransitionId rootTransitionId=layoutState.getRootTransitionId();
  if (rootTransitionId != null) {
    for (int i=0, size=allTransitions.size(); i < size; i++) {
      final Transition transition=allTransitions.get(i);
      if (transition == null) {
        throw new IllegalStateException("NULL_TRANSITION when collecting root bounds anim. Root: " + layoutState.mRootComponentName + ", root TransitionId: " + rootTransitionId);
      }
      TransitionUtils.collectRootBoundsTransitions(rootTransitionId,transition,AnimatedProperties.WIDTH,rootWidthTransition);
      TransitionUtils.collectRootBoundsTransitions(rootTransitionId,transition,AnimatedProperties.HEIGHT,rootHeightTransition);
    }
  }
  rootWidthTransition=rootWidthTransition.hasTransition ? rootWidthTransition : null;
  rootHeightTransition=rootHeightTransition.hasTransition ? rootHeightTransition : null;
  componentTree.setRootWidthAnimation(rootWidthTransition);
  componentTree.setRootHeightAnimation(rootHeightTransition);
  mRootTransition=TransitionManager.getRootTransition(allTransitions);
  mTransitionsHasBeenCollected=true;
}
