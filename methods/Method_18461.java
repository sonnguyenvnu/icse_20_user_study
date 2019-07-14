private void applyPreviousRenderData(Component component){
  if (!component.needsPreviousRenderData()) {
    throw new RuntimeException("Trying to apply previous render data to component that doesn't support it");
  }
  final String key=component.getGlobalKey();
  final ComponentLifecycle.RenderData previousRenderData=mRenderData.get(key);
  component.applyPreviousRenderData(previousRenderData);
}
