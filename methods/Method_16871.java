@Override protected boolean applies(){
  return !(Feature.usesMaximum(context.parentFeatures) || !Feature.usesMaximum(context.generateFeatures));
}
