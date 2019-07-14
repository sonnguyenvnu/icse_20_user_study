protected boolean isStrongKeys(){
  return context.parentFeatures.contains(Feature.STRONG_KEYS) || context.generateFeatures.contains(Feature.STRONG_KEYS);
}
