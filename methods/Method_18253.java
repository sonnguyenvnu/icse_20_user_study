/** 
 * Acquires a new layout output for the internal node and its associated component. It returns null if there's no component associated with the node as the mount pass only cares about nodes that will potentially mount content into the component host.
 */
@Nullable private static LayoutOutput createGenericLayoutOutput(InternalNode node,LayoutState layoutState,boolean hasHostView){
  final Component component=node.getTailComponent();
  if (component == null || component.getMountType() == NONE) {
    return null;
  }
  long hostMarker=layoutState.mCurrentHostMarker;
  return createLayoutOutput(component,hostMarker,layoutState,node,true,node.getImportantForAccessibility(),layoutState.mShouldDuplicateParentState,hasHostView);
}
