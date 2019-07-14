private void recordRenderData(LayoutState layoutState){
  final List<Component> components=layoutState.getComponentsNeedingPreviousRenderData();
  if (components == null || components.isEmpty()) {
    return;
  }
  if (mPreviousRenderState == null) {
    mPreviousRenderState=new RenderState();
  }
  mPreviousRenderState.recordRenderData(components);
}
