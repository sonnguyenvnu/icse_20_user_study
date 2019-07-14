@Override protected boolean applies(){
  return !(Feature.usesWriteQueue(context.parentFeatures) || !Feature.usesWriteQueue(context.generateFeatures));
}
