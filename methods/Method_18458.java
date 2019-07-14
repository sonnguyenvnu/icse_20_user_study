void recordRenderData(List<Component> components){
  if (components == null) {
    return;
  }
  for (int i=0, size=components.size(); i < size; i++) {
    recordRenderData(components.get(i));
  }
  mSeenGlobalKeys.clear();
}
