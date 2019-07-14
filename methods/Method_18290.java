/** 
 * Returns true if this is the root node (which always generates a matching layout output), if the node has view attributes e.g. tags, content description, etc, or if the node has explicitly been forced to be wrapped in a view.
 */
private static boolean needsHostView(InternalNode node,LayoutState layoutState){
  if (layoutState.isLayoutRoot(node)) {
    return true;
  }
  final Component component=node.getTailComponent();
  if (isMountViewSpec(component)) {
    return false;
  }
  if (node.isForceViewWrapping()) {
    return true;
  }
  if (hasViewContent(node,layoutState)) {
    return true;
  }
  if (component != null && component.hasCommonDynamicProps()) {
    return true;
  }
  if (needsHostViewForTransition(node)) {
    return true;
  }
  return false;
}
