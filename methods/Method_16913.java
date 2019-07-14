@Override protected void execute(){
  if (!Feature.usesAccessOrderWindowDeque(context.parentFeatures) && Feature.usesAccessOrderWindowDeque(context.generateFeatures)) {
    addFieldAndGetter("previousInAccessOrder");
    addFieldAndGetter("nextInAccessOrder");
  }
  if (!Feature.usesWriteOrderDeque(context.parentFeatures) && Feature.usesWriteOrderDeque(context.generateFeatures)) {
    addFieldAndGetter("previousInWriteOrder");
    addFieldAndGetter("nextInWriteOrder");
  }
}
