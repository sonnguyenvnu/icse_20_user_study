@Nullable public static DebugComponent getRootInstance(@Nullable ComponentTree componentTree){
  final LayoutState layoutState=componentTree == null ? null : componentTree.getMainThreadLayoutState();
  final InternalNode root=layoutState == null ? null : layoutState.getLayoutRoot();
  if (root != null && root != ComponentContext.NULL_LAYOUT) {
    final int outerWrapperComponentIndex=Math.max(0,root.getComponents().size() - 1);
    return DebugComponent.getInstance(root,outerWrapperComponentIndex);
  }
  return null;
}
