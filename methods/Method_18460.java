private void recordRenderData(Component component){
  if (!component.needsPreviousRenderData()) {
    throw new RuntimeException("Trying to record previous render data for component that doesn't support it");
  }
  final String key=component.getGlobalKey();
  if (mSeenGlobalKeys.contains(key)) {
    throw new RuntimeException("Cannot record previous render data for " + component.getSimpleName() + ", found another Component with the same key: " + key);
  }
  mSeenGlobalKeys.add(key);
  final ComponentLifecycle.RenderData existingInfo=mRenderData.get(key);
  final ComponentLifecycle.RenderData newInfo=component.recordRenderData(existingInfo);
  mRenderData.put(key,newInfo);
}
