void applyPreviousRenderData(List<Component> components){
  if (components == null) {
    return;
  }
  for (int i=0, size=components.size(); i < size; i++) {
    applyPreviousRenderData(components.get(i));
  }
}
