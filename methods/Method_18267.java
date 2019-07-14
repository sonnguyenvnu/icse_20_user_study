private static boolean isReconcilable(final ComponentContext c,final Component nextRootComponent,final @Nullable LayoutState currentLayoutState){
  if (currentLayoutState == null || currentLayoutState.mLayoutRoot == null || !c.isReconciliationEnabled()) {
    return false;
  }
  StateHandler stateHandler=c.getStateHandler();
  if (stateHandler == null || !stateHandler.hasPendingUpdates()) {
    return false;
  }
  final Component previous=currentLayoutState.mComponent;
  if (!isSameComponentType(previous,nextRootComponent)) {
    return false;
  }
  if (!nextRootComponent.isEquivalentTo(previous)) {
    return false;
  }
  return true;
}
