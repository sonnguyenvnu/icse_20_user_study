private static @Nullable void collectMountTimeTransitions(LayoutState layoutState,List<Transition> outList){
  final List<Component> componentsNeedingPreviousRenderData=layoutState.getComponentsNeedingPreviousRenderData();
  if (componentsNeedingPreviousRenderData == null) {
    return;
  }
  for (int i=0, size=componentsNeedingPreviousRenderData.size(); i < size; i++) {
    final Component component=componentsNeedingPreviousRenderData.get(i);
    final Transition transition=component.createTransition(component.getScopedContext());
    if (transition != null) {
      TransitionUtils.addTransitions(transition,outList,layoutState.mRootComponentName);
    }
  }
}
