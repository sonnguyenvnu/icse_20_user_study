@VisibleForTesting static void measureTree(InternalNode root,int widthSpec,int heightSpec,DiffNode previousDiffTreeRoot){
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("measureTree:" + root.getSimpleName());
  }
  if (YogaConstants.isUndefined(root.getStyleWidth())) {
    root.setStyleWidthFromSpec(widthSpec);
  }
  if (YogaConstants.isUndefined(root.getStyleHeight())) {
    root.setStyleHeightFromSpec(heightSpec);
  }
  if (previousDiffTreeRoot != null) {
    ComponentsSystrace.beginSection("applyDiffNode");
    applyDiffNodeToUnchangedNodes(root,previousDiffTreeRoot);
    ComponentsSystrace.endSection();
  }
  root.calculateLayout(SizeSpec.getMode(widthSpec) == SizeSpec.UNSPECIFIED ? YogaConstants.UNDEFINED : SizeSpec.getSize(widthSpec),SizeSpec.getMode(heightSpec) == SizeSpec.UNSPECIFIED ? YogaConstants.UNDEFINED : SizeSpec.getSize(heightSpec));
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
}
