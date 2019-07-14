@VisibleForTesting static InternalNode createAndMeasureTreeForComponent(ComponentContext c,Component component,int widthSpec,int heightSpec,@Nullable InternalNode nestedTreeHolder,@Nullable InternalNode current,@Nullable DiffNode diffTreeRoot,@Nullable PerfEvent layoutStatePerfEvent){
  if (layoutStatePerfEvent != null) {
    layoutStatePerfEvent.markerPoint("start_create_layout");
  }
  component.updateInternalChildState(c);
  if (ComponentsConfiguration.isDebugModeEnabled) {
    DebugComponent.applyOverrides(c,component);
  }
  c=component.getScopedContext();
  final boolean isTest="robolectric".equals(Build.FINGERPRINT);
  if (!isTest) {
    c=c.makeNewCopy();
  }
  final boolean hasNestedTreeHolder=nestedTreeHolder != null;
  if (hasNestedTreeHolder) {
    c.setTreeProps(nestedTreeHolder.getPendingTreeProps());
  }
  final int previousWidthSpec=c.getWidthSpec();
  final int previousHeightSpec=c.getHeightSpec();
  c.setWidthSpec(widthSpec);
  c.setHeightSpec(heightSpec);
  final InternalNode root=createTree(component,c,current);
  c.setTreeProps(null);
  c.setWidthSpec(previousWidthSpec);
  c.setHeightSpec(previousHeightSpec);
  if (layoutStatePerfEvent != null) {
    layoutStatePerfEvent.markerPoint("end_create_layout");
  }
  if (root == NULL_LAYOUT || c.wasLayoutInterrupted()) {
    return root;
  }
  if (hasNestedTreeHolder && isLayoutSpecWithSizeSpec(component)) {
    nestedTreeHolder.copyInto(root);
    diffTreeRoot=nestedTreeHolder.getDiffNode();
  }
 else   if (root.getStyleDirection() == com.facebook.yoga.YogaDirection.INHERIT && LayoutState.isLayoutDirectionRTL(c.getAndroidContext())) {
    root.layoutDirection(YogaDirection.RTL);
  }
  if (layoutStatePerfEvent != null) {
    layoutStatePerfEvent.markerPoint("start_measure");
  }
  measureTree(root,widthSpec,heightSpec,diffTreeRoot);
  if (layoutStatePerfEvent != null) {
    layoutStatePerfEvent.markerPoint("end_measure");
  }
  return root;
}
