private static boolean willRender(InternalNode node){
  if (node == null || ComponentContext.NULL_LAYOUT.equals(node)) {
    return false;
  }
  if (node.isNestedTreeHolder()) {
    throw new IllegalArgumentException("Cannot check willRender on a component that uses @OnCreateLayoutWithSizeSpec! " + "Try wrapping this component in one that uses @OnCreateLayout if possible.");
  }
  return true;
}
