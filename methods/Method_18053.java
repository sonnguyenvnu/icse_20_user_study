@Nullable public static DebugComponent getRootInstance(InternalNode rootInternalNode){
  final int outerWrapperComponentIndex=Math.max(0,rootInternalNode.getComponents().size() - 1);
  return DebugComponent.getInstance(rootInternalNode,outerWrapperComponentIndex);
}
