protected boolean isStrongValues(){
  return context.parentFeatures.contains(Feature.STRONG_VALUES) || context.generateFeatures.contains(Feature.STRONG_VALUES);
}
