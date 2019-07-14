@Override protected void execute(){
  addFactories();
  if (context.generateFeatures.contains(Feature.WEAK_KEYS)) {
    addWeakKeys();
  }
  if (context.generateFeatures.contains(Feature.WEAK_VALUES)) {
    addWeakValues();
  }
 else   if (context.generateFeatures.contains(Feature.SOFT_VALUES)) {
    addSoftValues();
  }
}
