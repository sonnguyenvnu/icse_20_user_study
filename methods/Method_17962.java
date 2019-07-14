void applyPreviousRenderData(LayoutState layoutState){
  final List<Component> components=layoutState.getComponentsNeedingPreviousRenderData();
  if (components == null || components.isEmpty()) {
    return;
  }
  if (mPreviousRenderState == null) {
    return;
  }
  mPreviousRenderState.applyPreviousRenderData(components);
}
