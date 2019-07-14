@Override protected boolean applies(){
  boolean parentFastPath=Feature.usesFastPath(context.parentFeatures);
  boolean fastpath=Feature.usesFastPath(Sets.union(context.parentFeatures,context.generateFeatures));
  return (parentFastPath != fastpath);
}
